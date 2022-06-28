package com.softwarepintarcom.rumkitbanmlg.jadwal_dokter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalPresenter
import com.itrsiam.rsiamuslimat.jadwal_dokter.JadwalView
import com.softwarepintarcom.rumkitbanmlg.DatePickerFragment
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.poli.ListPoliPresenter
import com.softwarepintarcom.rumkitbanmlg.poli.ListPoliView
import com.softwarepintarcom.rumkitbanmlg.poli.PoliAdapter
import com.softwarepintarcom.rumkitbanmlg.poli.PoliResultItem
import kotlinx.android.synthetic.main.fragment_awal.tvtgl
import kotlinx.android.synthetic.main.fragment_jadwal.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JadwalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JadwalFragment : Fragment(), ListPoliView, JadwalView {
    private lateinit var poliAdapter: PoliAdapter
    private lateinit var listpolipresenter: ListPoliPresenter
    private lateinit var jadwalPresenter: JadwalPresenter
    var tanggal: String? =null
    var poli_id: String? =null
    lateinit var datePicker: DatePickerFragment
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
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JadwalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JadwalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onDate()
        listpolipresenter= ListPoliPresenter(this)
        listpolipresenter.getPoli()
        datePicker = DatePickerFragment(requireContext(), true)
        jadwalPresenter= JadwalPresenter(this)
        jadwalPresenter.getJadwal(poli_id.toString(),tanggal.toString())
        btnCari.onClick {
            jadwalPresenter.getJadwal(poli_id.toString(),tanggal.toString())
        }
    }

    private fun onDate() {

        val minDate = Calendar.getInstance()

        tvtgl.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dayStr = if (dayOfMonth < 10) "0${dayOfMonth}" else "$dayOfMonth"
            val mon = month + 1
            val monthStr = if (mon < 10) "0${mon}" else "${mon}"

            tanggal = "$year-$mon-$dayOfMonth"
            listpolipresenter.getPoli()
//            alert {
//                message="Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
//            }.show()

        }
    }

    override fun onSuccessGet(data: List<PoliResultItem?>?) {
        list_poli.adapter=PoliAdapter(requireContext(), data as List<PoliResultItem>)
        poliAdapter=PoliAdapter(requireContext(), data as List<PoliResultItem>)

        list_poli.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                poli_id =data[position].poliId.toString()


            }
        }
    }

    override fun onFailedGet(msg: String) {

    }

    override fun onGetJadwal(data: List<JadwalResultItem?>?) {
        rv_jadwal.adapter=JadwalListAdapter(data as List<JadwalResultItem>,object :JadwalListAdapter.onClickItem{
            override fun clicked(item: JadwalResultItem?) {

            }

        })

    }

    override fun onFailedGetJadwal(msg: String) {
        requireContext().alert {
            message="gagal"
        }.show()

    }

}