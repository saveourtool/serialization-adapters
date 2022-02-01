package org.cqfn.save.adapter

import java.io.*
import java.util.ServiceLoader

fun main(args: Array<String>) {
    val input: InputStream = if (args.isEmpty()) System.`in` else (FileInputStream(args[0]))
    val output: OutputStream = if (args.size <= 1) System.`out` else(FileOutputStream(args[1]))

    ServiceLoader.load(AdapterProxy::class.java)
        .filterNotNull()
        .map {
            it.adapter()
        }
        .run {
            if (isEmpty()) println("No service providers have been found")
            this
        }
        .forEach { adapter ->
            adapter.convertAndStore(
                InputStreamReader(input),
                OutputStreamWriter(output),
            )
        }
}
