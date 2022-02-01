plugins {
    alias(libs.plugins.reckon)
    alias(libs.plugins.publish)
}

reckon {
    scopeFromProp()
    snapshotFromProp()
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
