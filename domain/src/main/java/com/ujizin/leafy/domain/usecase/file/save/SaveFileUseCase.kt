package com.ujizin.leafy.domain.usecase.file.save

import android.graphics.Bitmap
import java.io.File

interface SaveFileUseCase {

    /**
     * Save byte array in file.
     *
     * @param parentFile the parent file where's to be saved.
     * @param bitmap the bitmap to be saved.
     * @param extension extension from file.
     */
    operator fun invoke(parentFile: File, bitmap: Bitmap, extension: String): File
}
