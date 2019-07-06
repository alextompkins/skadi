package nz.atomic.skadi.extensions

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

/*
 * Extension functions for [Mat].
 */

fun Mat.toGray(grayMat: Mat): Mat {
    Imgproc.cvtColor(this, grayMat, Imgproc.COLOR_RGB2GRAY)
    return grayMat
}

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

fun Mat.gaussianBlur(
    dstMat: Mat,
    kSize: Size = Size(125.toDouble(), 125.toDouble()),
    sigmaX:Double = 0.toDouble()
): Mat {
    Imgproc.GaussianBlur(this, dstMat, kSize, sigmaX)
    return dstMat
}

fun Mat.canny(
    dstMat: Mat,
    threshold1: Double = 20.toDouble(),
    threshold2: Double = 255.toDouble()
): Mat {
    Imgproc.Canny(this, dstMat, threshold1, threshold2)
    return dstMat
}

fun Mat.threshold(
    dstMat: Mat,
    thresh: Double = 50.toDouble(),
    maxVal: Double = 255.toDouble(),
    type: Int = Imgproc.THRESH_BINARY
): Mat {
    Imgproc.threshold(this, dstMat, thresh, maxVal, type)
    return dstMat
}

fun Mat.adaptiveThreshold(
    dstMat: Mat,
    maxValue: Double = 255.toDouble(),
    adaptiveMethod: Int = Imgproc.ADAPTIVE_THRESH_MEAN_C,
    thresholdType: Int = Imgproc.THRESH_BINARY, blockSize: Int = 11,
    c: Double = 12.toDouble()
) : Mat {
    Imgproc.adaptiveThreshold(this, dstMat, maxValue, adaptiveMethod, thresholdType, blockSize, c)
    return dstMat
}

fun Mat.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), config)
    Utils.matToBitmap(this, bitmap)
    return bitmap
}

fun Mat.isGrayscale() = this.type() == CvType.CV_8U
