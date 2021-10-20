package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Almacen
import mx.tec.bamxapp.model.Almacenes
import mx.tec.bamxapp.model.Entrega
import mx.tec.bamxapp.model.EntregasAlmacen
import mx.tec.bamxapp.service.APIEntregasAlmacen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class InventarioEntregas : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_entregas)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosAlmacenesRetro: EntregasAlmacen
        val listAlmacenes = findViewById<ListView>(R.id.list_Almacenes)
        var adapter: AlmacenesAdapter
        val almacenesArray = mutableListOf<Almacenes>()

        val service = retrofit.create<APIEntregasAlmacen>(APIEntregasAlmacen::class.java)
        service.getEntregas(1).enqueue(object: Callback<EntregasAlmacen> {
            override fun onResponse(
                call: Call<EntregasAlmacen>,
                response: Response<EntregasAlmacen>
            ) {
                datosAlmacenesRetro = response.body()!!
                for(i in datosAlmacenesRetro.data.indices){
                    val temp = Almacenes(R.drawable.boxicon,
                        datosAlmacenesRetro.data[i].bodega,
                        datosAlmacenesRetro.data[i].direccion)
                    almacenesArray.add(temp)
                }
                adapter = AlmacenesAdapter(this@InventarioEntregas, R.layout.almacen_layout, almacenesArray)
                listAlmacenes.adapter = adapter
            }

            override fun onFailure(call: Call<EntregasAlmacen>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })


        //////////////


        val back = findViewById<ImageButton>(R.id.btn_back_maps)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@InventarioEntregas, InventarioOpciones::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }
}