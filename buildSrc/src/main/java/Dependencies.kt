import org.gradle.api.JavaVersion

object Config {
    val minSdk = 23
    val compileSdk = 30
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "30.0.3"
}

object Versions {


    // <editor-fold desc="tools">
    val kotlin = "1.4.31"
    val gradleandroid = "7.1.0-alpha12"
    val gradleversions = "0.38.0"
    // </editor-fold>
}

object Deps {

    // <editor-fold desc="tools">

    val tools_gradleandroid           = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    val tools_kotlin                  = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val tools_gradleversions          = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"

    // </editor-fold>
}