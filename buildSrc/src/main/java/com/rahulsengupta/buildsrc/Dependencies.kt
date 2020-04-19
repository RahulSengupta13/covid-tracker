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
    const val kotlin = "1.3.70"
    const val kotlinSerialization = "0.20.0-mpp-dev-1"
    const val gradle = "4.0.0-alpha09"

    //core
    const val appcompat = "1.1.0-beta01"
    const val annotation = "1.1.0"
    const val constraintLayout = "2.0.0-beta4"
    const val ktx = "1.2.0"
    const val fragment = "1.2.1"
    const val lifecycle = "2.2.0"
    const val navigation = "2.2.0"
    const val recyclerView = "1.2.0-alpha01"
    const val material = "1.2.0-alpha05"
    const val coroutines = "1.3.3"
    const val timber = "4.7.1"
    const val circularImageView = "4.2.0"
    const val spark = "1.2.0"
    const val ticker = "2.0.2"
    const val gson = "2.8.5"
    const val threeTen = "1.2.3"

    const val mapsPlayServices = "17.0.0"
    const val mapUtils = "1.1.0"

    //database
    const val room = "2.2.3"
    const val paging = "2.1.1"

    //networking
    const val retrofit = "2.8.1"
    const val okhttp = "4.5.0"
    const val kotlinSerializationConverter = "0.5.0"

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
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
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

    const val mapsPlayServices = "com.google.android.gms:play-services-maps:${Versions.mapsPlayServices}"
    const val mapUtils = "com.google.maps.android:android-maps-utils:${Versions.mapUtils}"

    const val roomRuntime =  "androidx.room:room-runtime:${Versions.room}"
    const val pagingRuntime =  "androidx.paging:paging-runtime:${Versions.paging}"
    const val roomKtx =  "androidx.room:room-ktx:${Versions.room}"
    const val glide =  "com.github.bumptech.glide:glide:${Versions.glide}"
    const val material =  "com.google.android.material:material:${Versions.material}"
    const val kotlinStdlib =  "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerialization}"
    const val coroutinesAndroid =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore =  "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // Tools
    const val timber =  "com.jakewharton.timber:timber:${Versions.timber}"
    const val circularImageView =  "com.mikhaellopez:circularimageview:${Versions.circularImageView}"
    const val spark =  "com.robinhood.spark:spark:${Versions.spark}"
    const val ticker =  "com.robinhood.ticker:ticker:${Versions.ticker}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val threeTen = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTen}"


    // Dagger
    const val daggerCompiler =  "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor =  "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger =  "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid =  "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport =  "com.google.dagger:dagger-android-support:${Versions.dagger}"

    // Networking
    const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp =  "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor =  "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val kotlinSerializationConverter =  "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverter}"

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
    const val persistence = ":persistence"
    const val network = ":network"
    const val home = ":home"
    const val core = ":core"
}