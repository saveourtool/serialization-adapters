plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    `maven-publish`
}

dependencies {
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.sarif4k)
    implementation(libs.save.plugin.warn)
}

application {
    mainClass.set("org.cqfn.save.adapter.RunnerKt")
}