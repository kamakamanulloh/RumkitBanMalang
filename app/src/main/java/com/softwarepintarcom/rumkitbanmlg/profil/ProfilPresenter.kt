package com.softwarepintarcom.rumkitbanmlg.profil

import android.util.Log
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class ProfilPresenter(val profilView: ProfilView) {
    fun getProfil(user_id:String?){
        NetworkConfig.getService()
            .get_Profil(user_id)
            .enqueue(object :retrofit2.Callback<ProfilResponse>{
                override fun onResponse(
                    call: Call<ProfilResponse>,
                    response: Response<ProfilResponse>
                ) {
                    if (response.isSuccessful){
                        val status= response.body()?.value
                        if(status==202){
                            profilView.onProfil(response.body()!!.id, response.body()!!.username,
                            response.body()!!.noHp,response.body()!!.namaDepan, response.body()!!.namaBelakang,
                            response.body()!!.password, response.body()!!.custUsrKode,
                                response.body()!!.custUsrId,
                            response.body()!!.custUsrNama, response.body()!!.custUsrTanggalLahir)
                        }
                        else{
                            profilView.onFailed("Error $status")
                        }
                    }
                }

                override fun onFailure(call: Call<ProfilResponse>, t: Throwable) {
                    profilView.onFailed(t.localizedMessage)
                    Log.d("Error","Error Data")
                }

            })
    }
}