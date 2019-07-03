package nz.atomic.skadi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.opencv.android.OpenCVLoader

class MainActivity : AppCompatActivity() {

    init {
        // Load OpenCV library
        val loaderSuccess = OpenCVLoader.initDebug()
        if (loaderSuccess) {
            Log.i("OpenCV", "OpenCV loaded successfully!")
        } else {
            Log.e("OpenCV", "OpenCV initialisation failed :(")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val takePhotoBtn: Button = findViewById(R.id.takePhotoBtn)
        takePhotoBtn.setOnClickListener {
            val intent = Intent(this, TakePhotoActivity::class.java)
            startActivity(intent)
        }
    }

}
