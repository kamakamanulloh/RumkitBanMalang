package com.softwarepintarcom.rumkitbanmlg.pasien


import PasienResponse
import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasienPresenter(val cekRMView: CekRMView) {
    fun cekrm(norm : String,tgl : String){
        NetworkConfig.getService()
            .cek_rm(norm,tgl)
            .enqueue(object : Callback<PasienResponse>{
                override fun onFailure(call: Call<PasienResponse>, t: Throwable) {
                    cekRMView.onFailure("Failure")
                }

                override fun onResponse(
                    call: Call<PasienResponse>,
                    response: Response<PasienResponse>) {
                    if (response.body()?.value == 202){
                        cekRMView.onSuccessLogin(response.body()?.message,response.body()?.cust_usr_id
                        ,response.body()?.pasien_rm,response.body()?.pasien_nama,response.body()?.pasien_tgllahir
                        ,response.body()?.pasien_alamat, response.body()?.cust_usr_no_identitas,response.body()?.cust_usr_no_jaminan)
                    }
                    else{
                        cekRMView.onFailedLogin(response.body()?.message)
                    }

                }

            })

    }



}