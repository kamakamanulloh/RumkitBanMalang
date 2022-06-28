package com.softwarepintarcom.rumkitbanmlg.pasien_baru

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.softwarepintarcom.rumkitbanmlg.DatePickerFragment
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.agama.AgamaAdapter
import com.softwarepintarcom.rumkitbanmlg.agama.AgamaPresenter
import com.softwarepintarcom.rumkitbanmlg.agama.AgamaResultItem
import com.softwarepintarcom.rumkitbanmlg.agama.AgamaView
import com.softwarepintarcom.rumkitbanmlg.api.Utils
import com.softwarepintarcom.rumkitbanmlg.hubungan.HubunganResultItem
import com.softwarepintarcom.rumkitbanmlg.hubungan.StatusHubAdapter
import com.softwarepintarcom.rumkitbanmlg.hubungan.StatusHubPresenter
import com.softwarepintarcom.rumkitbanmlg.hubungan.StatusHubView
import com.softwarepintarcom.rumkitbanmlg.pekerjaan.PekerjaanAdapter
import com.softwarepintarcom.rumkitbanmlg.pekerjaan.PekerjaanPresenter
import com.softwarepintarcom.rumkitbanmlg.pekerjaan.PekerjaanResultItem
import com.softwarepintarcom.rumkitbanmlg.pekerjaan.PekerjaanView
import com.softwarepintarcom.rumkitbanmlg.pendaftaran.AwalFragment
import com.softwarepintarcom.rumkitbanmlg.pendidikan.PendidikanAdapter
import com.softwarepintarcom.rumkitbanmlg.pendidikan.PendidikanPresenter
import com.softwarepintarcom.rumkitbanmlg.pendidikan.PendidikanResultItem
import com.softwarepintarcom.rumkitbanmlg.pendidikan.PendidikanView
import kotlinx.android.synthetic.main.fragment_pasien_baru.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasienBaruFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNCHECKED_CAST")
class PasienBaruFragment : Fragment(),PekerjaanView,AgamaView,PendidikanView,StatusHubView,PasienBaruView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var pendidikanPresenter: PendidikanPresenter
    lateinit var pekerjaanPresenter: PekerjaanPresenter
    lateinit var agamaPresenter: AgamaPresenter
    lateinit var pasienBaruPresenter: PasienBaruPresenter
    lateinit var datePicker: DatePickerFragment
    lateinit var statusHubPresenter: StatusHubPresenter
    var tanggal_lahir: String? =null
    private lateinit var agamaAdapter: AgamaAdapter
    lateinit var pendidikanAdapter: PendidikanAdapter
    lateinit var pekerjaanAdapter: PekerjaanAdapter
    lateinit var statusHubAdapter: StatusHubAdapter
    var agamaId:String?=null
    var pekerjaanId:String?=null
    var pendidikanId:String?=null
    var hubId:String?=null

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
        return inflater.inflate(R.layout.fragment_pasien_baru, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        agamaPresenter= AgamaPresenter(this)
        pekerjaanPresenter= PekerjaanPresenter(this)
        pendidikanPresenter= PendidikanPresenter(this)
        agamaPresenter.getAgama()
        pekerjaanPresenter.getPekerjaan()
        pendidikanPresenter.getPendidikan()
        statusHubPresenter= StatusHubPresenter(this)
        statusHubPresenter.getHub()
        pasienBaruPresenter=PasienBaruPresenter(this)

        datePicker = DatePickerFragment(requireContext(), true)
        btntgllahir.onClick {
            onDate()
        }
        btnsimpan.onClick {
            when {
                txtnmpasien.text?.isEmpty()!! -> {
                    txtnmpasien.error = "Kolom Harus Diisi"
                }
                txtnoid.text?.isEmpty()!! -> {
                    txtnoid.error = "Kolom Harus Diisi"
                }
                txtnohp.text?.isEmpty()!! -> {
                    txtnmpasien.error = "Kolom Harus Diisi"
                }
                txt_tempat_lahir.text?.isEmpty()!!->{
                    txt_tempat_lahir.error="Kolom Harus Diisi"
                }
                tvtl.text?.isEmpty()!!->{
                    tvtl.error="Kolom Harus Diisi"
                }
                else-> pasienBaruPresenter.regis_pxbaru(
                    txtnmpasien.text.toString(),
                    spn_jenisId.selectedItem.toString(),
                    txtnoid.text.toString(),
                    txtrtrw.text.toString(),
                    txtdesa.text.toString(),
                    txtnohp.text.toString(),
                    txtkab.text.toString(),
                    tanggal_lahir,
                    txt_tempat_lahir.text.toString(),
                    txnobpjs.text.toString(),
                    agamaId,
                    pendidikanId,
                    pekerjaanId,
                    txtprov.text.toString(),
                    txtkec.text.toString(),
                    txtnmpenganuggungjawab.text.toString(),
                    hubId,
                    spn_gender.selectedItem.toString(),
                    Utils.user_id
                )
            }

        }
    }

    private fun onDate() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        val minDate = Calendar.getInstance()

        datePicker.showDialog(d, m, y, object : DatePickerFragment.Callback {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                tvtl.text = ("$dayStr-$mon-$year")
                tanggal_lahir = "$dayStr-$monthStr-$year"

            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PasienBaruFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasienBaruFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSuccessGetAgama(data: List<AgamaResultItem?>?) {
        listAgama.adapter=AgamaAdapter(requireContext(), data as List<AgamaResultItem>)
        agamaAdapter= AgamaAdapter(requireContext(), data)

        listAgama.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listAgama.adapter=null

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                agamaId =data[position].agmId.toString()




            }
        }
    }

    override fun onFailedGetAgama(msg: String) {

    }

    override fun onFailureGetAgama(msg: String) {

    }

    override fun onSuccessGetPendidikan(data: List<PendidikanResultItem?>?) {
       listPendidikan.adapter=PendidikanAdapter(
           requireContext(),
           data as List<PendidikanResultItem>
       )
        pendidikanAdapter= PendidikanAdapter(requireContext(), data)
        listPendidikan.onItemSelectedListener
        object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listPendidikan.adapter=null

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pendidikanId =data[position].pendidikanId.toString()




            }
        }
    }

    override fun onFailedGetPendidikan(msg: String) {

    }

    override fun onFailureGetPendidikan(msg: String) {

    }

    override fun OnSuccesJob(data: List<PekerjaanResultItem?>?) {
        listPekerjaan.adapter=PekerjaanAdapter(
            requireContext(),
            data as List<PekerjaanResultItem>
        )
        pekerjaanAdapter= PekerjaanAdapter(requireContext(), data)
        listPendidikan.onItemSelectedListener
        object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                listPekerjaan.adapter=null

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pekerjaanId =data[position].pekerjaanId.toString()




            }
        }
    }

    override fun OnFailedJob(msg: String) {

    }

    override fun onGetHub(data: List<HubunganResultItem?>?) {
        spn_hub.adapter=StatusHubAdapter(requireContext(), data as List<HubunganResultItem>)
        statusHubAdapter= StatusHubAdapter(requireContext(), data)
        spn_hub.onItemSelectedListener
        AdapterView.OnItemClickListener { _, _, position, _ -> hubId=data[position].statusPjId
        }



    }

    override fun onFailedGetHub(msg: String) {

    }

    override fun onSuccessPx(msg: String?,norm:String?,cust_usr_id:String?) {
        alert {
            message=msg.toString()
        }.show()
        var awalFragment = AwalFragment()
        val mBundle = Bundle()
//        mBundle.putString("id_cust_usr",cust_usr_id)
        mBundle.putString("norm",norm)
        mBundle.putString("tanggal_lahir",tanggal_lahir)
        awalFragment.arguments=mBundle
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment, awalFragment)
            ?.addToBackStack(null)
            ?.commit()


    }

    override fun onFailedPx(msg: String?) {
        alert {
            message="Gagal"
        }.show()
    }
}