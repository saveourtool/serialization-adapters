rootProject.name = "save-adapters"

include("examples:csv-example")
include("examples:json-example")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")
