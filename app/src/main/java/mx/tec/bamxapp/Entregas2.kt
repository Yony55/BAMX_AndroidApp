package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.*
import mx.tec.bamxapp.service.APIEntregasAlmacen
import mx.tec.bamxapp.service.APIRecolecciones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Entregas2: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas2)

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val routeId = sharedPreferences.getInt("route_id", 0)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosEntregasRetrofit: EntregasAlmacen
        val listEntregas = findViewById<ListView>(R.id.list_entregas)
        var adapter: EntregaAdapter
        val entregasArray = mutableListOf<Entrega>()

        val service = retrofit.create<APIEntregasAlmacen>(APIEntregasAlmacen::class.java)
        service.getEntregas(routeId).enqueue(object: Callback<EntregasAlmacen> {
            override fun onResponse(
                call: Call<EntregasAlmacen>,
                response: Response<EntregasAlmacen>
            ) {
                datosEntregasRetrofit = response.body()!!
                for(i in datosEntregasRetrofit.data.indices) {
                    val temp = Entrega(
                        datosEntregasRetrofit.data[i].numEntrega,
                        datosEntregasRetrofit.data[i].bodega,
                        datosEntregasRetrofit.data[i].direccion,
                        datosEntregasRetrofit.data[i].descripcion)
                    entregasArray.add(temp)
                }
                adapter = EntregaAdapter(this@Entregas2, R.layout.entrega_layout, entregasArray)
                listEntregas.adapter = adapter
            }

            override fun onFailure(call: Call<EntregasAlmacen>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })


        ////////

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
//        val listEntregas = findViewById<ListView>(R.id.list_entregas)
//
        back.setOnClickListener(this@Entregas2)
//
//        val datosEntregas = listOf(
//            Entrega("1", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
//                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
//            Entrega("2", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
//                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
//            Entrega("3", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
//                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
//            Entrega("4", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
//                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg")
//        )

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Entregas2, MainMenu::class.java)
            startActivity(intent)
        }

//        val adapter = EntregaAdapter(this, R.layout.entrega_layout, datosEntregas)
//        listEntregas.adapter = adapter
    }

    override fun onClick(p0: View?) {
        //
    }
}