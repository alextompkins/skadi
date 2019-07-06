package nz.atomic.skadi

import android.os.Bundle
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import nz.atomic.skadi.extensions.canny
import org.opencv.android.CameraBridgeViewBase
import org.opencv.core.CvType
import org.opencv.core.Mat

class TakePhotoActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {

    private lateinit var cameraView: CameraBridgeViewBase
    private lateinit var frameOut: Mat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)

        cameraView = findViewById(R.id.cameraView)
        cameraView.visibility = SurfaceView.VISIBLE
        cameraView.setCvCameraViewListener(this)
    }

    override fun onStart() {
        super.onStart()
        cameraView.enableView()
    }

    override fun onStop() {
        super.onStop()
        cameraView.disableView()
    }

    override fun onCameraViewStarted(width: Int, height: Int) {
        frameOut = Mat(width, height, CvType.CV_8U)
    }

    override fun onCameraViewStopped() {
        frameOut.release()
    }

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        val frameIn = inputFrame.gray()

        return frameIn
            .canny(frameOut, 50.0, 100.0)
    }
}
