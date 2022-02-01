package org.cqfn.save.adapter

import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.reflect.KType

abstract class KxSerializationAdapter<IN: Any, OUT: Any>(
    private val inType: KType,
    private val decoder: StringFormat,
    private val outType: KType,
    private val encoder: StringFormat,
) : Adapter<IN, OUT>() {
    override fun read(input: InputStreamReader): IN {
        return input.use {
            decoder.decodeFromString(
                serializer(inType),
                it.readText(),
            ) as IN
        }
    }

    override fun OutputStreamWriter.write(out: OUT) {
        this.use {
            it.write(
                encoder.encodeToString(serializer(outType), out)
            )
        }
    }
}
