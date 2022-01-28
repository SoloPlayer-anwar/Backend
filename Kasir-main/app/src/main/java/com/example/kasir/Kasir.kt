package com.example.kasir

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.kasir.network.HttpClient
import com.example.kasir.utils.Const.SharePreferenceConfig.PREFERENCES_TOKEN
import com.example.kasir.utils.Const.SharePreferenceConfig.PREFERENCES_USER

class Kasir: MultiDexApplication() {

    companion object {
        lateinit var instance: Kasir

        fun getApp(): Kasir {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString(PREFERENCES_TOKEN, token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString(PREFERENCES_TOKEN, null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString(PREFERENCES_USER, user).apply()
    }

    fun getUser(): String? {
        return getPreferences().getString(PREFERENCES_USER, null)
    }

    fun clearToken() {
        getPreferences().edit().remove(PREFERENCES_TOKEN).apply()
        HttpClient.getInstance().buildRetrofitClient("")
    }
}