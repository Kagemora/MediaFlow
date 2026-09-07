plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.kagemora.mediaflow.core.datastore"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.dagger)

    implementation(libs.kotlinx.coroutines.core)
}