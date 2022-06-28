package com.softwarepintarcom.rumkitbanmlg.login

import com.google.gson.annotations.SerializedName
import com.itrsiam.rsiamuslimat.login.User

data class LoginResponse(

	@field:SerializedName("message")
	val message: String? = null,
	@field:SerializedName("value")
	val value: Int? = null,
	@field:SerializedName("results")
	val results: User? = null,
	@field:SerializedName("id")
	val id: String?=null,
	@field:SerializedName("username")
	val username: String?=null,
	@field:SerializedName("no_hp")
	val no_hp: String?=null,
	@field:SerializedName("nama_depan")
	val nama_depan: String?=null,
	@field:SerializedName("nama_belakang")
	val nama_belakang: String?=null,
	@field:SerializedName("password")
	val password: String?=null



)


