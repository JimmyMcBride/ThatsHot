package com.example.thatshot

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.Interceptor


object FlipperProvider {
    fun init(context: Context) {
        SoLoader.init(context, false)
        AndroidFlipperClient.getInstance(context).apply {
            addPlugin(
                InspectorFlipperPlugin(context, DescriptorMapping.withDefaults())
            )
            addPlugin(networkFlipperPlugin)
        }.also { it.start() }
    }
    fun getFlipperOkhttpInterceptor(): Interceptor {
        return FlipperOkhttpInterceptor(networkFlipperPlugin, false)
    }
    private val networkFlipperPlugin = NetworkFlipperPlugin()
}
