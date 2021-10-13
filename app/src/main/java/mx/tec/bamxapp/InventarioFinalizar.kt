package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.Inventario2
import mx.tec.bamxapp.InventarioConfirmar
import mx.tec.bamxapp.R

class InventarioFinalizar : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_finalizar)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val finalizar = findViewById<Button>(R.id.btn_inv_confirmar)
        back.setOnClickListener(this@InventarioFinalizar)
        finalizar.setOnClickListener(this@InventarioFinalizar)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@InventarioFinalizar, Inventario2::class.java)
            startActivity(intent)
        }
        finalizar.setOnClickListener {
            print("Diste click a finalizar")
            val intent = Intent(this@InventarioFinalizar, InventarioConfirmar::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }


}