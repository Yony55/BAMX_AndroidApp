package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class Entregas : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas)

        val entregas = findViewById<Button>(R.id.btn_entregas_entregas)
        val inventario = findViewById<Button>(R.id.btn_entregas_inventario)
        val back = findViewById<ImageButton>(R.id.btn_back_inventarioLay)

        back.setOnClickListener(this@Entregas)
        entregas.setOnClickListener(this@Entregas)
        inventario.setOnClickListener(this@Entregas)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Entregas, MainMenu::class.java)
            startActivity(intent)
        }

        entregas.setOnClickListener {
            println("Diste click en entregas")
            val intent = Intent(this@Entregas, Entregas2::class.java)
            startActivity(intent)

        }

        inventario.setOnClickListener {
            println("Diste click en inventario")
            val intent = Intent(this@Entregas, EntregasInventario::class.java)
            startActivity(intent)

        }
    }

    override fun onClick(p0: View?) {
        //No se utiliza
    }
}