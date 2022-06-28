package com.softwarepintarcom.rumkitbanmlg.registrasi

import com.softwarepintarcom.rumkitbanmlg.api.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPresenter (val registerView: RegisterView){

    fun register( login_cust_nik:String?,username:String?,no_hp:String?,
                  email:String?,password:String?,jk:String?,tl:String?,kota:String?,
                  alamat:String?){
        NetworkConfig.getService()
            .register(login_cust_nik,username,no_hp,email,password,jk,tl,kota, alamat)
            .enqueue(object : Callback<RegisterResults> {
                override fun onFailure(call: Call<RegisterResults>, t: Throwable) {
                    registerView.onFailedRegister(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<RegisterResults>,
                    response: Response<RegisterResults>
                ) {
                    if ( response.body()?.value == 200){
                        registerView.onSuccessRegister(response.body()?.message)
                    } else{
                        registerView.onFailedRegister(response.body()?.message)
                    }
                }

            })
    }
}
