# SARIF adapters
## Project's goal
Convert any format into SARIF

## Adding a custom format
- Implement a subclass of `Adapter` and `AdapterProxy`
- Create a Service Provider Interface file `META-INF/services/org.cqfn.save.adapter.AdapterProxy`

## Running the application
Prerequisites: Java.

Build a jar file for the custom format and place it on the classpath.
Then write serialized representation of your data into app's stdin:
```bash
cat my-data.json | ./save-adapter
```