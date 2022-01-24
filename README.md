# SARIF adapters
## Project's goal
Convert any format into SARIF (or any other format too).

## Building the project
```bash
./gradlew shadowJar
```
will produce a file `build/libs/save-adapters-<version>-all.jar`

## Building the examples
First, build main project, because examples depend on it:
```bash
./gradlew publishAllPublicationsToMavenLocalRepository
```
Then, navigate into examples' directory and run `gradle jar`.

## Adding a custom format
- Implement a subclass of `AdapterProxy`. Preferable way is to use `SarifAdapter` factory method, but if you need more granular control of functionality, you can extend classes directly.
- Create a Service Provider Interface file `META-INF/services/org.cqfn.save.adapter.AdapterProxy`

## Running the application
Prerequisites: Java.

Build a jar file for the custom format and place it on the classpath, along with `save-adapters-<version>-all.jar`.
Then write serialized representation of your data into app's stdin:
```bash
cat my-data.json | java -cp * org.cqfn.save.adapter.RunnerKt
```
