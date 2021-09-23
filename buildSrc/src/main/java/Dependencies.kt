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
    val glide = "4.12.0"
    val log_interceptor = "4.2.1"
    val kiel = "1.2.1"
    val retrofit = "2.9.0"

    val androidx_recyclerview = "1.1.0"
    val androidx_navigation = "2.3.4"
    val android_material = "1.3.0"


    val kotlin = "1.4.31"
    val gradleandroid = "7.1.0-alpha12"
    val gradleversions = "0.38.0"
}

object Deps {

    val lib_kt_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kt_coroutines}"

    //<editor-fold desc="Android">
    val koin = "io.insert-koin:koin-core:${Versions.koin}"
    val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    val koinAndroidViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin}"
    val glide                            = "com.github.bumptech.glide:glide:${Versions.glide}"
    val kiel                             = "io.github.ibrahimyilmaz:kiel:${Versions.kiel}"
    //  </editor-fold>

    //<editor-fold desc="AndroidX">
    val android_material                = "com.google.android.material:material:${Versions.android_material}"
    val androidx_recyclerview            = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"
    val androidx_navigation_fragment     = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_ui           = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    //  </editor-fold>

    // <editor-fold desc="tools">
    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_gradleversions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"
    // </editor-fold>

    // <editor-fold desc="retrofit">
    val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    // </editor-fold>
}