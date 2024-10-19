package com.ujizin.leafy.camera.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.camposer.extensions.takePicture
import com.ujizin.camposer.state.CameraState
import com.ujizin.leafy.core.ui.extensions.decodeToBitmapWithRotation
import com.ujizin.leafy.core.ui.extensions.launchCatching
import com.ujizin.leafy.domain.usecase.file.save.SaveFileUseCase
import com.ujizin.leafy.domain.usecase.plant.add.AddDraftPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update

@HiltViewModel
class CameraViewModel
@Inject
constructor(
    private val saveFile: SaveFileUseCase,
    private val addDraftPlant: AddDraftPlantUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CameraUiState>(CameraUiState.Initial)
    val uiState: StateFlow<CameraUiState>
        get() = _uiState

    fun takePicture(cameraState: CameraState) {
        viewModelScope.launchCatching({ _, exception ->
            _uiState.update { CameraUiState.Error("${exception.message}") }
        }) {
            val outputStream = ByteArrayOutputStream()
            val outputFile = ImageCapture.OutputFileOptions.Builder(outputStream).build()

            cameraState.takePicture(outputFile)

            val bitmap = outputStream.decodeToBitmapWithRotation() ?: return@launchCatching

            _uiState.update { CameraUiState.Preview(bitmap) }
        }
    }

    fun onBackCamera() {
        _uiState.update { CameraUiState.Initial }
    }

    fun saveImage(context: Context, bitmap: Bitmap, onImageSaved: () -> Unit) {
        addDraftPlant(file = saveFile(context.cacheDir, bitmap, "jpg"))
            .onCompletion { onImageSaved() }
            .launchIn(viewModelScope)
    }

    fun preparePreview(contentResolver: ContentResolver, uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        _uiState.update { CameraUiState.Preview(bitmap) }

        inputStream?.close()
    }
}

sealed interface CameraUiState {
    data object Initial : CameraUiState

    data class Preview(val bitmap: Bitmap) : CameraUiState

    data class Error(val message: String) : CameraUiState
}
