plugins {
    id("com.ujizin.android-application")
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.google.crashlytics.get().pluginId)
}

android { namespace = "com.ujizin.leafy" }

dependencies {
    implementation(projects.features.home)
    implementation(projects.features.search)
    implementation(projects.features.alarm)
    implementation(projects.features.camera)
    implementation(projects.features.publish)
    implementation(projects.features.about)
    implementation(projects.features.preferences)
    implementation(projects.features.tasks)
    implementation(projects.features.plant)

    implementation(projects.domain)
    implementation(projects.core.local)

    implementation(projects.core.ui)
    implementation(projects.core.themes)
    implementation(projects.core.navigation)
}
