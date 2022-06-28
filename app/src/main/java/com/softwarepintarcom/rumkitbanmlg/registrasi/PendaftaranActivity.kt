package com.softwarepintarcom.rumkitbanmlg.registrasi

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.softwarepintarcom.rumkitbanmlg.DatePickerFragment
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.login.LoginActivity
import kotlinx.android.synthetic.main.activity_pendaftaran.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class PendaftaranActivity : AppCompatActivity(),RegisterView {
    private lateinit var presenter : RegisterPresenter
    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var progressDialog : ProgressDialog
    var gender :String?=null
    var tanggal_lahir:String?=null
    lateinit var datePicker: DatePickerFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran)
        progressDialog = ProgressDialog(this)

        presenter = RegisterPresenter(this)
        datePicker = DatePickerFragment(this, true)
        tv_tgl.onClick {
            onDate()
        }

        btnsubmit.onClick {

            if (txtnm.text!!.isEmpty()){
                txtnm.error = "Nama tidak Boleh Kosong"

            }

            else  if (txtkota.text!!.isEmpty()){
            txtkota.error = "Kota tidak Boleh Kosong"

            }
            else if (txtponsel.text!!.isEmpty()){
                txtponsel.error = "No. Ponsel tidak Boleh Kosong"

            }
            else  if (txtponsel.text!!.toString().length<12){
            txtponsel.error = "No Hp Tidak Valid"

            }
            else  if (txtsandi.text!!.isEmpty()){
                txtsandi.error = "Kata Sandi tidak Boleh Kosong"

            }
            else  if (txtsandi.text!!.toString() != txtresandi.text!!.toString()){
                txtresandi.error = "Kata Sandi tidak cocok"


            }
            else if (!checked.isChecked){
                alert {
                    message="Mohon ceklist untuk persetujuan pendaftaran"
                }.show()
            }

            else if (txtemail.text!!.isEmpty()){
                txtemail.error = "Email tidak Boleh Kosong"

            }
            else if (!txtemail.text!!.toString().trim().matches(emailPattern.toRegex())){
                txtemail.error = "Email tidak valid"

            }
            else{
                progressDialog.show()
                presenter.register(
                   " ", txtnm.text.toString(), txtponsel.text.toString(),
                    txtemail.text.toString(),txtsandi.text.toString(),gender,
                    tvtgl.text.toString(),txtkota.text.toString(),txtalamat.text.toString()
                )
//                alert {
//                    message=gender.toString()
//                }.show()
            }




        }
    }


    private fun onDate() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                tvtgl.text = ("$dayStr-$mon-$year")
                tanggal_lahir = "$year-$monthStr-$dayStr"
            }
        })
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_lk ->
                    if (checked) {
                        gender="L"
                    }
                R.id.rb_pr ->
                    if (checked) {
                        gender="P"
                    }
            }
        }
    }

    override fun onSuccessRegister(msg: String?) {
        progressDialog.dismiss()

        alert {
            title="Information Register"
            message="Registrasi Berhasil Silahkan Login"
        }.show()
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun onFailedRegister(msg: String?) {
        progressDialog.dismiss()

        alert {
            title="Information Register"
            message=msg.toString()
        }.show()
    }
}