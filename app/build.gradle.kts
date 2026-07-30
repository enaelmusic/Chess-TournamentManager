import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.swisstournament2"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.swisstournament2"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //BaseURl
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if(localPropertiesFile.exists())
            localPropertiesFile.inputStream().use{ localProperties.load(it)}
        val baseURL = localProperties.getProperty("api.base.url")
        /**
         * if BuildConfig Object is not reconize, clean project, build, rebuild.
         * Cclass can be automticly generated from here , this add a new package(Java) and a class(BuildConfig.java) with attributes.
         * depend if you are in production or developement mode, be carefull for use-permission clearText if link is a http or https.
         * the variable api.base.url can be declared in rootProject/local.properties
         */
        buildConfigField("String","BASE_URL","\"${baseURL ?: "your_https_adresse/" }\"")
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    // Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    // Source: https://mvnrepository.com/artifact/com.squareup.retrofit2/converter-gson
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
}