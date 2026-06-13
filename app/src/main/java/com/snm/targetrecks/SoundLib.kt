package com.snm.targetrecks

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SoundLib : AppCompatActivity() {
    var img: ImageView? = null
    var img1: ImageView? = null
    var img2: ImageView? = null
    var img3: ImageView? = null
    var img4: ImageView? = null
    var img5: ImageView? = null
    var img6: ImageView? = null
    var img7: ImageView? = null
    var img8: ImageView? = null
    var img9: ImageView? = null
    var image1Displaying = true
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_lib)
        Toast.makeText(
            applicationContext,
            "Press the image to change the image.",
            Toast.LENGTH_LONG
        ).show()
        img = findViewById<ImageView>(R.id.img)
        img1 = findViewById<ImageView>(R.id.img1)
        img2 = findViewById<ImageView>(R.id.img2)
        img3 = findViewById<ImageView>(R.id.img3)
        img4 = findViewById<ImageView>(R.id.img4)
        img5 = findViewById<ImageView>(R.id.img5)
        img6 = findViewById<ImageView>(R.id.img6)
        img7 = findViewById<ImageView>(R.id.img7)
        img8 = findViewById<ImageView>(R.id.img8)
        img9 = findViewById<ImageView>(R.id.img9)

        // Play the mp3 when the button is pressed
        img?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.nilgiri_tahr)
            mediaPlayer = mp
            mp.start()
        })
        img1?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.red_panda)
            mediaPlayer = mp
            mp.start()
        })
        img2?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.great_ind_bustard)
            mediaPlayer = mp
            mp.start()
        })
        img3?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.malabar_squirrel)
            mediaPlayer = mp
            mp.start()
        })
        img4?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.pygmy_hog)
            mediaPlayer = mp
            mp.start()
        })
        img5?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release() // Release any previous MediaPlayer instance
            val mp = MediaPlayer.create(this@SoundLib, R.raw.liontailed_macaque)
            mediaPlayer = mp // Assign the new MediaPlayer instance to the class variable
            mp.start()
        })
        img6?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.black_buck)
            mediaPlayer = mp
            mp.start()
        })
        img7?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.snow_leopard)
            mediaPlayer = mp
            mp.start()
        })
        img8?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.monal)
            mediaPlayer = mp
            mp.start()
        })
        img9?.setOnClickListener(View.OnClickListener { v: View? ->
            mediaPlayer?.release()
            val mp = MediaPlayer.create(this@SoundLib, R.raw.indian_robin)
            mediaPlayer = mp
            mp.start()
        })
        img?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img?.setImageResource(R.drawable.nilgiritahr)
                    false
                } else {
                    img?.setImageResource(R.drawable.nilgiritahr1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img1?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img1?.setImageResource(R.drawable.redpandasl)
                    false
                } else {
                    img1?.setImageResource(R.drawable.redpandasl1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img2?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img2?.setImageResource(R.drawable.indianbustard)
                    false
                } else {
                    img2?.setImageResource(R.drawable.indianbustard1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img3?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img3?.setImageResource(R.drawable.malabarsqui)
                    false
                } else {
                    img3?.setImageResource(R.drawable.malabarsqui1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img4?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img4?.setImageResource(R.drawable.pygmyhog)
                    false
                } else {
                    img4?.setImageResource(R.drawable.pygmyhog1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img5?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img5?.setImageResource(R.drawable.macaque)
                    false
                } else {
                    img5?.setImageResource(R.drawable.macaque1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img6?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img6?.setImageResource(R.drawable.blackbuck)
                    false
                } else {
                    img6?.setImageResource(R.drawable.blackbuck1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img7?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img7?.setImageResource(R.drawable.snowleopardsl)
                    false
                } else {
                    img7?.setImageResource(R.drawable.snowleopardsl1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img8?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img8?.setImageResource(R.drawable.monal)
                    false
                } else {
                    img8?.setImageResource(R.drawable.monal1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
        img9?.setOnTouchListener(OnTouchListener setOnTouchListener@{ v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_UP) {
                image1Displaying = if (image1Displaying) {
                    img9?.setImageResource(R.drawable.robinsl)
                    false
                } else {
                    img9?.setImageResource(R.drawable.robinsl1)
                    true
                }
                v.performClick()
                return@setOnTouchListener true
            }
            false
        })
    }

    override fun onBackPressed() {

        mediaPlayer?.release()
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
