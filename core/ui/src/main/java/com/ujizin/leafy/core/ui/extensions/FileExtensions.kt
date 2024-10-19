package com.ujizin.leafy.core.ui.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Build
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File

fun ByteArrayOutputStream.decodeToBitmapWithRotation(): Bitmap? {
    val byteArray = toByteArray()
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size) ?: return null

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return bitmap

    val orientation =
        ExifInterface(ByteArrayInputStream(byteArray))
            .getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

    val degrees =
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90,
            ExifInterface.ORIENTATION_TRANSPOSE -> 90F
            ExifInterface.ORIENTATION_ROTATE_180,
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> 180F
            ExifInterface.ORIENTATION_ROTATE_270 -> 270F
            ExifInterface.ORIENTATION_TRANSVERSE -> -90F
            else -> 0F
        }

    val scale =
        when (orientation) {
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL,
            ExifInterface.ORIENTATION_FLIP_VERTICAL,
            ExifInterface.ORIENTATION_TRANSPOSE,
            ExifInterface.ORIENTATION_TRANSVERSE -> -1F to 1F

            else -> 1F to 1F
        }

    val matrix =
        Matrix().apply {
            postRotate(degrees)
            postScale(scale.first, scale.second)
        }

    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun File.copyAndDelete(destFile: File): File {
    val copiedFile = copyTo(destFile)
    delete()
    return copiedFile
}
