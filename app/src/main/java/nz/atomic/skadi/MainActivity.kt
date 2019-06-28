package nz.atomic.skadi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.opencv.android.InstallCallbackInterface
import org.opencv.android.LoaderCallbackInterface
import org.opencv.android.LoaderCallbackInterface.*
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, object : LoaderCallbackInterface {
            override fun onManagerConnected(status: Int) {
                when(status) {
                    SUCCESS -> Toast.makeText(this@MainActivity,
                        "OpenCV loaded successfully!", Toast.LENGTH_LONG).show()
                    INIT_FAILED -> Toast.makeText(this@MainActivity,
                        "OpenCV initialisation failed :(", Toast.LENGTH_LONG).show()
                }
            }

            override fun onPackageInstall(operation: Int, callback: InstallCallbackInterface?) {}
        })
    }

}
