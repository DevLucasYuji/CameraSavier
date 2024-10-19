plugins {
    id("com.ujizin.android-library")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().requiredVersion
    }
}

dependencies {
    implementation(platform(libs.findLibrary("compose-bom").get()))
    implementation(libs.findBundle("compose").get())
    debugImplementation(libs.findBundle("compose-debug").get())
    androidTestImplementation(libs.findLibrary("compose-android-test").get())
    implementation(libs.findLibrary("hilt-compose").get())

    implementation(libs.findLibrary("android-material").get())
    implementation(libs.findBundle("androidx").get())
}
