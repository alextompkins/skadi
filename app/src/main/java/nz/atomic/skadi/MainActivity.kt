package nz.atomic.skadi

import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import nz.atomic.skadi.extensions.canny
import nz.atomic.skadi.extensions.flip
import nz.atomic.skadi.extensions.resize
import nz.atomic.skadi.extensions.transpose
import org.opencv.android.CameraBridgeViewBase
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.LoaderCallbackInterface.INIT_FAILED
import org.opencv.android.LoaderCallbackInterface.SUCCESS
import org.opencv.android.OpenCVLoader
import org.opencv.core.Mat

class MainActivity : AppCompatActivity(), CameraBridgeViewBase.CvCameraViewListener2 {

    private lateinit var cameraView: CameraBridgeViewBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OpenCVLoader.initDebug()
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, object : LoaderCallbackInterface {
            override fun onManagerConnected(status: Int) {
                when(status) {
                    SUCCESS -> Log.i("OPENCV", "OpenCV loaded successfully!")
                    INIT_FAILED -> Log.e("OPENCV", "OpenCV initialisation failed :(")
                    else -> Log.e("OPENCV", "Misc error with initialisation: $status")
                }
            }

            override fun onPackageInstall(operation: Int, callback: InstallCallbackInterface?) {
                Log.i("OPENCV", "Installing OpenCV package...")
            }
        })

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
