package com.softwarepintarcom.rumkitbanmlg.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itrsiam.rsiamuslimat.login.LoginPresenter
import com.itrsiam.rsiamuslimat.login.LoginView
import com.softwarepintarcom.rumkitbanmlg.MainActivity
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.api.Utils.nohp
import com.softwarepintarcom.rumkitbanmlg.api.Utils.session
import com.softwarepintarcom.rumkitbanmlg.api.Utils.user_id
import com.softwarepintarcom.rumkitbanmlg.api.Utils.user_name
import com.softwarepintarcom.rumkitbanmlg.registrasi.PendaftaranActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_pendaftaran.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.sdk27.coroutines.onClick

class LoginActivity : AppCompatActivity() , LoginView {
    private lateinit var presenter: LoginPresenter
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "my_shared_preferences"


    var session_status="session_status"

    private lateinit var progressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)


        progressDialog = ProgressDialog(this)
        btn_login.onClick {

            val username = txtNik.text.toString()
            val pswd = txtPassword.text.toString()

            when {
                username.isEmpty() -> {
                    txtNik.error = "Kolom Harus Diisi"
                }
                pswd.isEmpty() -> {
                    txtPassword.error = "Kolom Harus Diisi"
                }
                else -> {
                    progressDialog.setMessage("Proses Login")
                    progressDialog.show()
                    presenter.login(username, pswd)
                }
            }
        }
        txtRegister.onClick {
            startActivity(intentFor<PendaftaranActivity>().newTask())
        }



        val sharedPref=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        session=sharedPref.getBoolean(session_status,false)
        user_id=sharedPref.getString("id",null)
        user_name=sharedPref.getString("username",null)
        nohp=sharedPref.getString("no_hp",null)
        if (session){
            val intent= Intent(this,MainActivity::class.java)
            intent.putExtra("id",user_id)
            intent.putExtra("username",user_name)
            intent.putExtra("no_hp", nohp)
            startActivity(intent)
            finish()
        }
//        else if (intent.getStringExtra("verif")==null){
//            startActivity<MainActivity>()
//            finish()
//
//
//        }


    }

    override fun onSuccessLogin(msg: String?, id: String?, username: String?, nohp: String?) {
        progressDialog = ProgressDialog(this)
        progressDialog.dismiss()


        alert {
            title="Login Berhasil"
            message= "Selamat Datang $username"
        }.show()

        val sharedPref=getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

        val editor=sharedPref.edit()
        editor.putBoolean(session_status, true)
        editor.putString("id",id)
        editor.putString("username",username)
        editor.putString("no_hp",nohp)
        editor.commit()
        val intent=Intent(this,MainActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("username",username)
        intent.putExtra("no_hp",nohp)
        startActivity(intent)
        finish()
    }

    override fun onProfil(
        id: String?,
        username: String?,
        nohp: String?,
        nama_depan: String?,
        nama_belakang: String?,
        password: String?
    ) {

    }

    override fun onFailedLogin(msg: String?) {
        progressDialog.dismiss()

        alert {
            title="Gagal"
            message= "Login Gagal NIK atau Email dan Password tidak ditemukan"
        }.show()

    }

    override fun onFailure(msg: String?) {
        progressDialog.dismiss()
        alert {
            title="Gagal"
            message= "Failure"
        }.show()
    }

    override fun onSuccessUpdate(msg: String) {

    }

    override fun onErrorUpdate(msg: String) {

    }
}