import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    dependencies {
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${libs.versions.detekt}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.kotlin}")
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics)
    }
}

plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.detekt)
}

detekt {
    toolVersion = rootProject.libs.versions.detekt.toString()
    config = files("config/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}

apply(from = "config-properties.gradle.kts")