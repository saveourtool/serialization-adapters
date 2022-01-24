# Kotlin Serialization Adapters
Adapters and converters for different serialization types. For example: from JSON to CSV, from TOML to YML, from CSV to SARIF, etc.
You simply need to provide the mapping **schema** and kotlinx **serialization library** for it.

## Project's initial idea
Initially, we wanted simply to create a small tool that will be able to convert custom formats that have a support in [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization/tree/master/formats) to [SARIF](https://docs.oasis-open.org/sarif/sarif/v2.0/sarif-v2.0.html) format.

Later we thought that it can be useful for other developers to have a common adapter for different serialization types.

## Adding a custom format
If you would like to use this library as cli-app:
1) Implement `@Serializable` data class that will be used for deserialization of an input
2) Implement a subclass of `AdapterProxy`. Preferable way is to use `SarifAdapter` factory method, but if you need more granular control of functionality, you can extend classes directly.
3) Create a Service Provider Interface file `META-INF/services/org.cqfn.save.adapter.AdapterProxy`

In case you plan to have an adapter as a library:
1) Implement `@Serializable` data classes that will be used for deserialization and serialization of an input
2) ??? (set the INPUT and OUTPUT formats and proper libraries will be automatically added by the adapter)
3) Provide these classes to the `KxSerializationAdapter`

## Examples of usage
We have prepared some examples that can be found [here](https://github.com/analysis-dev/serialization-adapters/tree/main/examples).

## How it works:
<img src="/flow.png" width="700px"/>


## Running the cli application
Prerequisites: Java.

Build a jar file for the custom format and place it on the classpath.
Then write serialized representation of your data into app's stdin:
```bash
cat my-data.json | java -cp * org.cqfn.save.adapter.RunnerKt
```
