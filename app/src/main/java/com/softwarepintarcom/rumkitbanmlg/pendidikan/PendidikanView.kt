package com.softwarepintarcom.rumkitbanmlg.pendidikan

interface PendidikanView {
    fun onSuccessGetPendidikan(data: List<PendidikanResultItem?>?)
    fun onFailedGetPendidikan(msg : String)
    fun onFailureGetPendidikan(msg : String)
}