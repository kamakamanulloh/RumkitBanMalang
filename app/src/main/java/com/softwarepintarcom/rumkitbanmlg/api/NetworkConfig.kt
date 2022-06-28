package com.softwarepintarcom.rumkitbanmlg.api

import PasienResponse
import com.itrsiam.rsiamuslimat.list_tiket.TiketResponse
import com.softwarepintarcom.rumkitbanmlg.agama.AgamaResponse
import com.softwarepintarcom.rumkitbanmlg.hubungan.StatusHubResponse
import com.softwarepintarcom.rumkitbanmlg.jadwal_dokter.JadwalResponse
import com.softwarepintarcom.rumkitbanmlg.jumlah.JumlahResponse
import com.softwarepintarcom.rumkitbanmlg.login.LoginResponse
import com.softwarepintarcom.rumkitbanmlg.pasien_baru.PasienBaruResponse
import com.softwarepintarcom.rumkitbanmlg.pekerjaan.PekerjaanResponse
import com.softwarepintarcom.rumkitbanmlg.pendaftaran.PendaftaranResponse
import com.softwarepintarcom.rumkitbanmlg.pendidikan.PendidikanResponse
import com.softwarepintarcom.rumkitbanmlg.poli.PoliResponse
import com.softwarepintarcom.rumkitbanmlg.profil.ProfilResponse
import com.softwarepintarcom.rumkitbanmlg.registrasi.RegisterResults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object NetworkConfig {

    fun getInterceptor():OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level=HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://202.164.223.126/api_regonline/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService()= getRetrofit().create(ApiService::class.java)
}
interface ApiService{
    @FormUrlEncoded
    @POST("registrasi.php")
    fun register(
        @Field("login_cust_nik") nik : String?,
        @Field("username") username : String?,
        @Field("no_hp")no_hp : String?,
        @Field("email")email : String?,
        @Field("password")password : String?,
        @Field("jk")jk : String?,
        @Field("tl") tl : String?,
        @Field("kota")kota : String?,
        @Field("alamat")alamat : String?

    ):
            retrofit2.Call<RegisterResults>


    @FormUrlEncoded
    @POST("edit_profil.php")
    fun update_profil(
        @Field("login_cust_id")login_cust_id : String?,
        @Field("username") username : String?,
        @Field("nama_depan")nama_depan : String?,
        @Field("nama_belakang")nama_belakang : String?,

        @Field("no_hp")nohp : String?
    ):
            retrofit2.Call<LoginResponse>

    @FormUrlEncoded
    @POST("update_password.php")
    fun update_password(
        @Field("password")password : String?,
        @Field("login_cust_id")login_cust_id : String?
    ):
            retrofit2.Call<LoginResponse>


    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username")username: String?,
        @Field("password")password: String?
    ):
            retrofit2.Call<LoginResponse>

    @GET("profil.php?user_id=")
    fun profil(
        @Query("user_id")user_id: String?
    ):
            retrofit2.Call<LoginResponse>

    @GET("list_poli.php")
    fun get_poli(

    ):
            retrofit2.Call<PoliResponse>

    @GET("listAgama.php")
    fun get_agama(

    ):
            retrofit2.Call<AgamaResponse>

    @GET("listPendidikan.php")
    fun get_pendidikan(

    ):
            retrofit2.Call<PendidikanResponse>



    @GET("listPekerjaan.php")
    fun get_pekerjaan(

    ):
            retrofit2.Call<PekerjaanResponse>


    @GET("list_hubungan.php")
    fun get_hubungan(

    ):
            retrofit2.Call<StatusHubResponse>



    @FormUrlEncoded
    @POST("list_jadwal.php")
    fun get_jadwal(
        @Field("id_poli")id_poli:String?,
        @Field("tanggal")tanggal:String?
    ):
            retrofit2.Call<JadwalResponse>


    @GET("kuota.php?id_jadwal=")
    fun get_Kuota(
        @Query("id_jadwal")id_jadwal: String?
    ):retrofit2.Call<JumlahResponse>


    @FormUrlEncoded
    @POST("cek.php")
    fun cek_rm(
        @Field("pasien_rm")pasien_rm: String?,
        @Field("tgl_lahir")tgl_lahir: String?

    ):
            retrofit2.Call<PasienResponse>


    @FormUrlEncoded
    @POST("regis_antrian.php")
    fun regis_pasien(
        @Field("id_dokter")id_dokter:String?,
        @Field("reg_buffer_jenis_pasien")reg_buffer_jenis_pasien:String?,
        @Field("reg_buffer_id_login")reg_buffer_id_login:String?,
        @Field("poli_id")poli_id:String?,
        @Field("nama_dokter")nama_dokter:String?,
        @Field("nm_poli")nm_poli:String?,
        @Field("reg_buffer_nobpjs")reg_buffer_nobpjs:String?,
        @Field("reg_buffer_tanggal")reg_buffer_tanggal:String?,
        @Field("jam")jam:String?,
        @Field("cust_usr_id")cust_usr_id:String?,
        @Field("reg_buffer_no_rujukan")reg_buffer_no_rujukan:String?,
        @Field("id_jadwal")id_jadwal:String?
    ):
            retrofit2.Call<PendaftaranResponse>

    @GET("history.php?id_login=")
    fun get_Tiket(
        @Query("id_login")id_login: String?
    ):retrofit2.Call<TiketResponse>

    @GET("profil.php?user_id=")
    fun get_Profil(
        @Query("user_id")user_id: String?
    ):retrofit2.Call<ProfilResponse>

    @FormUrlEncoded
    @POST("regis_pxbaru.php")
    fun regis_pxbaru(
        @Field("nama_lengkap")nama_lengkap:String?,
        @Field("identitas")identitas:String?,
        @Field("noid")noid:String?,
        @Field("rtrw")rtrw:String?,
        @Field("desa")desa:String?,
        @Field("nohp")nohp:String?,
        @Field("kota")kota:String?,
        @Field("tanggal_lahir")tanggal_lahir:String?,
        @Field("tempat_lahir")tempat_lahir:String?,

        @Field("nobpjs")nobpjs:String?,
        @Field("agama")agama:String?,
        @Field("pendidikan")pendidikan:String?,
        @Field("pekerjaan")pekerjaan:String?,
        @Field("provinsi")provinsi:String?,
        @Field("kecamatan")kecamatan:String?,
        @Field("penanggungjawab")penanggungjawab:String?,
        @Field("statushub")statushub:String?,
        @Field("gender")gender:String?,
        @Field("id_cust_login")id_cust_login:String?

    ):
            retrofit2.Call<PasienBaruResponse>


}