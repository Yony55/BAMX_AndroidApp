package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

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

        inventario.setOnClickListener(this@MainMenu)
        rutas.setOnClickListener(this@MainMenu)
        usuario.setOnClickListener(this@MainMenu)
        entregas.setOnClickListener(this@MainMenu)

        inventario.setOnClickListener {
            print("Diste click a inventario")
            val intent = Intent(this@MainMenu, Inventario::class.java)
            startActivity(intent)
        }
        //Falta implementar MAPS
//        rutas.setOnClickListener {
//            print("Diste click a inventario")
//            val intent = Intent(this@MainMenu, Rutas::class.java)
//            startActivity(intent)
//        }
        usuario.setOnClickListener {
            print("Diste click a Usuario")
            val intent = Intent(this@MainMenu, Usuario::class.java)
            startActivity(intent)
        }
        entregas.setOnClickListener {
            print("Diste click a Entregas")
            val intent = Intent(this@MainMenu, Entregas::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }
}