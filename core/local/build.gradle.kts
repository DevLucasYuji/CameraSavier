plugins {
    id("com.ujizin.android-library")
    alias(libs.plugins.kotlinx.serialization)
}

android { namespace = "com.ujizin.leafy.data.local" }

dependencies {
    implementation(libs.bundles.datastore)

    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.serialization.json)

    androidTestImplementation(projects.core.test)
    androidTestImplementation(libs.bundles.test)
    androidTestImplementation(libs.coroutines.test)

    implementation(projects.core.repository)
}
