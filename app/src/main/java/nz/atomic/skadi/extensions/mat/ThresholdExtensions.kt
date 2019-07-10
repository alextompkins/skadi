package nz.atomic.skadi.extensions.mat

import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc

fun Mat.threshold(
    dstMat: Mat,
    thresh: Double = 50.toDouble(),
    maxVal: Double = 255.toDouble(),
    type: Int = Imgproc.THRESH_BINARY
): Mat {
    Imgproc.threshold(this, dstMat, thresh, maxVal, type)
    return dstMat
}

fun Mat.inRange(
    dstMat: Mat,
    lowThreshold: Scalar,
    highThreshold: Scalar
): Mat {
    Core.inRange(this, lowThreshold, highThreshold, dstMat)
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
