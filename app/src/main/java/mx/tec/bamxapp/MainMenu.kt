package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.activity_vehiculo.*
import mx.tec.bamxapp.service.APIVehiculo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainMenu : AppCompatActivity(), View.OnClickListener {
    lateinit var sharedVehicle: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_menu)

        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            1)

        val inventario = findViewById<ImageButton>(R.id.btn_inventario)
        val rutas= findViewById<ImageButton>(R.id.btn_rutas)
        val usuario = findViewById<ImageButton>(R.id.btn_usuario)
        val entregas = findViewById<ImageButton>(R.id.btn_entregas)
        val vehiculo = findViewById<ImageButton>(R.id.btn_vehiculo)
        val recoleccion = findViewById<ImageButton>(R.id.btn_recoleccion)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        sharedVehicle = getSharedPreferences("vehiculo", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("nombre", "@")
        val vehicle_id = sharedPreferences.getInt("vehicle_id", 0)

        tv_bienvenida.text = "Bienvenido, $username"

        inventario.setOnClickListener(this@MainMenu)
        rutas.setOnClickListener(this@MainMenu)
        usuario.setOnClickListener(this@MainMenu)
        entregas.setOnClickListener(this@MainMenu)
        vehiculo.setOnClickListener(this@MainMenu)
        recoleccion.setOnClickListener(this@MainMenu)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosVehiculo: mx.tec.bamxapp.model.Vehiculo

        val service = retrofit.create<APIVehiculo>(APIVehiculo::class.java)
        service.getVehiculo(vehicle_id).enqueue(object: Callback<mx.tec.bamxapp.model.Vehiculo> {
            override fun onResponse(call: Call<mx.tec.bamxapp.model.Vehiculo>, response: Response<mx.tec.bamxapp.model.Vehiculo>) {
                datosVehiculo = response.body()!!
                with(sharedVehicle.edit()){
                    putString("modelo", datosVehiculo.data[0].model)
                    putString("color", datosVehiculo.data[0].color)
                    putString("placas", datosVehiculo.data[0].plates)
                    putString("poliza", datosVehiculo.data[0].insurance_policy)
                    putString("verificacion", datosVehiculo.data[0].verification_date)
                    putString("aseguradora", datosVehiculo.data[0].insurance_company)
                    commit()
                }
            }

            override fun onFailure(call: Call<mx.tec.bamxapp.model.Vehiculo>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }
        })

        inventario.setOnClickListener {
            print("Diste click a inventario")
            val intent = Intent(this@MainMenu, InventarioOpciones::class.java)
            startActivity(intent)
        }
        rutas.setOnClickListener {
            print("Diste click a inventario")
            val intent = Intent(this@MainMenu, Rutas::class.java)
            startActivity(intent)
        }
        usuario.setOnClickListener {
            print("Diste click a Usuario")
            val intent = Intent(this@MainMenu, Usuario::class.java)
            startActivity(intent)
        }
        entregas.setOnClickListener {
            print("Diste click a Entregas")
            val intent = Intent(this@MainMenu, Entregas2::class.java)
            startActivity(intent)
        }
        vehiculo.setOnClickListener {
            print("Diste click a Vehiculo")
            val intent = Intent(this@MainMenu, Vehiculo::class.java)
            startActivity(intent)
        }
        recoleccion.setOnClickListener {
            print("Diste click a Recoleccion")
            val intent = Intent(this@MainMenu, Recolecciones::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }
    override fun onBackPressed() {
        return
    }
}