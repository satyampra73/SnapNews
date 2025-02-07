plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
}
val MY_KEY: String = project.findProperty("MY_KEY") as? String ?: ""
val MY_URL: String = project.findProperty("MY_URL") as? String ?: ""
android {
    namespace = "com.satyam.snapnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.satyam.snapnews"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String","API_KEY",MY_KEY)
        buildConfigField("String","BASE_URL",MY_URL)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        buildConfig = true
        viewBinding = true
    }
}
kapt {
    correctErrorTypes = true
}

dependencies {
    val nav_version = "2.8.6"
    val room_version = "2.6.1"
    val dagger_version = "2.52"
    val coroutines_version = "1.9.0"
    val retrofit_version = "2.11.0"
    val okHTTP_version = "4.12.0"
    val lifecycle_version = "2.8.7"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHTTP_version")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //mock web server
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation ("com.google.truth:truth:1.4.4")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //hilt dependency injection
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

}