package org.cqfn.save.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Example(
    val filePath: String,
    val badSmellType: String,
    val startLine: Long,
)
