package com.snm.targetrecks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class GalleryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GalleryAdapter
    private val mediaList: MutableList<MediaItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = GalleryAdapter(mediaList)
        recyclerView.adapter = adapter

        // Fetch media items from Firebase Storage
        fetchMediaItems()
    }

    private fun fetchMediaItems() {
        val storage = Firebase.storage
        val storageRef = storage.reference.child("uploads")

        storageRef.listAll().addOnSuccessListener { result ->
            result.items.forEach { item ->
                // Get download URL for each media item
                item.downloadUrl.addOnSuccessListener { uri ->
                    mediaList.add(MediaItem(uri.toString(), item.name))
                    adapter.notifyDataSetChanged()
                }
            }
        }.addOnFailureListener { e ->
            // Handle any errors
            // Log.e(TAG, "Error fetching media items: ${e.message}")
        }
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


