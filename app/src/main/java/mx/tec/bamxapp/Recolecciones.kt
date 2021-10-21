package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Almacenes
import mx.tec.bamxapp.model.EntregasAlmacen
import mx.tec.bamxapp.model.Recoleccion
import mx.tec.bamxapp.model.RecoleccionesRetrofit
import mx.tec.bamxapp.service.APIEntregasAlmacen
import mx.tec.bamxapp.service.APIRecolecciones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Recolecciones:AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recolecciones)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val routeID = sharedPreferences.getInt("route_id", 0)

        back.setOnClickListener(this@Recolecciones)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Recolecciones, MainMenu::class.java)
            startActivity(intent)
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosInventarioRetrofit: RecoleccionesRetrofit
        val listAlmacenes = findViewById<ListView>(R.id.list_recolecciones)
        var adapter: RecoleccionAdapter
        val almacenesArray = mutableListOf<Recoleccion>()

        val service = retrofit.create<APIRecolecciones>(APIRecolecciones::class.java)
        service.getRecolecciones(routeID).enqueue(object: Callback<RecoleccionesRetrofit> {
            override fun onResponse(
                call: Call<RecoleccionesRetrofit>,
                response: Response<RecoleccionesRetrofit>
            ) {
                datosInventarioRetrofit = response.body()!!
                for(i in datosInventarioRetrofit.data.indices){
                    val temp = Recoleccion(datosInventarioRetrofit.data[i].numRecoleccion,
                    datosInventarioRetrofit.data[i].socio,
                        datosInventarioRetrofit.data[i].socio_id,
                    datosInventarioRetrofit.data[i].determinante,
                    datosInventarioRetrofit.data[i].direccion,
                    datosInventarioRetrofit.data[i].descripcion,
                    datosInventarioRetrofit.data[i].latitude,
                    datosInventarioRetrofit.data[i].longitude)
                    almacenesArray.add(temp)
                }
                adapter = RecoleccionAdapter(this@Recolecciones, R.layout.recoleccion_layout, almacenesArray)
                listAlmacenes.adapter = adapter
            }

            override fun onFailure(call: Call<RecoleccionesRetrofit>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })


        ////////////////////////////////


//        val listRecolecciones = findViewById<ListView>(R.id.list_recolecciones)
//        //val prueba = findViewById<Button>(R.id.btn_inv_prueba)
//
//        val datosRecolecciones = listOf(
//
//            Recoleccion("1","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
//                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"),
//            Recoleccion("2","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
//                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"),
//            Recoleccion("3","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
//                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"))
//
//        val adapter = RecoleccionAdapter(this, R.layout.recoleccion_layout, datosRecolecciones)
//        listRecolecciones.adapter = adapter


    }
    override fun onClick(p0: View?) {
        //
    }
}