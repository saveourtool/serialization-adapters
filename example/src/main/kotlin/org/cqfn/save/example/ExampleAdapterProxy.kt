package org.cqfn.save.example

import io.github.detekt.sarif4k.ArtifactLocation
import io.github.detekt.sarif4k.Location
import io.github.detekt.sarif4k.Message
import io.github.detekt.sarif4k.PhysicalLocation
import io.github.detekt.sarif4k.Result
import kotlinx.serialization.json.Json
import org.cqfn.save.adapter.AdapterProxy
import org.cqfn.save.adapter.SarifAdapter

class ExampleAdapterProxy : AdapterProxy {
    override fun adapter() = SarifAdapter<List<Example>>(
        decoder = Json,
    ) { exampleList ->
        exampleList.map {
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
