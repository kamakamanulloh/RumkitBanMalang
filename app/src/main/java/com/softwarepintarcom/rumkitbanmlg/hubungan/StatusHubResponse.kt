package com.softwarepintarcom.rumkitbanmlg.hubungan

import com.google.gson.annotations.SerializedName

data class StatusHubResponse(

    @field:SerializedName("result")
	val result: List<HubunganResultItem?>? = null,

    @field:SerializedName("value")
	val value: Int? = null
)

data class HubunganResultItem(

	@field:SerializedName("status_pj_id")
	val statusPjId: String? = null,

	@field:SerializedName("status_pj_nama")
	val statusPjNama: String? = null
)
