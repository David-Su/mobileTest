package com.example.mobiletest

import android.app.Application
import android.content.Context

/**
 * @author SuK
 * @time 2025/11/6 11:57
 * @desc
 */
class MobileTestApp : Application() {
    companion object {
        val INSTANCE: MobileTestApp
            get() = _INSTANCE!! //不存在其他类可以在Application构建之前获取INSTANCE，所以可以用!!
        private var _INSTANCE: MobileTestApp? = null
    }

    init {
        _INSTANCE = this
    }

}