plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow")
}

dependencies {
    implementation("org.cqfn.save:save-adapters:0.1.0")
    implementation("de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")
}

tasks {
    shadowJar {
        dependencies {
            exclude(dependency("org.cqfn.save:save-adapters"))
        }
    }
}

tasks.named("build") { finalizedBy("shadowJar") }