package nz.atomic.skadi.extensions

import android.graphics.Bitmap
import org.opencv.android.Utils
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

/**
 * Extension functions for [Mat].
 */
fun Mat.copy(): Mat {
    val clone = Mat(height(), width(), type())
    this.copyTo(clone)
    return clone
}

fun Mat.toGray(): Mat {
    val grayMat = Mat(height(), width(), CvType.CV_8U)
    Imgproc.cvtColor(this, grayMat, Imgproc.COLOR_RGB2GRAY)
    return grayMat
}

fun Mat.transpose(): Mat {
    val dstMat = Mat(width(), height(), type())
    Core.transpose(this, dstMat)
    return dstMat
}

fun Mat.flip(): Mat {
    val dstMat = copy()
    Core.flip(this, dstMat, 1)
    return dstMat
}

fun Mat.resize(size: Size): Mat {
    val resized = Mat(size.width.toInt(), size.height.toInt(), this.type())
    Imgproc.resize(this, resized, size)
    return resized
}

fun Mat.gaussianBlur(kSize: Size = Size(125.toDouble(), 125.toDouble()), sigmaX:Double = 0.toDouble()): Mat {
    val dstMat = copy()
    Imgproc.GaussianBlur(this, dstMat, kSize, sigmaX)
    return dstMat
}

fun Mat.canny(threshold1: Double = 20.toDouble(), threshold2: Double = 255.toDouble()): Mat {
    val srcMat = if (this.isGrayscale()) this else this.toGray()
    val dstMat = srcMat.copy()
    Imgproc.Canny(srcMat, dstMat, threshold1, threshold2)
    return dstMat
}

fun Mat.threshold(thresh: Double = 50.toDouble(), maxVal: Double = 255.toDouble(), type:Int = Imgproc.THRESH_BINARY): Mat {
    val srcMat = if (this.isGrayscale()) this else this.toGray()
    val dstMat = srcMat.copy()
    Imgproc.threshold(srcMat, dstMat, thresh, maxVal, type)
    return dstMat
}

fun Mat.adaptiveThreshold(maxValue: Double = 255.toDouble(), adaptiveMethod: Int = Imgproc.ADAPTIVE_THRESH_MEAN_C,
                          thresholdType: Int = Imgproc.THRESH_BINARY, blockSize: Int = 11, c: Double = 12.toDouble()) : Mat {
    val srcMat = if (this.isGrayscale()) this else this.toGray()
    val dstMat = srcMat.copy()
    Imgproc.adaptiveThreshold(srcMat, dstMat, maxValue, adaptiveMethod, thresholdType, blockSize, c)
    return dstMat
}

fun Mat.toBitmap(config: Bitmap.Config = Bitmap.Config.ARGB_8888): Bitmap {
    val bitmap = Bitmap.createBitmap(this.cols(), this.rows(), config)
    Utils.matToBitmap(this, bitmap)
    return bitmap
}

fun Mat.isGrayscale() = this.type() == CvType.CV_8U
