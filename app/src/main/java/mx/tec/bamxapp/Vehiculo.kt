package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vehiculo.*
import mx.tec.bamxapp.model.Almacenes
import mx.tec.bamxapp.model.EntregasAlmacen
import mx.tec.bamxapp.model.Vehiculo
import mx.tec.bamxapp.service.APIEntregasAlmacen
import mx.tec.bamxapp.service.APIVehiculo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Vehiculo:AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val inventario = findViewById<Button>(R.id.btn_vehiculo_inventario)
        val sharedPreferences = getSharedPreferences("vehiculo", Context.MODE_PRIVATE)
        val modelo = sharedPreferences.getString("modelo", "@")
        val color = sharedPreferences.getString("color", "@")
        val placas = sharedPreferences.getString("placas", "@")
        val poliza = sharedPreferences.getString("poliza", "@")
        val verificacion = sharedPreferences.getString("verificacion", "@")
        val aseguradora = sharedPreferences.getString("aseguradora", "@")

        back.setOnClickListener(this@Vehiculo)
        inventario.setOnClickListener(this@Vehiculo)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Vehiculo, MainMenu::class.java)
            startActivity(intent)
        }
        inventario.setOnClickListener {
            print("Diste click a inventario")
            val intent = Intent(this@Vehiculo, EntregasInventario::class.java)
            startActivity(intent)
        }

        ///

        tv_static_modelo.text = "Modelo: ${modelo}"
        tv_static_color.text = "Color: ${color}"
        tv_static_placas.text = "Placas: ${placas}"
        tv_static_poliza.text = "Póliza: ${poliza}"
        tv_static_verificacion.text = "Verificación: ${verificacion}"
        tv_static_empresaSeguro.text = "Aseguradora: ${aseguradora}"

    }
    override fun onClick(p0: View?) {
        //
    }
}