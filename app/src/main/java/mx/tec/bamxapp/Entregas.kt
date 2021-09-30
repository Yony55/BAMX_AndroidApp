package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Entregas : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas)

        val entregas = findViewById<Button>(R.id.btn_entregas_entregas)
        val inventario = findViewById<Button>(R.id.btn_entregas_inventario)

        entregas.setOnClickListener(this@Entregas)
        inventario.setOnClickListener(this@Entregas)

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