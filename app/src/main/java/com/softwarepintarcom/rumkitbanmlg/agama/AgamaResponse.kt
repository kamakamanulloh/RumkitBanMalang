package com.softwarepintarcom.rumkitbanmlg.agama

import com.google.gson.annotations.SerializedName

data class AgamaResponse(

	@field:SerializedName("result")
	val result: List<AgamaResultItem?>? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class AgamaResultItem(

	@field:SerializedName("agm_id")
	val agmId: String? = null,

	@field:SerializedName("agm_nama")
	val agmNama: String? = null
)
