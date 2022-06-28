package com.softwarepintarcom.rumkitbanmlg.pasien_baru

import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasienBaruPresenter(val pasienBaruView: PasienBaruView) {
    fun regis_pxbaru(nama:String?,identitas:String?,noid:String?,
                     rtrw:String?,desa:String?,nohp:String?,tanggal_lahir:String?,tempat_lahir:String?,
                     nobpjs:String?,agama:String?,pendidikan:String?,pekerjaan:String?,provinsi:String?,
                     kota:String?,kecamatan:String?,penanggungjawab:String?,statushub:String?,gender:String?,id_login:String?) {
        NetworkConfig.getService()
            .regis_pxbaru(nama,identitas,noid,
        rtrw,desa,nohp,tanggal_lahir,tempat_lahir,
        nobpjs,agama,pendidikan,pekerjaan,provinsi,
        kota,kecamatan,penanggungjawab,statushub,gender,id_login)
            .enqueue(object : Callback<PasienBaruResponse> {
                override fun onFailure(call: Call<PasienBaruResponse>, t: Throwable) {
                    pasienBaruView.onFailedPx(t.localizedMessage)
                }
                override fun onResponse(
                    call: Call<PasienBaruResponse>,
                    response: Response<PasienBaruResponse>
                ) {
                    when (response.body()?.value) {
                        200 -> {
                            pasienBaruView.onSuccessPx(response.body()!!.message,
                                response.body()!!.login_custusr_kode,response.body()!!.cust_usr_id
                              )
                        }

                        else -> {
                            pasienBaruView.onFailedPx(response.body()?.message)
                        }
                    }

                }

            })

    }
}