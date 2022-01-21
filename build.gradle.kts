plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    alias(libs.plugins.publish)
    `maven-publish`
    alias(libs.plugins.shadow)
}

dependencies {
    api(libs.kotlinx.serialization.core)
    api(libs.sarif4k)
}

application {
    mainClass.set("org.cqfn.save.adapter.RunnerKt")
}

tasks {
    shadowJar {
        manifest {
            attributes(
                "Main-Class" to "org.cqfn.save.adapter.RunnerKt"
            )
        }
    }
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}
