package com.softwarepintarcom.rumkitbanmlg.profil

interface ProfilView {
    fun onProfil (

        id: String?,
        username: String?,
        nohp: String?,
        nama_depan:String?,
        nama_belakang:String?,
        password:String?,
        noRM:String?,
        cust_usr_id:String?,
        cust_usr_nama:String?,
        tanggal_lahir:String?
    )
    fun onFailed(
        msg:String?
    )
}