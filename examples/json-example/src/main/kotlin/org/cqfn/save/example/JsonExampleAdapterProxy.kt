package org.cqfn.save.example

import io.github.detekt.sarif4k.ArtifactLocation
import io.github.detekt.sarif4k.Location
import io.github.detekt.sarif4k.Message
import io.github.detekt.sarif4k.PhysicalLocation
import io.github.detekt.sarif4k.PropertyBag
import io.github.detekt.sarif4k.Region
import io.github.detekt.sarif4k.Result
import kotlinx.serialization.json.Json
import org.cqfn.save.adapter.AdapterProxy
import org.cqfn.save.adapter.SarifAdapter

class JsonExampleAdapterProxy : AdapterProxy {
    override fun adapter() = SarifAdapter<List<Example>>(
        decoder = Json {
           ignoreUnknownKeys = true
        },
    ) { exampleList ->
        exampleList.flatMap { example ->
            example.refactoring.map {
                val location = it.statements.single()
                Result(
                    message = Message(text = example.message),
                    locations = listOf(
                        Location(
                            physicalLocation = PhysicalLocation(
                                artifactLocation = ArtifactLocation(
                                    uri = example.filePath
                                ),
                                region = Region(startLine = location.startLine, startColumn = location.startColumn)
                            )
                        )
                    )
                )
            }
        }
    }
}
