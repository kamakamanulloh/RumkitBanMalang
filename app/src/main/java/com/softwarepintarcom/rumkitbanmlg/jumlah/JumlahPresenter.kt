package com.softwarepintarcom.rumkitbanmlg.jumlah

import android.util.Log

import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JumlahPresenter(val jumlahView: JumlahView) {
    fun getJumlah(id_jadwal: String?){
      NetworkConfig.getService()
            .get_Kuota(id_jadwal )
            .enqueue(object : Callback<JumlahResponse>{
                override fun onFailure(call: Call<JumlahResponse>, t: Throwable) {
                    jumlahView.onFailedJumlah(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(
                    call: Call<JumlahResponse>,
                    response: Response<JumlahResponse>
                ) {
                    if(response.isSuccessful){
                      jumlahView.onSuccessJumlah(response.body()?.value.toString())
                    }
                }

            })
    }
}