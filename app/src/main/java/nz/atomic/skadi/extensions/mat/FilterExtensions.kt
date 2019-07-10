package nz.atomic.skadi.extensions.mat

import org.opencv.core.Mat
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

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
