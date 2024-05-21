plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqldelight)

}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.android.driver)

        }

        iosMain.dependencies {
            implementation(libs.native.driver)

        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.kotlinx.datetime)
            implementation(libs.sql.delight)
            implementation(libs.koin.core)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }


    sqldelight {

        databases {

            create("AppDatabase") {
                packageName.set("com.mohaberabi.note.database")
            }
        }
    }
}






android {
    namespace = "com.mohaberabi.notekmp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
