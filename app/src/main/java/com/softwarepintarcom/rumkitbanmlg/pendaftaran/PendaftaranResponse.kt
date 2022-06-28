package com.softwarepintarcom.rumkitbanmlg.pendaftaran
import com.google.gson.annotations.SerializedName

data class PendaftaranResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("noAntrian")
	val noAntrian: String? = null,

	@field:SerializedName("buffer_id")
	val reg_buffer_id: String? = null,


	@field:SerializedName("value")
	val value: Int? = null,


	@field:SerializedName("kode_buffer")
	val kode_buffer: String? = null
)
