package com.snm.targetrecks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*

class Community : AppCompatActivity() {

    private lateinit var editTextDescription: EditText
    private lateinit var imageViewMedia: ImageView
    private lateinit var postsLayout: LinearLayout
    private var selectedImageUri: Uri? = null

    private val firestoreDB = FirebaseFirestore.getInstance()
    private val postsCollection = firestoreDB.collection("Posts")
    private val storageRef = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        editTextDescription = findViewById(R.id.edit_text_description)
        imageViewMedia = findViewById(R.id.image_view_media)
        postsLayout = findViewById(R.id.posts_layout)

        findViewById<Button>(R.id.button_post).setOnClickListener {
            val userId = "user_id" // Replace with actual user ID
            val description = editTextDescription.text.toString()
            selectedImageUri?.let { uploadImageToFirebase(userId, description, it) }
        }



        // Retrieve posts when the activity starts
        retrievePostsFromFirestore()
    }

    fun loadMedia(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                imageViewMedia.setImageURI(imageUri)
                imageViewMedia.visibility = View.VISIBLE
                selectedImageUri = imageUri
            }
        }
    }

    private fun uploadImageToFirebase(userId: String, description: String, imageUri: Uri) {
        val imageName = "image_" + UUID.randomUUID().toString() + ".jpg"
        val imageRef = storageRef.child("images/$imageName")
        val uploadTask = imageRef.putFile(imageUri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                val imageUrl = downloadUri.toString()
                savePostToFirestore(userId, imageUrl, description)
            } else {
                // Handle unsuccessful uploads
            }
        }
    }

    private fun savePostToFirestore(userId: String, imageUrl: String, description: String) {
        val postMap = hashMapOf(
            "userId" to userId,
            "imageUrl" to imageUrl,
            "description" to description,
            "timestamp" to Calendar.getInstance().timeInMillis
        )

        postsCollection.add(postMap)
            .addOnSuccessListener { documentReference ->
                editTextDescription.text.clear()
                imageViewMedia.setImageResource(android.R.color.transparent)
                imageViewMedia.visibility = View.GONE
                selectedImageUri = null
                retrievePostsFromFirestore() // After posting, refresh the posts
            }
            .addOnFailureListener { e ->
                // Handle errors
                Log.e(TAG, "Error adding post to Firestore", e)
            }
    }

    private fun retrievePostsFromFirestore() {
        postsLayout.removeAllViews() // Clear existing posts before reloading
        postsCollection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val postId = document.id
                    val userId = document.getString("userId")
                    val description = document.getString("description")
                    val imageUrl = document.getString("imageUrl")

                    if (!imageUrl.isNullOrEmpty()) { // Check if imageUrl is not empty or null
                        val postView = layoutInflater.inflate(R.layout.post_item, null)
                        val postTextView = postView.findViewById<TextView>(R.id.post_text_view)
                        val postImageView = postView.findViewById<ImageView>(R.id.post_image_view)
                        val deleteButton = postView.findViewById<Button>(R.id.button_delete_post)

                        postTextView.text = "User ID: $userId\nDescription: $description"
                        Picasso.get().load(imageUrl).into(postImageView)

                        deleteButton.setOnClickListener {
                            deletePost(postId)
                        }

                        postsLayout.addView(postView)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error retrieving posts", exception)
            }
    }

    private fun deletePost(postId: String) {
        postsCollection.document(postId)
            .delete()
            .addOnSuccessListener {
                retrievePostsFromFirestore() // After deletion, refresh the posts
            }
            .addOnFailureListener { e ->
                // Handle errors
                Log.e(TAG, "Error deleting post", e)
            }
    }

    companion object {
        private const val TAG = "CommunityActivity"
        private const val REQUEST_IMAGE_PICK = 100
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
