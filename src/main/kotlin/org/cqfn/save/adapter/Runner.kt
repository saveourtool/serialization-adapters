package org.cqfn.save.adapter

import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ServiceLoader

fun main(args: Array<String>) {
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
                InputStreamReader(System.`in`),
                OutputStreamWriter(System.`out`),
            )
        }
}
