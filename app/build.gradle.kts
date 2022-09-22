plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.jamiecraane.plugins.kmmimages") version "1.0.0-alpha12"
}

kmmImagesConfig {
    imageFolder.set(project.projectDir.resolve("images"))
    sharedModuleFolder.set(project.projectDir)
    androidResFolder.set(project.projectDir.resolve("src/main/generated-res"))
    packageName.set("dev.jamiecraane.standalonekmmimages")
    defaultLanguage.set("en")
    usePdf2SvgTool.set(true) // optional parameter
    kotlinMainSourceFolder.set("main")
}

var generateImages = tasks["generateImages"]
tasks["preBuild"].dependsOn(generateImages)

android {
    namespace = "com.example.kmmstartertemplate.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.kmmstartertemplate.android"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    sourceSets {
        getByName("main") {
            res.srcDirs("src/main/generated-res")
        }
    }
}


dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.compose.material:material:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.0")
}