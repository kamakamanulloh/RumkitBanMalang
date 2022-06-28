package com.softwarepintarcom.rumkitbanmlg.list_tiket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itrsiam.rsiamuslimat.list_tiket.TiketPresenter
import com.itrsiam.rsiamuslimat.list_tiket.TiketResults
import com.itrsiam.rsiamuslimat.list_tiket.TiketView
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.api.Utils
import com.softwarepintarcom.rumkitbanmlg.pasien.TicketViewActivity
import kotlinx.android.synthetic.main.fragment_tiket.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.support.v4.startActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TiketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNCHECKED_CAST")
class TiketFragment : Fragment(),TiketView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var presenter: TiketPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter= TiketPresenter(this)
        presenter.getTiket(Utils.user_id!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TiketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TiketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onGetTiket(data: List<TiketResults?>?) {
        progress_bar.visibility=(View.GONE)

        rv.adapter= TiketAdapter(data as List<TiketResults>,object : TiketAdapter.onClickItem{
            override fun clicked(item: TiketResults?) {
                startActivity<TicketViewActivity>("dataItem" to item)


            }
        })

    }

    override fun onFailedGetTiket(msg: String) {
        progress_bar.visibility=(View.GONE)
        context?.alert {


            message=msg
        }?.show()
    }

    override fun onGet(msg: String) {

    }

    override fun onFailed(msg: String) {

    }
}