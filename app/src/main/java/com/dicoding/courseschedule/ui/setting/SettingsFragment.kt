package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.NightMode
import java.util.*

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var dailyReminder: DailyReminder

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference

        val prefTheme =
            findPreference<ListPreference>(getString(R.string.pref_key_dark))
        prefTheme?.setOnPreferenceChangeListener { _, newValue ->
            val mode = NightMode.valueOf((newValue as String).uppercase(Locale.US)).value
            updateTheme(mode)
            true
        }

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference

        dailyReminder = DailyReminder()

        val prefNotification =
            findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        prefNotification?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue as Boolean) {
                dailyReminder.setDailyReminder(requireActivity())
            } else {
                dailyReminder.cancelAlarm(requireActivity())
            }
            true
        }

    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}