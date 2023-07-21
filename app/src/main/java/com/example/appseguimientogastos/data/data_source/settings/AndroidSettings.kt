package com.example.appseguimientogastos.data.data_source.settings

import android.content.SharedPreferences

class AndroidSettings(private val preferences: SharedPreferences) : Settings {

    override fun getString(key: String): String? =
        if (exists(key)) preferences.getString(key, null) else null

    override fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).commit()
    }

    override fun getBoolean(key: String) =
        if (exists(key)) preferences.getBoolean(key, false) else null

    override fun setBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).commit()
    }

    override fun getInt(key: String) =
        if (exists(key)) preferences.getInt(key, 0) else null

    override fun setInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).commit()
    }

    override fun getFloat(key: String) =
        if (exists(key)) preferences.getFloat(key, 0f) else null

    override fun setFloat(key: String, value: Float) {
        preferences.edit().putFloat(key, value).commit()
    }

    override fun getLong(key: String) =
        if (exists(key)) preferences.getLong(key, 0L) else null

    override fun setLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).commit()
    }

    override fun exists(key: String): Boolean = preferences.contains(key)

    override fun clear(key: String) {
        preferences.edit().remove(key).commit()
    }

    override fun clear() {
        preferences.edit().clear().commit()
    }
}