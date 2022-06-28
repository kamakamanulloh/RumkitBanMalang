package com.softwarepintarcom.rumkitbanmlg.agama

import android.util.Log
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class AgamaPresenter(val agamaView: AgamaView) {
    fun getAgama(){
        NetworkConfig.getService()
            .get_agama()
            .enqueue(object :retrofit2.Callback<AgamaResponse>{
                override fun onResponse(
                    call: Call<AgamaResponse>,
                    response: Response<AgamaResponse>
                ) {
                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            val data=response.body()?.result
                            agamaView.onSuccessGetAgama(data)
                        }
                        else{
                            agamaView.onFailedGetAgama("Error $status")
                        }


                    }

                }

                override fun onFailure(call: Call<AgamaResponse>, t: Throwable) {
                    agamaView.onFailureGetAgama(t.localizedMessage)
                    Log.d("Error","Error Data")
                }

            })
    }
}