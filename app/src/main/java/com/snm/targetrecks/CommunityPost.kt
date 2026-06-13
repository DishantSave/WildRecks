package com.snm.targetrecks

import com.google.firebase.firestore.PropertyName

data class CommunityPost(
    @get:PropertyName("userId")
    @set:PropertyName("userId")
    var userId: String = "",

    @get:PropertyName("imageUrl")
    @set:PropertyName("imageUrl")
    var imageUrl: String = "",

    @get:PropertyName("description")
    @set:PropertyName("description")
    var description: String = "",

    @get:PropertyName("timestamp")
    @set:PropertyName("timestamp")
    var timestamp: Long = 0
)
