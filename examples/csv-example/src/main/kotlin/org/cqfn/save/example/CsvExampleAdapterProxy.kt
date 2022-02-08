package org.cqfn.save.example

import io.github.detekt.sarif4k.ArtifactLocation
import io.github.detekt.sarif4k.Location
import io.github.detekt.sarif4k.Message
import io.github.detekt.sarif4k.PhysicalLocation
import io.github.detekt.sarif4k.Result
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.csv.Csv
import org.cqfn.save.adapter.AdapterProxy
import org.cqfn.save.adapter.SarifAdapter

@OptIn(ExperimentalSerializationApi::class)
class CsvExampleAdapterProxy : AdapterProxy {
    override fun adapter() = SarifAdapter<List<Example>>(
        decoder = Csv {
            ignoreUnknownColumns = true
            hasHeaderRecord = true
            // this one is for running on Windows
            recordSeparator = "\r\n"
        },
    ) { exampleList ->
        exampleList.map {
            Result(
                message = Message(text = it.smell),
                locations = listOf(
                    Location(
                        physicalLocation = PhysicalLocation(
                            artifactLocation = ArtifactLocation(
                                uri = it.path
                            )
                        )
                    )
                )
            )
        }
    }
}
