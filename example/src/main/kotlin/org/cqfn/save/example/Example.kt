package org.cqfn.save.example

import kotlinx.serialization.Serializable

@Serializable
data class Example(val message: String, val name: String)
