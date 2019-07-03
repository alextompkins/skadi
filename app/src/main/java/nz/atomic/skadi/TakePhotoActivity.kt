package nz.atomic.skadi

import android.os.Bundle
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import nz.atomic.skadi.extensions.canny
import nz.atomic.skadi.extensions.flip
import nz.atomic.skadi.extensions.resize
import nz.atomic.skadi.extensions.transpose
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.JavaCameraView
import org.opencv.core.Mat

class TakePhotoActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {

    private lateinit var cameraView: JavaCameraView

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

    override fun onCameraViewStarted(width: Int, height: Int) {}

    override fun onCameraViewStopped() {}

    override fun onCameraFrame(inputFrame: CameraBridgeViewBase.CvCameraViewFrame): Mat {
        val inputGray = inputFrame.gray()
        return inputGray
            .transpose()
            .flip()
            .canny(50.0, 100.0)
            .resize(inputGray.size())
    }
}
