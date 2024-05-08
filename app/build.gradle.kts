plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.dedesaepulloh.tribunnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dedesaepulloh.tribunnews"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "usernameAuth", "\"${findProperty("usernameAuth") ?: ""}\"")
            buildConfigField("String", "passwordAuth", "\"${findProperty("passwordAuth") ?: ""}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "usernameAuth", "\"${findProperty("usernameAuth") ?: ""}\"")
            buildConfigField("String", "passwordAuth", "\"${findProperty("passwordAuth") ?: ""}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation (libs.extension)
    implementation(libs.androidx.activity)
    kapt (libs.compiler)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)

    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation(libs.adapter.rxjava2)
    implementation(libs.rxandroid)

    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    implementation(libs.kotlinx.coroutines.android)

    implementation (libs.glide)
    implementation (libs.timber)

    debugImplementation (libs.library)
    releaseImplementation (libs.library.no.op)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}