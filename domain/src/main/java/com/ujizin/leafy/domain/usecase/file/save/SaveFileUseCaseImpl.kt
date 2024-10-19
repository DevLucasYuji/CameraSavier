package com.ujizin.leafy.domain.usecase.file.save

import android.graphics.Bitmap
import com.ujizin.leafy.domain.repository.FileRepository
import java.io.File

internal class SaveFileUseCaseImpl(private val repository: FileRepository) : SaveFileUseCase {

    override operator fun invoke(parentFile: File, bitmap: Bitmap, extension: String): File =
        repository.saveBitmap(parentFile, bitmap, extension)
}
