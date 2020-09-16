package com.invader.controller.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.invader.controller.R
import com.invader.controller.settings.SettingManager
import com.invader.controller.udp.Invader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val REQUEST_SETTINGS = 1000

    private var invader: Invader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipAddress = SettingManager.getIPAddress(this@MainActivity)
        invader = ipAddress?.let { Invader(it, 10001,10001) }
        setupUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.action_settings) {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivityForResult(intent, REQUEST_SETTINGS)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                // Refresh the API client since the IP has changed
                val ipAddress = SettingManager.getIPAddress(this@MainActivity)
                ipAddress?.let { invader?.changeIP(it) }
            }
        }
    }

    private fun setupUI() {
        button_1.setOnClickListener {
            invader?.send("1")
        }
        button_2.setOnClickListener {
            invader?.send("2")
        }
        button_3.setOnClickListener {
            invader?.send("3")
        }
        button_4.setOnClickListener {
            invader?.send("4")
        }
        button_5.setOnClickListener {
            invader?.send("5")
        }
        button_6.setOnClickListener {
            invader?.send("6")
        }
        button_7.setOnClickListener {
            invader?.send("7")
        }
        button_8.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    invader?.send("8")
                }
                MotionEvent.ACTION_UP -> {
                    invader?.send("0")
                }
            }
            true
        }
    }
}
