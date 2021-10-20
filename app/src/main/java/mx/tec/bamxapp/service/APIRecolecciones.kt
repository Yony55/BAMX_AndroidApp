package mx.tec.bamxapp.service

import mx.tec.bamxapp.model.RecoleccionesRetrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRecolecciones {
    @GET("get-recolecciones")
    fun getRecolecciones(@Query("route_id") id: Int): Call<RecoleccionesRetrofit>
}