pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Leafy"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":domain")
include(
    ":core:ui",
    ":core:local",
    ":core:repository",
    ":core:themes",
    ":core:navigation",
    ":core:test"
)
include(
    ":features:home",
    ":features:search",
    ":features:alarm",
    ":features:camera",
    ":features:publish",
    ":features:about",
    ":features:preferences",
    ":features:plant",
    ":features:tasks",
)
