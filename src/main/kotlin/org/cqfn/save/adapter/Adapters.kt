package org.cqfn.save.adapter

import io.github.detekt.sarif4k.Result
import io.github.detekt.sarif4k.Tool
import io.github.detekt.sarif4k.ToolComponent
import kotlinx.serialization.StringFormat
import kotlin.reflect.typeOf

inline fun <reified IN : Any> SarifAdapter(
    decoder: StringFormat,
    tool: Tool = Tool(driver = ToolComponent(name = "N/A")),
    crossinline mapper: (IN) -> List<Result>,
) = object : SarifAdapter<IN>(
    type = typeOf<IN>(),
    decoder = decoder,
    tool = tool,
) {
    override fun mapToSarifResults(t: IN): List<Result> {
        return mapper(t)
    }
}
