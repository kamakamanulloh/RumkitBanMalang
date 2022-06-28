package com.softwarepintarcom.rumkitbanmlg.pasien

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.softwarepintarcom.rumkitbanmlg.DatePickerFragment
import com.softwarepintarcom.rumkitbanmlg.MainActivity
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.api.Utils
import com.softwarepintarcom.rumkitbanmlg.pendaftaran.PendaftaranPresenter
import com.softwarepintarcom.rumkitbanmlg.pendaftaran.PendaftaranView
import kotlinx.android.synthetic.main.activity_pasien2.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class PasienActivity2 : AppCompatActivity() ,CekRMView,PendaftaranView {
    var tanggal: String? =null
    var poli_id: String? =null
    var jadwal_id: String? =null

    var tanggal_lahir: String? =null
    var jumlah: Int? =null
    var pasienid: String? =null
    var dokterid: String? =null
    var nm_px: String? =null
    var jam: String? =null
    var nm_poli: String? =null
    var nm_dokter: String? =null
    var rm_px: String? =null
    var kuota: String? =null
    lateinit var datePicker: DatePickerFragment
    lateinit var pendaftaranPresenter: PendaftaranPresenter
    private   lateinit var presenter: PasienPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasien2)
        datePicker = DatePickerFragment(this, true)
        presenter=PasienPresenter(this)
        pendaftaranPresenter= PendaftaranPresenter(this)


        tvPoli.text= intent.getStringExtra("poli")
        tvDokter.text= intent.getStringExtra("nm_dokter")
        tvTanggal.text=intent.getStringExtra("tanggal")
        tvJam.text=intent.getStringExtra("jam")
        if (intent.getStringExtra("jenispx") == "2"){
            txtnobpjs.visibility= View.GONE
            titlebpjs.visibility= View.GONE
//            alert {
//                message="Umum"
//            }.show()
        }
        else if (intent.getStringExtra("jenispx") == "5"){

//            alert {
//                message="BPJS"
//            }.show()
        }
       btncarirm.onClick {
           val norm = txtrm.text.toString()
           if (norm.isEmpty() || tvtl.text.toString().isEmpty()){
               txtrm.error="Kolom Harus Diisi"
               tvtl.error="Kolom Harus Diisi"
           }
           else{
               presenter.cekrm(norm,tanggal_lahir.toString())
           }
       }
        btntgllahir.onClick {
            onDate()
        }

        btnNext.onClick {
            pendaftaranPresenter.regis_antrian(
                intent.getStringExtra("dokter_id"),
                intent.getStringExtra("jenispx"),
                Utils.user_id,
                intent.getStringExtra("poli_id"),
                intent.getStringExtra("nm_dokter"),
                intent.getStringExtra("poli"),
                txtnobpjs.text.toString(),
                " ",
                "",
                intent.getStringExtra("tanggal"),
                intent.getStringExtra("jam"),
                "",
                "",
                "",
                "",
                pasienid,
                "",

                intent.getStringExtra("id_jadwal")

            )

        }
        if (intent.getStringExtra("norm").toString() != "" &&
            intent.getStringExtra("tanggal_lahir").toString()!=""){
            txtrm.setText(intent.getStringExtra("norm"))
            tvtl.text=intent.getStringExtra("tanggal_lahir")
            tanggal_lahir=intent.getStringExtra("tanggal_lahir")

            if (txtrm.text.toString()=="" || tvtl.text.toString()==""){
                txtrm.error="Kolom Harus Diisi"
                tvtl.error="Kolom Harus Diisi"
            }
            else{
                presenter.cekrm(intent.getStringExtra("norm"),tanggal_lahir.toString())
            }
        }
        else{
            txtrm.setText("")
            tvtl.text=""
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
                tvtl.text = ("$dayStr-$mon-$year")
                tanggal_lahir = "$year-$monthStr-$dayStr"
            }
        })
    }

    override fun onSuccessLogin(
        msg: String?,
        pasien_id: String?,
        pasien_rm: String?,
        pasien_nama: String?,
        pasien_tl: String?,
        pasien_alamat: String?,
        cust_usr_no_identitas: String?,
        cust_usr_no_jaminan: String?
    ) {
        txtrmhasil.setText(pasien_rm)
        txtnama.setText(pasien_nama)
        txttgllahir.setText(pasien_tl)
        txtalamat.setText(pasien_alamat)
        txthp.setText(Utils.nohp)
        pasienid=pasien_id
        nm_px=pasien_nama
        rm_px=pasien_rm
    }

    override fun onFailedLogin(msg: String?) {
       alert {
           message=msg.toString()
       }.show()
    }

    override fun onFailure(msg: String?) {

    }

    override fun onSuccessPendaftaran(
        msg: String?,
        id_buffer: String?,
        noAntrian: String?,
        kdAntrian: String?
    ) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Informasi")
            builder.setMessage(msg)
                .setPositiveButton("Ya",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                        val tiketintent= Intent(this, TicketViewActivity::class.java)
                        tiketintent.putExtra("buffer_id",id_buffer)
                        tiketintent.putExtra("no_antrian",noAntrian)
                        tiketintent.putExtra("nm_poli",intent.getStringExtra("poli"))
                        tiketintent.putExtra("nm_dokter",intent.getStringExtra("nm_dokter"))
                        tiketintent.putExtra("jam",intent.getStringExtra("jam"))
                        tiketintent.putExtra("tanggal",intent.getStringExtra("tanggal"))
                        tiketintent.putExtra("jenis_px",intent.getStringExtra("jenispx"))
                        tiketintent.putExtra("nm_px",nm_px)
                        tiketintent.putExtra("rm_px",rm_px)
                        tiketintent.putExtra("kode_buffer",kdAntrian)
                        startActivity(tiketintent)


                    })
                .setNegativeButton("Nanti Saja",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        val intent= Intent(applicationContext, MainActivity::class.java)


                        startActivity(intent)

                    })

//            .setNegativeButton("Asuransi",
//                DialogInterface.OnClickListener { dialog, id ->
//                    // User cancelled the dialog
//                    dialog.dismiss()
//                })
            builder.show()






    }

    override fun onFailedRPendaftaran(msg: String?) {
        alert {
            message="Gagal"
        }.show()

    }

    override fun onFullRPendaftaran(msg: String?) {
        alert {
            message=msg.toString()
        }.show()
    }


}