package com.softwarepintarcom.rumkitbanmlg.hubungan

interface StatusHubView {
    fun onGetHub(data: List<HubunganResultItem?>?)
    fun onFailedGetHub(msg : String)
}