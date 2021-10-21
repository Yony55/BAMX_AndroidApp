package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class InventarioOpciones : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_opciones)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val entrega = findViewById<ImageButton>(R.id.btn_inv_entrega)
        val recoleccion = findViewById<ImageButton>(R.id.btn_inv_recoleccion)

        back.setOnClickListener(this@InventarioOpciones)
        entrega.setOnClickListener(this@InventarioOpciones)
        recoleccion.setOnClickListener(this@InventarioOpciones)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@InventarioOpciones, MainMenu::class.java)
            startActivity(intent)
        }
        entrega.setOnClickListener {
            print("Diste click a entrega")
            val intent = Intent(this@InventarioOpciones, InventarioEntregas::class.java)
            startActivity(intent)
        }
        recoleccion.setOnClickListener {
            print("Diste click a recoleccion")
            val intent = Intent(this@InventarioOpciones, Inventario::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }

    override fun onBackPressed(){
        super.onBackPressed()
        val intent = Intent(this@InventarioOpciones, MainMenu::class.java)
        startActivity(intent)
    }

}