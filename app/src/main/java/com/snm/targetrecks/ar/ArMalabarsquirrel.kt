package com.snm.targetrecks.ar

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.ar.core.Config
import com.snm.targetrecks.ArActivity
import com.snm.targetrecks.R
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.node.VideoNode

class ArMalabarsquirrel : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode
    private lateinit var videoNode: VideoNode
    private lateinit var mediaPlayer:MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar_antelope)

        sceneView = findViewById<ArSceneView?>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.ad)

        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        videoNode = VideoNode(sceneView.engine, scaleToUnits = 0.7f, centerOrigin = Position(y=-4f), glbFileLocation = "malabarsquirrel.glb", player = mediaPlayer, onLoaded = {_,_ ->
            mediaPlayer.start()
        })

        modelNode = ArModelNode(sceneView.engine,PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "",
                scaleToUnits = 0.2f

            )
            {
                sceneView.planeRenderer.isVisible = true
                val materialInstance = it.materialInstances[0]
            }
            onAnchorChanged = {
                placeButton.isGone = it != null
            }

        }
        sceneView.addChild(modelNode)
        modelNode.addChild(videoNode)

    }

    private fun placeModel(){
        modelNode.anchor()

        sceneView.planeRenderer.isVisible = false

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onBackPressed() {
        // Create an Intent to navigate back to the previous activity
        val intent = Intent(this, ArActivity::class.java)
        // Set the appropriate flags to clear the back stack
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        // Start the activity
        startActivity(intent)
        // Finish the current activity
        finish()
        super.onBackPressed()
    }
}