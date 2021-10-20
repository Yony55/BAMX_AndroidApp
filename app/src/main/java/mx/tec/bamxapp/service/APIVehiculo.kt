package mx.tec.bamxapp.service

import mx.tec.bamxapp.model.Vehiculo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIVehiculo {
    @GET("vehicle/")
    fun getVehiculo(@Query("id") id: Int): Call<Vehiculo>
}