package org.cqfn.save.adapter

import io.github.detekt.sarif4k.Result
import io.github.detekt.sarif4k.Run
import io.github.detekt.sarif4k.SarifSchema210
import io.github.detekt.sarif4k.Tool
import io.github.detekt.sarif4k.Version
import kotlinx.serialization.StringFormat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.reflect.KType

abstract class Adapter<T : Any>(
    private val type: KType,
    private val decoder: StringFormat,
    private val tool: Tool,
) {

    /**
     * [t] is of the same type as [type], but needs to be cast manually
     */
    abstract fun mapToSarifResults(t: T): List<Result>

    open fun sarifBuilder(results: List<Result>) = SarifSchema210(
        version = Version.The210,
        runs = listOf(
            Run(
                tool = tool,
                results = results
            )
        )
    )

    fun convertAndStore(input: InputStreamReader, output: OutputStreamWriter) {
        val sarif = convert(input)
        output.use {
            it.write(Json.encodeToString(sarif))
            it.write("\n")
        }
    }

    private fun convert(input: InputStreamReader): SarifSchema210 {
        val decodedObject = input.readText().let {
            decoder.decodeFromString(serializer(type), it)
        } as T
        return sarifBuilder(
            results = mapToSarifResults(decodedObject)
        )
    }
}
