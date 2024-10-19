package com.ujizin.leafy.domain.repository

import android.graphics.Bitmap
import java.io.File

interface FileRepository {
    /**
     * Save byte array in file.
     *
     * @param parentFile the parent file where's to be saved.
     * @param bitmap the bitmap to be saved.
     * @param extension extension from file.
     */
    fun saveBitmap(parentFile: File, bitmap: Bitmap, extension: String): File
}
