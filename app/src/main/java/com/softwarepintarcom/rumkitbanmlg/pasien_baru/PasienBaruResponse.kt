package com.softwarepintarcom.rumkitbanmlg.pasien_baru
import com.google.gson.annotations.SerializedName

data class PasienBaruResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("login_custusr_kode")
    val login_custusr_kode: String? = null,

    @field:SerializedName("tanggal_lahir")
    val tanggal_lahir: String? = null,

    @field:SerializedName("cust_usr_id")
    val cust_usr_id: String? = null,




    @field:SerializedName("value")
    val value: Int? = null
)
