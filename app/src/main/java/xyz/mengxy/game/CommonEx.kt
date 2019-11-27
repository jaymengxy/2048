package xyz.mengxy.game

import android.content.Context
import android.preference.PreferenceManager


/**
 * Created by Mengxy on 2019-11-04.
 */

fun getIntegerPreference(context: Context, key: String, defaultValue: Int = 0): Int {
    return PreferenceManager.getDefaultSharedPreferences(context)?.getInt(key, defaultValue) ?: defaultValue
}

fun setIntegerPreference(context: Context, key: String, value: Int) {
    PreferenceManager.getDefaultSharedPreferences(context)?.edit()?.putInt(key, value)?.apply()
}