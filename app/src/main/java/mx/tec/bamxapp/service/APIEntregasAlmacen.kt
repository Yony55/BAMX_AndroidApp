package mx.tec.bamxapp.service

import mx.tec.bamxapp.model.EntregasAlmacen
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEntregasAlmacen {
    @GET("get-entregas")
    fun getEntregas(@Query("route_id") id: Int): Call<EntregasAlmacen>
}