package com.softwarepintarcom.rumkitbanmlg.registrasi

import com.google.gson.annotations.SerializedName

data class RegisterResults(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)