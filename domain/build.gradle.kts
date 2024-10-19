plugins { id("com.ujizin.android-library") }

android { namespace = "com.ujizin.leafy.domain" }

dependencies {
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.bundles.test)
    testImplementation(projects.core.test)
}
