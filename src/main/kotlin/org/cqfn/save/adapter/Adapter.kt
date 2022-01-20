package org.cqfn.save.adapter

import java.io.InputStreamReader
import java.io.OutputStreamWriter

abstract class Adapter<IN : Any, OUT : Any> {
    protected abstract fun read(input: InputStreamReader): IN

    protected abstract fun OutputStreamWriter.write(out: OUT)

    protected abstract fun map(t: IN): OUT

    fun convertAndStore(input: InputStreamReader, output: OutputStreamWriter) {
        output.write(
            map(
                read(input)
            )
        )
    }
}
