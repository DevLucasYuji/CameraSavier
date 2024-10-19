plugins {
    id("com.ujizin.android-compose")
    alias(libs.plugins.kotlinx.serialization)
}

android { namespace = "com.ujizin.leafy.navigation" }

dependencies { implementation(libs.kotlinx.serialization.json) }
