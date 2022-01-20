plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    `maven-publish`
}

dependencies {
    api(libs.kotlinx.serialization.core)
    api(libs.sarif4k)
}

application {
    mainClass.set("org.cqfn.save.adapter.RunnerKt")
}