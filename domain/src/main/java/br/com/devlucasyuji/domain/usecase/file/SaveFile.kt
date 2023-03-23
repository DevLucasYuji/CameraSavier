package br.com.devlucasyuji.domain.usecase.file

import android.graphics.Bitmap
import java.io.File

interface SaveFile {

    /**
     * Save byte array in file.
     *
     * @param parentFile the parent file where's to be saved.
     * @param bitmap the bitmap to be saved.
     * @param extension extension from file.
     * */
    operator fun invoke(
        parentFile: File,
        bitmap: Bitmap,
        extension: String
    ): File
}
