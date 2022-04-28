object Dependencies {
    private const val lifecycle = "2.4.0"
    private const val fragment = "1.3.6"
    private const val navigation = "2.3.5"
    private const val hilt = "2.41"
    private const val room = "2.4.2"

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

    // Moshi
    val moshi = "com.squareup.moshi:moshi:1.13.0"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"
}