package com.softwarepintarcom.rumkitbanmlg.pekerjaan

import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class PekerjaanPresenter(val pekerjaanView: PekerjaanView) {
    fun getPekerjaan(){
        NetworkConfig.getService()
            .get_pekerjaan()
            .enqueue(object :retrofit2.Callback<PekerjaanResponse>{
                override fun onResponse(
                    call: Call<PekerjaanResponse>,
                    response: Response<PekerjaanResponse>
                ) {
                    if (response.isSuccessful){
                        val status= response.body()?.value
                        if (status==202){
                            pekerjaanView.OnSuccesJob(response.body()?.result)
                        }
                        else{
                            pekerjaanView.OnFailedJob("Status $status")

                        }
                    }
                }

                override fun onFailure(call: Call<PekerjaanResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}