import Versions.coroutine
//import Versions.flipLoad
//import Versions.flipVersion
import Versions.fragment
import Versions.gsonVersion
import Versions.hilt
import Versions.lifecycle
import Versions.navigation
//import Versions.okhttp
import Versions.room

object Versions{
    const val lifecycle = "2.4.0"
    const val fragment = "1.3.6"
    const val navigation = "2.3.5"
    const val hilt = "2.41"
    const val room = "2.4.2"
    const val coroutine = "1.6.1"
    const val gsonVersion = "2.9.0"
//    const val flipVersion = "0.142.0"
//    const val flipLoad = "0.10.1"
//    const val okhttp = "4.9.3"
}

object Deps {

    // Lifecycle [ViewModel, LiveData] Ktx
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle"
    val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle"

    // Fragment Ktx
    val fragmentKtx = "androidx.fragment:fragment-ktx:$fragment"

    // Navigation Component
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigation"
    val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigation"

    // Hilt
    val hiltAndroid = "com.google.dagger:hilt-android:$hilt"
    val hiltCompiler = "com.google.dagger:hilt-compiler:$hilt"

    // Room

    val roomRuntime = "androidx.room:room-runtime:$room"
    val roomCompiler = "androidx.room:room-compiler:$room"
    val roomRoom = "androidx.room:room-ktx:$room"


    //Coroutine
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine"

    // GSON
    val gson = "com.google.code.gson:gson:$gsonVersion"

//    //Flipper
//    val flipper ="com.facebook.flipper:flipper:$flipVersion"
//    val flipperLoader = "com.facebook.soloader:soloader:$flipLoad"
//    val flipperNetwork = "com.facebook.flipper:flipper-network-plugin$flipVersion"
//    val flipperRelease = "com.facebook.flipper:flipper-noop:$flipVersion"
//
//    //okhttp3
//    val okhttp3 = "com.squareup.okhttp3:okhttp:$okhttp"
}
