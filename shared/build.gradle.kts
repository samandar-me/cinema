plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.21"
    id("kotlin-parcelize")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    val decomposeVersion = "2.0.0"

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export("com.arkivanov.decompose:decompose:2.1.0-compose-experimental-alpha-07")
            export("com.arkivanov.essenty:lifecycle:1.2.0-alpha-06")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)

                api("com.arkivanov.decompose:decompose:2.1.0-compose-experimental-alpha-07")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:2.1.0-compose-experimental-alpha-07")
                implementation("com.arkivanov.essenty:lifecycle:1.2.0-alpha-06")

                api(libs.mvikotlin)
                api(libs.mvikotlin.main)
                api(libs.mvikotlin.extensions.coroutines)

                api("io.github.qdsfdhvh:image-loader:1.6.8")

                implementation("io.github.xxfast:decompose-router:0.5.0")
            }
        }
//        val commonTest by getting {
//            dependencies {
//                implementation(libs.kotlin.test)
//            }
//        }
        val androidMain by getting {
            dependencies {
                implementation(libs.appcompat)
                implementation(libs.activity.compose)
                implementation(libs.ktor.client.cio)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

android {
    namespace = "com.sdk.cinema2"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}