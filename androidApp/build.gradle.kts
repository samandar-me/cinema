plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.6.21"
    id("kotlinx-serialization")
}

android {
    namespace = "com.sdk.cinema2.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.sdk.cinema2.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)

    implementation("org.kodein.di:kodein-di:7.20.2")
    implementation("org.kodein.di:kodein-di-framework-android-core:7.20.2")
    implementation("org.kodein.di:kodein-di-framework-android-x:7.5.1")
}