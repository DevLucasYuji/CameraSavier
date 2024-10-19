plugins {
    id("com.ncorti.ktfmt.gradle")
    id("com.diffplug.spotless")
}

internal val Project.libs: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

spotless {
    format("misc") {
        target("**/*.gradle", "**/*.md", "**/.gitignore")
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt", "**/*.kts")
        ktfmt(libs.findVersion("ktfmt").get().requiredVersion).kotlinlangStyle()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
