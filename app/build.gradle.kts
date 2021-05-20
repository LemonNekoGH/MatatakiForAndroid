plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "moe.lemonneko.mttk"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_BASE_URL", "\"https://api.mttk.net\"")
        }
        debug {
            buildConfigField("String", "API_BASE_URL", "\"https://apitest.mttk.net\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    // JSON Parser
    implementation("org.json:json:20210307")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
    // ImmersionBars
    implementation("com.gyf.immersionbar:immersionbar:3.0.0")
    implementation("com.gyf.immersionbar:immersionbar-ktx:3.0.0")
    // Network
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    // Unit Test
    testImplementation("junit:junit:4.13.2")
    testImplementation(kotlin("test-junit"))
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.0.0-beta05")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta05")
    implementation("androidx.compose.foundation:foundation:1.0.0-beta05")
    implementation("androidx.compose.material:material:1.0.0-beta05")
    implementation("androidx.compose.material:material-icons-core:1.0.0-beta05")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-beta01")
    implementation("androidx.activity:activity-compose:1.3.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02")
    // Jetpack Compose Utils
    implementation("com.google.accompanist:accompanist-swiperefresh:0.8.1")
    implementation("com.google.accompanist:accompanist-pager:0.8.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.8.1")
    implementation("com.google.accompanist:accompanist-glide:0.8.1")
}