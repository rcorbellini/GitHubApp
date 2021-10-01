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

    val junit = "4.13.2"
    val junitext = "1.1.2"
    val mockito = "2.2.11"
    val androidx_espresso = "3.3.0"
    val androidx_testing = "1.3.0"
    val jupter = "5.6.2"
    val archComponentTestVersion = "2.1.0"


    val kotlin = "1.4.31"
    val gradleandroid = "7.1.0-alpha13"
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
    val androidx_navigation_safe_args    = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"
    //  </editor-fold>

    // <editor-fold desc="tools">
    val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_gradleversions =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"
    // </editor-fold>

    // <editor-fold desc="testing">
    val testlib_junit               = "junit:junit:${Versions.junit}"
    val testlib_junitext            = "androidx.test.ext:junit:${Versions.junitext}"
    val testandroidx_rules          = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner         = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore   = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"
    val testlib_mockito             = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    val testlib_kt_junit            = "org.jetbrains.kotlin:kotlin-test-junit5"
    val testlib_kt_test             = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
    val testlib_coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kt_coroutines}"
    val testlib_jupter              = "org.junit.jupiter:junit-jupiter-api:${Versions.jupter}"
    val testlib_archComponent       = "androidx.arch.core:core-testing:${Versions.archComponentTestVersion}"
    //</editor-fold>

    // <editor-fold desc="retrofit">
    val logginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    // </editor-fold>
}