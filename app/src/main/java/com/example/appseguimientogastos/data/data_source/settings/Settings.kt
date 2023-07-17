package com.worldline.ascott_hotel.data.data_source.settings

interface Settings {
    fun getString(key: String): String?
    fun setString(key: String, value: String)
    fun getBoolean(key: String): Boolean?
    fun setBoolean(key: String, value: Boolean)
    fun getInt(key: String): Int?
    fun setInt(key: String, value: Int)
    fun getFloat(key: String): Float?
    fun setFloat(key: String, value: Float)
    fun getLong(key: String): Long?
    fun setLong(key: String, value: Long)
    fun exists(key: String): Boolean
    fun clear(key: String)
    fun clear()
}