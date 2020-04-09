package com.rahulsengupta.buildsrc

object AppMetaData {
    const val id = "com.rahulsengupta.covidtracker.android"
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val minSdkVersion = 24
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "29.0.2"
}

object Versions {
    // Application
    const val kotlin = "1.3.61"
    const val gradle = "4.0.0-alpha09"

    //core
    const val appcompat = "1.1.0-beta01"
    const val constraintLayout = "2.0.0-beta4"
    const val ktx = "1.2.0"
    const val fragment = "1.2.1"
    const val lifecycle = "2.2.0"
    const val navigation = "2.2.0"
    const val recyclerView = "1.2.0-alpha01"
    const val material = "1.1.0"
    const val coroutines = "1.3.3"
    const val timber = "4.7.1"
    const val gson = "2.8.6"

    //database
    const val room = "2.2.3"
    const val paging = "2.1.1"

    //networking
    const val retrofit = "2.7.1"
    const val okhttp = "3.14.4"
    const val scarlet = "0.1.10"

    //di
    const val dagger = "2.25.4"

    //image
    const val glide = "4.9.0"

    //testing
    const val junit = "4.12"
    const val androidxJunit = "1.1.1"
    const val espresso = "3.2.0"

}

object Dependencies {

    //kapt
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //core
    const val appcompat =  "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout =  "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val core =  "androidx.core:core-ktx:${Versions.ktx}"
    const val fragmentKtx =  "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val lifecycleExtensions =  "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val livedata =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewmodel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //navigation
    const val navigationFragment =  "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi =  "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val recyclerview =  "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val roomRuntime =  "androidx.room:room-runtime:${Versions.room}"
    const val pagingRuntime =  "androidx.paging:paging-runtime:${Versions.paging}"
    const val roomKtx =  "androidx.room:room-ktx:${Versions.room}"
    const val glide =  "com.github.bumptech.glide:glide:${Versions.glide}"
    const val material =  "com.google.android.material:material:${Versions.material}"
    const val kotlinStdlib =  "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesAndroid =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore =  "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // Tools
    const val timber =  "com.jakewharton.timber:timber:${Versions.timber}"

    // Dagger
    const val daggerCompiler =  "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor =  "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger =  "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid =  "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport =  "com.google.dagger:dagger-android-support:${Versions.dagger}"

    // Networking
    const val gson =  "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConvertor =  "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp =  "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor =  "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //web socket
    const val scarlet =  "com.tinder.scarlet:scarlet:${Versions.scarlet}"
    const val scarletWebSocketOkHttp =  "com.tinder.scarlet:websocket-okhttp:${Versions.scarlet}"
    const val scarletGsonAdapter =  "com.tinder.scarlet:message-adapter-gson:${Versions.scarlet}"
    const val scarletLifecycle =  "com.tinder.scarlet:lifecycle-android:${Versions.scarlet}"
    const val scarletCoroutinesAdapter =  "com.tinder.scarlet:stream-adapter-coroutines:${Versions.scarlet}"

    //testing
    const val junit = "junit:junit:${Versions.junit}"
    const val junitAndroidx = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object GradleTemplates {
    const val androidLibrary = "template-android-library.gradle"
    const val featureModule = "template-feature-module.gradle"
}

object Modules {
    const val app = ":app"
    const val network = ":network"
    const val home = ":home"
    const val core = ":core"
}