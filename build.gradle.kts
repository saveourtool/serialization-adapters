plugins {
    alias(libs.plugins.kotlin.jvm)
    application
    alias(libs.plugins.publish)
    `maven-publish`
    signing
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

signing {
    useInMemoryPgpKeys(findProperty("signingKey") as String?, findProperty("signingPassword") as String?)
    sign(*extensions.getByType<PublishingExtension>().publications.toTypedArray())
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://oss.sonatype.org/content/repositories/snapshots/"))
            username.set(findProperty("sonatypeUsername") as String?)
            password.set(findProperty("sonatypePassword") as String?)
        }
    }
}
