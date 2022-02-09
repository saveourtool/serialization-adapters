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
        exampleList.map {
            Result(
                message = Message(text = it.message),
                locations = listOf(
                    Location(
                        physicalLocation = PhysicalLocation(
                            artifactLocation = ArtifactLocation(
                                uri = it.filePath
                            )
                        ),
                        annotations = it.refactoring.map {
                            val location = it.statements.single()
                            Region(
                                startLine = location.startLine,
                                startColumn = location.startColumn,
                                endLine = location.endLine,
                                endColumn = location.endColumn
                            )
                        }
                    )
                ),
                properties = PropertyBag(
                    listOf(
                        "engine: ${it.engine}",
                        "className: ${it.className}",
                        "entityType: ${it.entityType}",
                        "entityName: ${it.entityName}",
                        "startLine: ${it.startLine}",
                    )
                ),
                ruleID = it.badSmellType,
            )
        }
    }
}
