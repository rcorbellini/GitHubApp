import org.gradle.api.JavaVersion

object Config {
    val minSdk = 23
    val compileSdk = 30
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "30.0.3"
}

object Versions {
    val kt_coroutines = "1.4.3"
    val koin = "2.2.2"
    val log_interceptor = "4.2.1"
    val retrofit = "2.9.0"

    // <editor-fold desc="tools">
    val kotlin = "1.4.31"
    val gradleandroid = "7.1.0-alpha12"
    val gradleversions = "0.38.0"
    // </editor-fold>
}

object Deps {

    val lib_kt_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kt_coroutines}"

    //<editor-fold desc="Android">
    val koin = "io.insert-koin:koin-core:${Versions.koin}"
    val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    //  </editor-fold>

    // <editor-fold desc="tools">
    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_gradleversions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"
    // </editor-fold>

    // <editor-fold desc="retrofit">
    val logginInterceptor                = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    val retrofit                         = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson                    = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    // </editor-fold>
}