plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    //временно стопнул
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.kagemora.mediaflow"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.kagemora.mediaflow"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // ---------- Модули проекта ----------
    implementation(project(":core:common"))
    // implementation(project(":core:model"))
    implementation(project(":core:network"))
    // implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    // implementation(project(":core:ui"))
    // implementation(project(":core:navigation"))
    implementation(project(":feature:auth:api"))
    implementation(project(":feature:auth:impl"))
    // implementation(project(":feature:feed:impl"))
    // implementation(project(":feature:market:impl"))
    // implementation(project(":feature:video:impl"))

    // ---------- Compose ----------
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)

    // ---------- DI: Dagger 2 ----------
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // ---------- WorkManager ----------
    implementation(libs.androidx.work.runtime.ktx)

    // ---------- Logging / Leak detection ----------
    implementation(libs.timber)
    debugImplementation(libs.leakcanary.android)

    // ---------- Firebase ----------
    //временно стопнул
//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.messaging)
//    implementation(libs.firebase.crashlytics)

    // ---------- Unit-тесты ----------
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)

    // ---------- Инструментальные / Compose UI тесты ----------
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.datastore.preferences)
}