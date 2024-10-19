package com.ujizin.leafy.publish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.domain.usecase.plant.add.AddDraftPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion

@HiltViewModel
class PublishViewModel @Inject constructor(private val addDraftPlant: AddDraftPlantUseCase) :
    ViewModel() {

    fun sendDraftPlant(title: String, description: String, onFinishPublish: () -> Unit) {
        addDraftPlant(title = title, description = description)
            .onCompletion { onFinishPublish() }
            .launchIn(viewModelScope)
    }
}
