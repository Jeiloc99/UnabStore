plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "me.jeisonsalcedo.unabstore"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "me.jeisonsalcedo.unabstore"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.google.firebase.firestore.ktx)
    implementation(libs.firebase.firestore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //firebase
    implementation(platform(libs.firebase.bom))

    //auth
    implementation(libs.firebase.auth)

    //firestore
    // app/build.gradle.kts


        // Usa el BoM oficial de Firebase
        implementation(platform("com.google.firebase:firebase-bom:33.4.0"))

        // Firestore (elige UNO; recomiendo -ktx)
        implementation("com.google.firebase:firebase-firestore-ktx")
        // (si prefieres la clásica: implementation("com.google.firebase:firebase-firestore"))

        // (opcional) Analytics
        implementation("com.google.firebase:firebase-analytics-ktx")


}