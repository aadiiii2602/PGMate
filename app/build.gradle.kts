plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pgmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pgmate"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.airbnb.android:lottie:6.4.1")
    implementation("com.google.zxing:core:3.2.1")
    implementation ("com.loopj.android:android-async-http:1.4.11")
    implementation ("com.google.android.gms:play-services-auth:21.2.0")
}