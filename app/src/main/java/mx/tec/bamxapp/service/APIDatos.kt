package mx.tec.bamxapp.service

import mx.tec.bamxapp.model.DatosRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIDatos {
    @GET("get-route")
    fun getDatos(@Query("user_id") id: Int): Call<DatosRetrofit>
}