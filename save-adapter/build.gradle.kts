plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.core)
}

java {
    withJavadocJar()
    withSourcesJar()
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
