package com.softwarepintarcom.rumkitbanmlg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.softwarepintarcom.rumkitbanmlg.login.LoginActivity
import com.softwarepintarcom.rumkitbanmlg.registrasi.PendaftaranActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.sdk27.coroutines.onClick

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        checkFirstRun()
        btnlogin.onClick {

            startActivity(intentFor<LoginActivity>().newTask())
            finish()
        }
        btnregister.onClick {
            startActivity(intentFor<PendaftaranActivity>().newTask())
            finish()
        }

        // Creates instance of the manager.
        val appUpdateManager = AppUpdateManagerFactory.create(this)

// Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // For a flexible update, use AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
            }
        }



    }


    private fun checkFirstRun() {
        val isFirstRun: Boolean =
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true)
        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply()
        }
        else if(!isFirstRun){
            startActivity(intentFor<LoginActivity>().newTask())
            finish()

        }
    }
}


