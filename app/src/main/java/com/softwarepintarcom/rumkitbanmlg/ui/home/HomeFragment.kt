package com.softwarepintarcom.rumkitbanmlg.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.softwarepintarcom.rumkitbanmlg.R
import com.softwarepintarcom.rumkitbanmlg.list_tiket.TiketFragment
import com.softwarepintarcom.rumkitbanmlg.pasien_baru.PasienBaruFragment
import com.softwarepintarcom.rumkitbanmlg.pendaftaran.AwalFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_PasienLama.onClick {
            var awalActivity = AwalFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, awalActivity)
                ?.addToBackStack(null)
                ?.commit()
        }
        btncekantrian.onClick {
            var tiketFragment = TiketFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, tiketFragment)
                ?.addToBackStack(null)
                ?.commit()

        }
        btn_PasienBaru.onClick {
            var BaruFragment = PasienBaruFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, BaruFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}