plugins { id("com.ujizin.android-library") }

android { namespace = "com.ujizin.leafy.test" }

dependencies {
    api(libs.coroutines.test)
    api(libs.bundles.test)
}
