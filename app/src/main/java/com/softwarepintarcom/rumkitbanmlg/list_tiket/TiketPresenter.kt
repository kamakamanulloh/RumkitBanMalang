package com.itrsiam.rsiamuslimat.list_tiket

import android.util.Log
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TiketPresenter (val tiketView: TiketView) {
    fun getTiket(id_login:String){
        NetworkConfig.getService()
            .get_Tiket(id_login)
            .enqueue(object : Callback<TiketResponse>{
                override fun onFailure(call: Call<TiketResponse>, t: Throwable) {
                    tiketView.onFailedGetTiket(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }

                override fun onResponse(
                    call: Call<TiketResponse>,
                    response: Response<TiketResponse>
                ) {
                    if (response.isSuccessful){
                        val status= response.body()?.value
                        if (status==1){
                            val results= response.body()!!.result
                            tiketView.onGetTiket(results)
                        }
                        else{
                            tiketView.onFailedGetTiket("Error $status")
                        }
                    }

                }

            })
    }





}