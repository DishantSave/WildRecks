package com.snm.targetrecks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Specify the latitude and longitude of the location you want to open
        val latitude = 19.4739
        val longitude = 72.8582

        // Create a Uri object with the location coordinates
        val uri = Uri.parse("geo:$latitude,$longitude")

        // Create an Intent with action ACTION_VIEW and the Uri
        val intent = Intent(Intent.ACTION_VIEW, uri)

        // Set the package to Google Maps
        intent.setPackage("com.google.android.apps.maps")

        // Verify if there's a Google Maps app available to handle the Intent
        if (intent.resolveActivity(packageManager) != null) {
            // Start the Intent if a Google Maps app is available
            startActivity(intent)
        } else {
            // Handle the case where Google Maps app is not installed
            // You can open the location in a browser or display a message to the user
            // For example:
            // val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            // startActivity(browserIntent)
            // Or show a Toast message
            // Toast.makeText(this, "Google Maps app is not installed", Toast.LENGTH_SHORT).show()
        }

        // Finish the activity so that it doesn't remain in the back stack

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
