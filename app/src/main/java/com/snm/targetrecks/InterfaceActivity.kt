package com.snm.targetrecks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.codebyashish.autoimageslider.AutoImageSlider
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.ExceptionsClass
import com.codebyashish.autoimageslider.Models.ImageSlidesModel

class InterfaceActivity : AppCompatActivity() {
    private var targetBtn: ImageButton? = null
    private var Btn1: ImageButton? = null
    private var Btn2: ImageButton? = null
    private var Btn3: ImageButton? = null
    private var Btn4: ImageButton? = null
    private var Btn5: ImageButton? = null
    private var Btn6: ImageButton? = null
    private var Btn7: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface)
        targetBtn = findViewById<ImageButton>(R.id.targetBtn)
        Btn1 = findViewById<ImageButton>(R.id.Btn1)
        Btn2 = findViewById<ImageButton>(R.id.Btn2)
        Btn3 = findViewById<ImageButton>(R.id.Btn3)
        Btn4 = findViewById<ImageButton>(R.id.Btn4)
        Btn5 = findViewById<ImageButton>(R.id.Btn5)
        Btn6 = findViewById<ImageButton>(R.id.Btn6)
        Btn7 = findViewById<ImageButton>(R.id.Btn7)

        targetBtn?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InterfaceActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        Btn1?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InterfaceActivity, MapActivity::class.java)
            startActivity(intent)
            finish()
        })


        Btn2?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InterfaceActivity, ArActivity::class.java)
            startActivity(intent)
            finish()
        })

        Btn3?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@InterfaceActivity, SoundLib::class.java)
            startActivity(intent)
            finish()
        })

        Btn4?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@InterfaceActivity, Community::class.java)
            startActivity(intent)
            finish()
        })

        Btn5?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@InterfaceActivity, CameraActivity::class.java)
            startActivity(intent)
            finish()
        })

        Btn6?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@InterfaceActivity, GalleryActivity::class.java)
            startActivity(intent)
            finish()
        })

        Btn7?.setOnClickListener(View.OnClickListener {
            val url = "https://animalia.bio/endemic-lists/country/endemic-animals-of-india"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        })


    }
}