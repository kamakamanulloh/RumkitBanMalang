package com.softwarepintarcom.rumkitbanmlg.pendidikan

import com.google.gson.annotations.SerializedName

data class PendidikanResponse(

	@field:SerializedName("result")
	val result: List<PendidikanResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class PendidikanResultItem(

	@field:SerializedName("pendidikan_id")
	val pendidikanId: String? = null,

	@field:SerializedName("pendidikan_nama")
	val pendidikanNama: String? = null
)
