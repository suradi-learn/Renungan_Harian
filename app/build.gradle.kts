plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.suradi.renunganharian"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.suradi.renunganharian"
        minSdk = 28
        targetSdk = 36
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    dependencies {

        // 🔥 Compose BOM (WAJIB untuk sinkron versi)
        implementation(platform("androidx.compose:compose-bom:2024.04.01"))

        implementation("androidx.activity:activity-compose:1.8.2")

        // Compose UI
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")

        // Material 3
        implementation("androidx.compose.material3:material3")

        // Icon
        implementation("androidx.compose.material:material-icons-extended")

        // 🔥 Navigation (WAJIB cocok dengan Compose)
        implementation("androidx.navigation:navigation-compose:2.7.7")

        // Debug
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }
}