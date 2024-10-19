plugins { id("com.ujizin.android-feature") }

android { namespace = "com.ujizin.leafy.features.camera" }

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(libs.coil)
    implementation(libs.camposer)
}
