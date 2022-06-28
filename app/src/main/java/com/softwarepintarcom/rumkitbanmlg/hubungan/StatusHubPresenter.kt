package com.softwarepintarcom.rumkitbanmlg.hubungan

import android.util.Log
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class StatusHubPresenter (val statusHubView: StatusHubView) {
    fun getHub(){
        NetworkConfig.getService()
            .get_hubungan()
            .enqueue(object :retrofit2.Callback<StatusHubResponse>{
                override fun onResponse(
                    call: Call<StatusHubResponse>,
                    response: Response<StatusHubResponse>
                ) {
                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            statusHubView.onGetHub(response.body()!!.result)
                        }
                        else{
                            statusHubView.onFailedGetHub("Gagal")
                        }
                    }
                }

                override fun onFailure(call: Call<StatusHubResponse>, t: Throwable) {
                    statusHubView.onFailedGetHub(t.localizedMessage)
                    Log.d("Error","Status Error")
                }

            })
    }

}