plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle.api.plugin)
    implementation(libs.kfmt.plugin)
    implementation(libs.spotless.plugin.gradle)
    implementation(libs.hilt.plugin)
    implementation(libs.ksp.plugin)
}
