package org.cqfn.save.adapter

import io.github.detekt.sarif4k.Result
import io.github.detekt.sarif4k.Run
import io.github.detekt.sarif4k.SarifSchema210
import io.github.detekt.sarif4k.Tool
import io.github.detekt.sarif4k.Version
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

abstract class SarifAdapter<IN: Any>(
    type: KType,
    decoder: StringFormat,
    protected val tool: Tool,
) : KxSerializationAdapter<IN, SarifSchema210>(
    inType = type,
    decoder = decoder,
    outType = typeOf<SarifSchema210>(),
    encoder = Json,
) {
    abstract fun mapToSarifResults(t: IN): List<Result>

    open fun sarifBuilder(results: List<Result>) = SarifSchema210(
        version = Version.The210,
        runs = listOf(
            Run(
                tool = tool,
                results = results
            )
        )
    )

    override fun map(t: IN): SarifSchema210 {
        return sarifBuilder(
            results = mapToSarifResults(t)
        )
    }
}
