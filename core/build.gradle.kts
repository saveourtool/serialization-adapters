plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
    signing
}

dependencies {
    api(libs.kotlinx.serialization.core)
    api(libs.sarif4k)
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(project.description ?: project.name)
                url.set("https://github.com/analysis-dev/serialization-adapters")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("petertrr")
                        name.set("Petr Trifanov")
                        email.set("peter.trifanov@mail.ru")
                    }
                    developer {
                        id.set("akuleshov7")
                        name.set("Andrey Kuleshov")
                        email.set("andrewkuleshov7@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/cqfn/save")
                    connection.set("scm:git:git://github.com/cqfn/save.git")
                }
            }
        }
    }
}

signing {
    val gpgSecret = System.getenv("GPG_SEC")
    if (gpgSecret != null) {
        useInMemoryPgpKeys(gpgSecret, System.getenv("GPG_PASSWORD"))
        sign(*extensions.getByType<PublishingExtension>().publications.toTypedArray())
    }
}
