package com.softwarepintarcom.rumkitbanmlg.profil

import com.google.gson.annotations.SerializedName

data class ProfilResponse(

	@field:SerializedName("nama_depan")
	val namaDepan: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("no_hp")
	val noHp: String? = null,

	@field:SerializedName("cust_usr_id")
	val custUsrId: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("cust_usr_kode")
	val custUsrKode: String? = null,

	@field:SerializedName("value")
	val value: Int? = null,

	@field:SerializedName("nama_belakang")
	val namaBelakang: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("cust_usr_nama")
	val custUsrNama: String? = null,

	@field:SerializedName("cust_usr_tanggal_lahir")
	val custUsrTanggalLahir: String? = null
)
