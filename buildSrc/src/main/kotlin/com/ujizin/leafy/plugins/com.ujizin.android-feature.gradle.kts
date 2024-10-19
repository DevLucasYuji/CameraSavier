plugins {
    id("com.ujizin.android-compose")
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:themes"))
    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
}