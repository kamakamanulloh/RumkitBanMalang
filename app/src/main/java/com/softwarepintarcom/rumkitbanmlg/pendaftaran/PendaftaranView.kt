package com.softwarepintarcom.rumkitbanmlg.pendaftaran

interface PendaftaranView {
    fun onSuccessPendaftaran(msg:String?,id_buffer:String?,noAntrian:String?,kdAntrian:String?)
    fun onFailedRPendaftaran(msg: String?)
    fun onFullRPendaftaran(msg: String?)
}