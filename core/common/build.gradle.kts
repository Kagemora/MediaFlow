plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.kagemora.mediaflow.core.common"
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
    // ViewModel + viewModelScope — тут будет жить BaseViewModel<State, Intent, Effect>
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Корутины — Flow/StateFlow внутри BaseViewModel.
    // -android обязателен: viewModelScope использует Dispatchers.Main,
    // а его реальная реализация лежит именно в -android, не в -core.
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Dagger — только рантайм-аннотации (@Inject, @MapKey, Provider<T>)
    implementation(libs.dagger)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}