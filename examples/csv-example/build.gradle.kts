plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

//kotlin {
//    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
//}

dependencies {
    implementation("org.cqfn.save:save-adapters:0.1.0-SNAPSHOT")
    implementation("de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")
}
