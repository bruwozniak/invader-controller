package com.smarthome.controller.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.smarthome.controller.R
import com.smarthome.controller.api.SmartHomeApi
import com.smarthome.controller.api.SmartHomeApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val REQUEST_SETTINGS = 1000

    private var smartHomeApi: SmartHomeApi? = null

    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null
    private var disposable3: Disposable? = null
    private var disposable4: Disposable? = null
    private var disposable5: Disposable? = null
    private var disposable6: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smartHomeApi = SmartHomeApiClient.getApi(this@MainActivity)
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
                smartHomeApi = SmartHomeApiClient.getApi(this@MainActivity)
            }
        }
    }

    private fun setupUI() {
        button_1.setOnClickListener {
            disposable1 = smartHomeApi?.action1("param1Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }

        button_2.setOnClickListener {
            disposable2 = smartHomeApi?.action2("param2Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }

        button_3.setOnClickListener {
            disposable3 = smartHomeApi?.action3("param3Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }

        button_4.setOnClickListener {
            disposable4 = smartHomeApi?.action4("param4Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }

        button_5.setOnClickListener {
            disposable5 = smartHomeApi?.action5("param5Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }

        button_6.setOnClickListener {
            disposable6 = smartHomeApi?.action6("param6Value")?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                    { result ->
                        if (result.success) {
                            showSnackbar(R.string.action_successful)
                        } else {
                            showSnackbar(R.string.action_failed)
                        }
                    }, { error ->
                        Log.e(TAG, error.message)
                        showSnackbar(R.string.action_failed)
                    }
                )
        }
    }

    override fun onPause() {
        super.onPause()
        cleanDisposables()
    }

    private fun showSnackbar(textResource: Int) {
        Snackbar.make(coordinator, textResource, Snackbar.LENGTH_SHORT).show()
    }

    private fun cleanDisposables() {
        disposable1?.dispose()
        disposable2?.dispose()
        disposable3?.dispose()
        disposable4?.dispose()
        disposable5?.dispose()
        disposable6?.dispose()
    }
}
