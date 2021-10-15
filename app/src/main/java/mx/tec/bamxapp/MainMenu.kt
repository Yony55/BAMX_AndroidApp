package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_menu)

        val inventario = findViewById<ImageButton>(R.id.btn_inventario)
        val rutas= findViewById<ImageButton>(R.id.btn_rutas)
        val usuario = findViewById<ImageButton>(R.id.btn_usuario)
        val entregas = findViewById<ImageButton>(R.id.btn_entregas)
        val vehiculo = findViewById<ImageButton>(R.id.btn_vehiculo)
        val recoleccion = findViewById<ImageButton>(R.id.btn_recoleccion)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("usuario", "@")

        tv_bienvenida.text = "Bienvenido, $username"

        inventario.setOnClickListener(this@MainMenu)
        rutas.setOnClickListener(this@MainMenu)
        usuario.setOnClickListener(this@MainMenu)
        entregas.setOnClickListener(this@MainMenu)
        vehiculo.setOnClickListener(this@MainMenu)
        recoleccion.setOnClickListener(this@MainMenu)

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