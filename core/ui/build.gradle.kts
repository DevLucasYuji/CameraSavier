plugins { id("com.ujizin.android-compose") }

android { namespace = "com.ujizin.leafy.core.components" }

dependencies {
    implementation(projects.core.themes)

    // TODO remove navigation, domain from core:ui
    implementation(projects.core.navigation)
    implementation(projects.domain)

    implementation(libs.coil)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.app.compat)
}
