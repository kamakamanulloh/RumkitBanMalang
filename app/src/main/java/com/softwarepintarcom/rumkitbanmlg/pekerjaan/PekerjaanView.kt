package com.softwarepintarcom.rumkitbanmlg.pekerjaan

interface PekerjaanView {
    fun OnSuccesJob(data:List<PekerjaanResultItem?>?)
    fun OnFailedJob(msg:String)
}