package org.cqfn.save.example

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Example(val engine: String, val filePath: String, val className: String, val entityType: String, val entityName: String,
                   val badSmellType: String, val message: String, val refactoring: List<Statements>)

@Serializable
data class Statements(val lineColumnList: List<LineColumn>)

@Serializable
data class LineColumn(
    @SerialName("start_line")
    val startLine : Long,

    @SerialName("start_column")
    val startColumn : Long,

    @SerialName("end_line")
    val endLine : Long,

    @SerialName("end_column")
    val endColumn : Long,
)