package com.itrsiam.rsiamuslimat.jadwal_dokter

import com.softwarepintarcom.rumkitbanmlg.jadwal_dokter.JadwalResultItem

interface JadwalView {
    fun onGetJadwal(data: List<JadwalResultItem?>?)
    fun onFailedGetJadwal(msg : String)

}