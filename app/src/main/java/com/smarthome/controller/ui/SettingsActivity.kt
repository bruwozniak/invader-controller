package com.smarthome.controller.ui

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.smarthome.controller.R
import com.smarthome.controller.settings.SettingManager
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        setupUI()
        fillValues()
    }

    private fun setupUI() {
        button_cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        button_save.setOnClickListener {
            SettingManager.setIPAddress(this@SettingsActivity, field_ip_address.text.toString())
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun fillValues() {
        val savedIpAddress = SettingManager.getIPAddress(this@SettingsActivity)
        if (!TextUtils.isEmpty(savedIpAddress)) {
            field_ip_address.setText(savedIpAddress)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}