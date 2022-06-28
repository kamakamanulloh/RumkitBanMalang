package com.softwarepintarcom.rumkitbanmlg.pendaftaran

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalAdapter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalPresenter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalView
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.jadwal_dokter.JadwalResultItem
import com.softwarepintarcom.rumkitbanmlg.jumlah.JumlahPresenter
import com.softwarepintarcom.rumkitbanmlg.jumlah.JumlahView
import com.softwarepintarcom.rumkitbanmlg.pasien.PasienActivity2
import com.softwarepintarcom.rumkitbanmlg.poli.ListPoliPresenter
import com.softwarepintarcom.rumkitbanmlg.poli.ListPoliView
import com.softwarepintarcom.rumkitbanmlg.poli.PoliAdapter
import com.softwarepintarcom.rumkitbanmlg.poli.PoliResultItem
import kotlinx.android.synthetic.main.fragment_awal.*

import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AwalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AwalFragment : Fragment() , ListPoliView, JadwalView, JumlahView {

    private lateinit var poliAdapter: PoliAdapter


    private   lateinit var jumlahPresenter: JumlahPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    private lateinit var listpolipresenter: ListPoliPresenter
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

    var norm:String?=null

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_awal, container, false)

//        when {
//            arguments?.getString("norm")!=null && arguments?.getString("id_cust_usr")!=null -> {
//
//
//            }
//        }


        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AwalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AwalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onDatePeriksa()
        listpolipresenter= ListPoliPresenter(this)
        jadwalPresenter= JadwalPresenter(this)
        jumlahPresenter= JumlahPresenter(this)
        btnLanjut.onClick {
//            val intent= Intent(applicationContext, PasienActivity::class.java)
//            intent.putExtra("poli_id",poli_id)
//            intent.putExtra("dokter_id",dokterid)
//            intent.putExtra("tanggal",tanggal)
//            startActivity(intent)
            dialog()
        }
//        alert {
//            message=arguments?.getString("norm").toString()+" "+arguments?.getString("id_cust_usr")
//        }.show()
        when{
            arguments?.getString("norm")!=null && arguments?.getString("tanggal_lahir")!=null->{
                norm=arguments?.getString("norm")
                tanggal_lahir=arguments?.getString("tanggal_lahir")
            }
            else->{
                norm=""
                tanggal_lahir=""
            }

        }

    }

    private fun onDatePeriksa() {

        val minDate = Calendar.getInstance()
        minDate.add(Calendar.DAY_OF_MONTH,1)
        tvtgl.minDate=minDate.timeInMillis
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 3)
        tvtgl.maxDate=maxDate.timeInMillis
        tvtgl.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dayStr = if (dayOfMonth < 10) "0${dayOfMonth}" else "${dayOfMonth}"
            val mon = month + 1
            val monthStr = if (mon < 10) "0${mon}" else "${mon}"

            tanggal = "$dayStr-$monthStr-$year"
            listpolipresenter.getPoli()
//            alert {
//                message="Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
//            }.show()

        }

    }

    private fun dialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Informasi")
        builder.setMessage("Pilih tipe pembayaran")
            .setPositiveButton("Umum",
                DialogInterface.OnClickListener { dialog, id ->
                    // FIRE ZE MISSILES!
                    val intent= Intent(requireContext(), PasienActivity2::class.java)
                    intent.putExtra("poli_id",poli_id)
                    intent.putExtra("dokter_id",dokterid)
                    intent.putExtra("tanggal",tanggal)
                    intent.putExtra("id_jadwal",jadwal_id)
                    intent.putExtra("nm_dokter",nm_dokter)
                    intent.putExtra("jam",jam)
                    intent.putExtra("poli",nm_poli)
                    intent.putExtra("norm",norm)
                    intent.putExtra("tanggal_lahir",tanggal_lahir)
                    intent.putExtra("jenispx","2")

                    startActivity(intent)


                })
            .setNegativeButton("BPJS",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    val intent= Intent(requireContext(), PasienActivity2::class.java)
                    intent.putExtra("poli_id",poli_id)
                    intent.putExtra("dokter_id",dokterid)
                    intent.putExtra("tanggal",tanggal)
                    intent.putExtra("id_jadwal",jadwal_id)
                    intent.putExtra("nm_dokter",nm_dokter)
                    intent.putExtra("jam",jam)
                    intent.putExtra("poli",nm_poli)
                    intent.putExtra("jenispx","5")
                    intent.putExtra("norm",norm)
                    intent.putExtra("tanggal_lahir",tanggal_lahir)
                    startActivity(intent)

                })

//            .setNegativeButton("Asuransi",
//                DialogInterface.OnClickListener { dialog, id ->
//                    // User cancelled the dialog
//                    dialog.dismiss()
//                })
        builder.show()



    }


    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        listpoli.adapter= PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data)

        listpoli.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listdokter.adapter=null

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                poli_id =data[position].poliId.toString()
                nm_poli=data[position].poliNama.toString()
                if (nm_poli.equals("Poli Umum")){
                    listdokter.adapter=null

                }
                else{
                    jadwalPresenter.getJadwal(poli_id!!, tanggal.toString())
                }




            }
        }

    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        listdokter.adapter= JadwalAdapter(requireContext(), data as List<JadwalResultItem>)

        listdokter.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                jadwal_id =data[position].jadwalDokterId.toString()
                dokterid=data[position].id_dokter.toString()
                nm_dokter=data[position].dokterNama.toString()
                jam=data[position].jadwalDokterJamMulai.toString()
                jumlahPresenter.getJumlah(jadwal_id)
                kuota=data[position].kuota.toString()




            }
        }
    }

    override fun onFailedGetJadwal(msg: String) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccessJumlah(msg: String) {
        jumlah=msg.toInt()
        val kuotajadwal=kuota?.toInt()
        val sisa= kuotajadwal?.minus(jumlah!!)
        jml.text = "Sisa Kuota : $sisa"
        tv_kuota.text="Kuota : $kuota"

    }

    override fun onFailedJumlah(msg: String) {
        jml.text = ""
    }
}