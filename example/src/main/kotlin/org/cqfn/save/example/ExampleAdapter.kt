package org.cqfn.save.example

import io.github.detekt.sarif4k.ArtifactLocation
import io.github.detekt.sarif4k.Location
import io.github.detekt.sarif4k.Message
import io.github.detekt.sarif4k.PhysicalLocation
import io.github.detekt.sarif4k.Result
import io.github.detekt.sarif4k.Tool
import io.github.detekt.sarif4k.ToolComponent
import kotlinx.serialization.json.Json
import org.cqfn.save.adapter.Adapter
import kotlin.reflect.typeOf

class ExampleAdapter : Adapter<List<Example>>(
    type = typeOf<List<Example>>(),
    decoder = Json,
    tool = Tool(driver = ToolComponent(name = "my-awesome-tool")),
) {
    override fun mapToSarifResults(t: List<Example>): List<Result> {
        return t.map {
            Result(
                message = Message(text = it.message),
                locations = listOf(
                    Location(
                        physicalLocation = PhysicalLocation(
                            artifactLocation = ArtifactLocation(
                                uri = it.name
                            )
                        )
                    )
                )
            )
        }
    }
}
