package com.softwarepintarcom.rumkitbanmlg.pasien

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.itrsiam.rsiamuslimat.list_tiket.TiketPresenter
import com.itrsiam.rsiamuslimat.list_tiket.TiketResults
import com.itrsiam.rsiamuslimat.list_tiket.TiketView
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.softwarepintarcom.rumkitbanmlg.R
import kotlinx.android.synthetic.main.activity_ticket_view.*

class TicketViewActivity : AppCompatActivity(), TiketView {
    var multiFormatWriter: MultiFormatWriter = MultiFormatWriter()
    private lateinit var presenter: TiketPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_view)

        var jenis_px:String?=null
        presenter= TiketPresenter(this)
        val tiketResults=intent.getSerializableExtra("dataItem")

        if (tiketResults==null){

            try {
                val bitMatrix = multiFormatWriter.encode(
                    intent.getStringExtra("buffer_id"),
                    BarcodeFormat.QR_CODE,
                    500,
                    400
                )
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(bitMatrix)
                imgqrcode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

            when {
                intent.getStringExtra("jenis_px").equals("2") -> {
                    jenis_px="Umum"
                }
                intent.getStringExtra("jenis_px").equals("5") -> {
                    jenis_px="BPJS"
                }
                intent.getStringExtra("jenis_px").equals("7") -> {
                    jenis_px="Asuransi"
                }
            }

            tvnoantrian.text = "No Antrian "+intent.getStringExtra("no_antrian")
            tvklinik.text = intent.getStringExtra("nm_poli")
            tvdokter.text = intent.getStringExtra("nm_dokter")
            tv_tanggal.text = intent.getStringExtra("tanggal")+" ("+intent.getStringExtra("jam")+")"
            tvnmpasien.text = intent.getStringExtra("nm_px")
            tvrm.text = intent.getStringExtra("rm_px")
            tvcarabayar.text = jenis_px
            tvkodeantrian.text="Kode Booking "+intent.getStringExtra("buffer_id")

        }
        else if (tiketResults!=null){
            val item=tiketResults as TiketResults?

            try {
                val bitMatrix = multiFormatWriter.encode(
                    item?.regBufferId.toString(),
                    BarcodeFormat.QR_CODE,
                    400,
                    400
                )
                val encoder = BarcodeEncoder()
                val bitmap = encoder.createBitmap(bitMatrix)
                imgqrcode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }


            tvnoantrian.text =  "No Antrian "+item?.regBufferNoAntrian.toString()
            tvklinik.text = item?.poliNama.toString()
            tvdokter.text = item?.usrName.toString()
            tv_tanggal.text = item?.regBufferTanggal.toString()+" ("+ item?.regBufferWaktu.toString()+")"
            tvnmpasien.text = item?.custUsrNama.toString()
            tvrm.text = item?.custUsrKode.toString()
            tvcarabayar.text = item?.jenisNama.toString()
            tvkodeantrian.text="Kode Booking "+item?.regBufferId.toString()

        }


    }

    override fun onGetTiket(data: List<TiketResults?>?) {

    }

    override fun onFailedGetTiket(msg: String) {

    }

    override fun onGet(msg: String) {

    }

    override fun onFailed(msg: String) {

    }
}