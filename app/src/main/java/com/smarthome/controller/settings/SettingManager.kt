package com.smarthome.controller.settings

import android.content.Context
import android.preference.PreferenceManager

object SettingManager {
    private const val PREF_IP_ADDRESS = "ip_address"

    fun setIPAddress(context: Context, address: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(PREF_IP_ADDRESS, address).apply()
    }

    fun getIPAddress(context: Context): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_IP_ADDRESS, "")
    }
}