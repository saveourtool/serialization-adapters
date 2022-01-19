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

class ExampleAdapter : Adapter<Example>(
    type = Example::class,
    decoder = Json,
    tool = Tool(driver = ToolComponent(name = "my-awesome-tool")),
) {
    override fun mapToSarifResult(t: Example): Result {
        return Result(
            message = Message(text = t.message),
            locations = listOf(Location(physicalLocation = PhysicalLocation(artifactLocation = ArtifactLocation(uri = t.name))))
        )
    }
}
