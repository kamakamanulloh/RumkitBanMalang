package com.softwarepintarcom.rumkitbanmlg.pasien_baru

interface PasienBaruView {
    fun onSuccessPx(msg:String?,norm:String?,cust_usr_id:String?)
    fun onFailedPx(msg: String?)

}