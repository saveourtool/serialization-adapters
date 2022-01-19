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
import kotlin.reflect.KClass

abstract class Adapter<T : Any>(
    private val type: KClass<T>,
    private val decoder: StringFormat,
    private val tool: Tool,
) {
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
            it.buffered().write(Json.encodeToString(sarif))
        }
    }

    private fun convert(input: InputStreamReader): SarifSchema210 {
        val decodedObject = input.readText().let {
            decoder.decodeFromString(serializer(type.java), it)
        }
        return sarifBuilder(
            results = mapToSarifResults(decodedObject as T)
        )
    }
}
