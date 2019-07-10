package nz.atomic.skadi.extensions.mat

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

fun Mat.transpose(dstMat: Mat): Mat {
    Core.transpose(this, dstMat)
    return dstMat
}

fun Mat.flip(dstMat: Mat): Mat {
    Core.flip(this, dstMat, 1)
    return dstMat
}

fun Mat.resize(dstMat: Mat, size: Size): Mat {
    Imgproc.resize(this, dstMat, size)
    return dstMat
}

fun Mat.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), config)
    Utils.matToBitmap(this, bitmap)
    return bitmap
}
