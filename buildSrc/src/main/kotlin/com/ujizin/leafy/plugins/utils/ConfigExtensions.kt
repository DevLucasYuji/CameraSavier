package com.ujizin.leafy.plugins.utils

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun BaseAppModuleExtension.configApplication(rootProject: Project) = with(rootProject) {
    defaultConfig {
        applicationId = "com.ujizin.leafy"
        versionCode = 1
        versionName = "0.1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("${rootProject.extra["RELEASE_STORE_FILE"]}")
            storePassword = "${rootProject.extra["RELEASE_STORE_PASSWORD"]}"
            keyAlias = "${rootProject.extra["RELEASE_KEY_ALIAS"]}"
            keyPassword = "${rootProject.extra["RELEASE_KEY_PASSWORD"]}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            resValue("string", "app_name", "Leafy")
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name", "Leafy Dev")
        }
    }
}

fun CommonExtension<*, *, *, *, *, *>.configAndroid() {
    compileSdk = 34
    defaultConfig {
        minSdk = 23
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}
