package com.snm.targetrecks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.snm.targetrecks.ar.ArBustard
import com.snm.targetrecks.ar.ArMalabarsquirrel
import com.snm.targetrecks.ar.ArRedpanda
import com.snm.targetrecks.ar.ArSnowleopard

class ArActivity : AppCompatActivity() {
    private var Btn1: ImageButton? = null
    private var Btn2: ImageButton? = null
    private var Btn3: ImageButton? = null
    private var Btn4: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
        Btn1 = findViewById<ImageButton>(R.id.Btn1)
        Btn2 = findViewById<ImageButton>(R.id.Btn2)
        Btn3 = findViewById<ImageButton>(R.id.Btn3)
        Btn4 = findViewById<ImageButton>(R.id.Btn4)

        Btn1?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ArActivity, ArRedpanda::class.java)
            startActivity(intent)
            finish()
        })

        Btn2?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ArActivity, ArMalabarsquirrel::class.java)
            startActivity(intent)
            finish()
        })

        Btn3?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ArActivity, ArSnowleopard::class.java)
            startActivity(intent)
            finish()
        })

        Btn4?.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this@ArActivity, ArSnowleopard::class.java)
            startActivity(intent)
            finish()
        })
    }
    override fun onBackPressed() {
        // Create an Intent to navigate back to the previous activity
        val intent = Intent(this, InterfaceActivity::class.java)
        // Set the appropriate flags to clear the back stack
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        // Start the activity
        startActivity(intent)
        // Finish the current activity
        finish()
        super.onBackPressed()
    }
}