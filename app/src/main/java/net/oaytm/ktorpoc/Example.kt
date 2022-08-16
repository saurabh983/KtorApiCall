package net.oaytm.ktorpoc

import kotlinx.serialization.Serializable

@Serializable
data class Example(
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String,
)
