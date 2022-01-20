plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.cqfn.save:save-adapters:0.1.0-SNAPSHOT")
}
