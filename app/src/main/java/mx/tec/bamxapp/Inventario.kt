package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ListView
import mx.tec.bamxapp.model.Recoleccion
import mx.tec.bamxapp.model.RecoleccionesRetrofit
import mx.tec.bamxapp.model.Socio
import mx.tec.bamxapp.service.APIRecolecciones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Inventario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_inventario)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosRecoleccionesRetrofit: RecoleccionesRetrofit
        val listSocio = findViewById<ListView>(R.id.list_Socios)
        var adapter: SocioAdapter
        val sociosArray = mutableListOf<Socio>()

        val service = retrofit.create<APIRecolecciones>(APIRecolecciones::class.java)
        service.getRecolecciones(1).enqueue(object: Callback<RecoleccionesRetrofit> {
            override fun onResponse(
                call: Call<RecoleccionesRetrofit>,
                response: Response<RecoleccionesRetrofit>
            ) {
                datosRecoleccionesRetrofit = response.body()!!
                for(i in datosRecoleccionesRetrofit.data.indices){
                    val determinante = datosRecoleccionesRetrofit.data[i].determinante
                    if (determinante == null || determinante.isEmpty()){
                        val temp = Socio(R.drawable.boxicon,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)

                        sociosArray.add(temp)
                    }else if(determinante == "4544" || determinante == "1248" || determinante == "3906"){
                        val temp = Socio(R.drawable.superama,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    } else if(determinante == "3869" || determinante == "1345" ||
                        determinante == "3875" || determinante == "1841" || determinante == "3121" ||
                            determinante == "3823" || determinante == "1980" || determinante == "2658" ||
                            determinante == "5426" || determinante == "3126" || determinante == "1278"){
                        val temp = Socio(R.drawable.aurrera,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    } else if(determinante == "163" || determinante == "418" ||
                        determinante == "240" || determinante == "412"){
                        val temp = Socio(R.drawable.lacomer,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    } else if(determinante == "238" || determinante == "658"){
                        val temp = Socio(R.drawable.chedraui,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    } else if(determinante == "324" || determinante == "568" ||
                        determinante == "340" || determinante == "570"){
                        val temp = Socio(R.drawable.kfc,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    } else{
                        val temp = Socio(R.drawable.boxicon,
                            datosRecoleccionesRetrofit.data[i].socio,
                            datosRecoleccionesRetrofit.data[i].determinante,
                            datosRecoleccionesRetrofit.data[i].direccion,
                            datosRecoleccionesRetrofit.data[i].socio_id)
                        sociosArray.add(temp)
                    }
                }
                adapter = SocioAdapter(this@Inventario, R.layout.socio_layout, sociosArray)
                listSocio.adapter = adapter
            }

            override fun onFailure(call: Call<RecoleccionesRetrofit>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })


        ////

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
//        val listSocio = findViewById<ListView>(R.id.list_Socios)
//
//        val datosSocios = listOf(
//            Socio(R.drawable.superama, "Superama", "4544", "Manuel Ávila Camacho #606",),
//            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
//            Socio(R.drawable.lacomer, "La Comer", "163", "Enrique Flores Magón #27"),
//            Socio(R.drawable.fresko, "Fresko", "412", "Río Balsas #102"),
//            Socio(R.drawable.citymarket, "City Market", "418", "Río Yaqui #28"),
//            Socio(R.drawable.chedraui, "Chedraui", "238", "Paseo Cuauhnáhuac #16"),
//            Socio(R.drawable.kfc, "KFC", "324", "Av. Emiliano Zapata #801"),
//            Socio(R.drawable.alboa, "Alboa", "s/n", "CC Bugambilia Plaza / Río Balsas s/n")
//        )

        
        back.setOnClickListener(this@Inventario)
        //prueba.setOnClickListener(this@Inventario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Inventario, InventarioOpciones::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_v2, R.anim.slide_out_v2)
        }

        //Prueba JEJE
        //prueba.setOnClickListener {
        //    print("Diste click a prueba")
        //    val intent2 = Intent(this@Inventario, Inventario2::class.java)
        //    startActivity(intent2)
        //}

//        val adapter = SocioAdapter(this, R.layout.socio_layout, datosSocios)
//        listSocio.adapter = adapter
//
        listSocio.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, Inventario2::class.java)
            intent.putExtra("socio_id", sociosArray[position].id)
            intent.putExtra("Nombre", sociosArray[position].nombre)
            intent.putExtra("Imagen", sociosArray[position].imagen)
            intent.putExtra("Determinante", sociosArray[position].determinante)
            intent.putExtra("Direccion", sociosArray[position].direccion)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_1, R.anim.slide_out_1)
        }

    }

    override fun onClick(p0: View?) {
        //
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Inventario, InventarioOpciones::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_v2, R.anim.slide_out_v2)
    }
}