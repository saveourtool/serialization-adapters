# Adapter from CSV to SARIF
This example uses the module `de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv` for
deserialization of CSV. Class `CsvExampleAdapterProxy` is configured to convert the following data:
```text
message,name
Warning message 1,Location 1
Warning message 2,Location 2
```

Because this adapter requires additional runtime dependencies,
it's suggested to build a jar-with-dependencies file using `shadow` plugin.
The resulting `csv-adapter-example-all.jar` should then be placed on the classpath
when executing the main adapter runner.