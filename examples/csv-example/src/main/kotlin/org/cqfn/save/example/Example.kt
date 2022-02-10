package org.cqfn.save.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Example(
    val smell: String,
    val path: String,

    @SerialName("start_line")
    val startLine: Long,
)
