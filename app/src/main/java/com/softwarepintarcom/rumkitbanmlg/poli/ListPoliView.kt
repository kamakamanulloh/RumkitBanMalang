package com.softwarepintarcom.rumkitbanmlg.poli

interface ListPoliView {
    fun onSuccessGet(data: List<PoliResultItem?>?)
    fun onFailedGet(msg : String)

}