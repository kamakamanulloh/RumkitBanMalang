package com.softwarepintarcom.rumkitbanmlg.agama

interface AgamaView {
    fun onSuccessGetAgama(data: List<AgamaResultItem?>?)
    fun onFailedGetAgama(msg : String)
    fun onFailureGetAgama(msg : String)
}