plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.eniskaner.eyojsatellittesinformation"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eniskaner.eyojsatellittesinformation"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    /*packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }*/
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {
        sourceSets.configureEach {
            kotlin.srcDir(layout.buildDirectory.files("generated/ksp/$name/kotlin/"))
        }
        sourceSets.all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.hilt.navigation.fragment)

    // hilt
    //kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    /*val listExcludes = listOf(
        ":core",
        ":feature",
        ":feature:main",
    )

    rootProject.subprojects.forEach { module ->
        if (module.path !in listExcludes) implementation(project(module.path))
    }*/

    implementation(project(":eyojnavigation"))
    implementation(project(":feature:onboarding"))
    implementation(project(":core:common"))
    implementation(project(":feature:main:satellitecommunicator"))
    implementation(project(":feature:main:satellites"))
    implementation(project(":feature:main:satellitedetail"))
}