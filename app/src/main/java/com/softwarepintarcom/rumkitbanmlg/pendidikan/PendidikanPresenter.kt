package com.softwarepintarcom.rumkitbanmlg.pendidikan

import android.util.Log
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class PendidikanPresenter(val pendidikanView: PendidikanView) {
    fun getPendidikan(){
        NetworkConfig.getService()
            .get_pendidikan()
            .enqueue(object : retrofit2.Callback<PendidikanResponse>{
                override fun onResponse(
                    call: Call<PendidikanResponse>,
                    response: Response<PendidikanResponse>
                ) {
                    if (response.isSuccessful){
                        val status=response.body()?.value
                        if (status==202){
                            val data=response.body()?.result
                            pendidikanView.onSuccessGetPendidikan(data)
                        }
                        else{
                            pendidikanView.onFailedGetPendidikan("Error $status")
                        }


                    }

                }


                override fun onFailure(call: Call<PendidikanResponse>, t: Throwable) {
                    pendidikanView.onFailureGetPendidikan(t.localizedMessage)
                    Log.d("Error","Error Data")
                }

            })
    }
}