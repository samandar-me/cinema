enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

rootProject.name = "Cinema2"
include(":androidApp")
include(":shared")