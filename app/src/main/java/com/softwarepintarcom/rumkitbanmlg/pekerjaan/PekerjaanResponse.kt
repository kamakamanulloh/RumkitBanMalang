package com.softwarepintarcom.rumkitbanmlg.pekerjaan

import com.google.gson.annotations.SerializedName

data class PekerjaanResponse(

	@field:SerializedName("result")
	val result: List<PekerjaanResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class PekerjaanResultItem(

	@field:SerializedName("pekerjaan_id")
	val pekerjaanId: String? = null,

	@field:SerializedName("pekerjaan_nama")
	val pekerjaanNama: String? = null
)
