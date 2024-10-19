import com.ujizin.leafy.plugins.utils.configAndroid
import com.ujizin.leafy.plugins.utils.configApplication

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("com.ujizin.code-quality")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

apply(from = "$rootDir/config-properties.gradle.kts")

android {
    configApplication(project)
    configAndroid()
    kotlinOptions {
        jvmTarget = "17"
    }
    hilt {
        enableAggregatingTask = true
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().requiredVersion
    }
}

dependencies {
    implementation(platform(libs.findLibrary("firebase-bom").get()))
    implementation(libs.findLibrary("firebase-crashlytics-ktx").get())
    implementation(libs.findLibrary("firebase-analytics-ktx").get())

    implementation(libs.findLibrary("androidx-core-splashscreen").get())
    implementation(libs.findBundle("androidx").get())

    implementation(platform(libs.findLibrary("compose-bom").get()))
    implementation(libs.findBundle("compose").get())
    implementation(libs.findLibrary("hilt-compose").get())

    implementation(libs.findLibrary("hilt").get())
    ksp(libs.findLibrary("hilt.compiler").get())

    testImplementation(libs.findLibrary("mockk").get())
}
