package nz.atomic.skadi.extensions.mat

import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

fun Mat.rgbToGray(dstMat: Mat): Mat {
    Imgproc.cvtColor(this, dstMat, Imgproc.COLOR_RGB2GRAY)
    return dstMat
}

/**
 * OpenCV's HSV representation is: H[0, 179], S[0, 255], V[0, 255]
 */
fun Mat.rgbToHsv(dstMat: Mat): Mat {
    Imgproc.cvtColor(this, dstMat, Imgproc.COLOR_RGB2HSV)
    return dstMat
}

fun Mat.isGrayscale() = this.type() == CvType.CV_8U
