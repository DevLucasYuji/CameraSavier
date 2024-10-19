import com.ujizin.leafy.plugins.utils.configAndroid

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("com.ujizin.code-quality")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    configAndroid()
    kotlinOptions {
        jvmTarget = "17"
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    if (project.path != ":domain") {
        implementation(project(":domain"))
    }

    implementation(platform(libs.findLibrary("firebase-bom").get()))
    implementation(libs.findLibrary("firebase-crashlytics-ktx").get())
    implementation(libs.findLibrary("firebase-analytics-ktx").get())


    implementation(libs.findLibrary("hilt").get())
    ksp(libs.findLibrary("hilt-compiler").get())

    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("mockk").get())
}
