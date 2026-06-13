package com.snm.targetrecks

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class CameraActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_VIDEO_CAPTURE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check camera permission and dispatch camera intent
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_IMAGE_CAPTURE
            )
        } else {
            // Display dialog to choose between image and video capture
            showCaptureDialog()
        }
    }

    private fun showCaptureDialog() {
        val items = arrayOf<CharSequence>("Capture Image", "Capture Video", "Back")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an action")
        builder.setItems(items) { _, item ->
            when (item) {
                0 -> dispatchTakePictureIntent()
                1 -> dispatchTakeVideoIntent()
                2 -> goBackAction()
            }
        }
        builder.setCancelable(false)
        builder.show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    private fun goBackAction() {
        val intent = Intent(this, InterfaceActivity::class.java)
        startActivity(intent)
        // Finish the current activity
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE || requestCode == REQUEST_VIDEO_CAPTURE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showCaptureDialog()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    uploadToFirebase(imageBitmap)
                }
                REQUEST_VIDEO_CAPTURE -> {
                    val videoUri: Uri? = data?.data
                    uploadToFirebase(videoUri)
                }
            }
        }
    }

    private fun uploadToFirebase(imageBitmap: Bitmap) {
        // Convert Bitmap to Uri
        val tempUri = getImageUri(imageBitmap)
        uploadToFirebase(tempUri)
    }

    private fun uploadToFirebase(fileUri: Uri?) {
        if (fileUri != null) {
            val storage = Firebase.storage
            val storageRef = storage.reference

            val fileName = "${System.currentTimeMillis()}${getFileExtension(fileUri)}"
            val fileRef = storageRef.child("uploads/$fileName")

            val uploadTask = fileRef.putFile(fileUri)

            uploadTask.addOnSuccessListener {
                Toast.makeText(this, "Upload successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, InterfaceActivity::class.java)
                startActivity(intent)
                // Finish the current activity
                finish()
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "File URI is null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageUri(inImage: Bitmap): Uri {
        val path = MediaStore.Images.Media.insertImage(contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    private fun getFileExtension(uri: Uri): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
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
