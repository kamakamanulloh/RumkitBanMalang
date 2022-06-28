package com.softwarepintarcom.rumkitbanmlg.poli

import android.util.Log

import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class ListPoliPresenter( val listPoliView: ListPoliView) {
    fun getPoli(){
        NetworkConfig.getService()
            .get_poli()
            .enqueue(object : retrofit2.Callback<PoliResponse>{
                override fun onFailure(call: Call<PoliResponse>, t: Throwable) {
                    listPoliView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(call: Call<PoliResponse>, response: Response<PoliResponse>) {
                    if(response.isSuccessful){
                        val status = response.body()?.value
                        if (status == 202){
                            val data = response.body()?.result
                            listPoliView.onSuccessGet(data)
                        } else{
                            listPoliView.onFailedGet("Error $status")
                        }
                    }
                }

            })

    }

}


