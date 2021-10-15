package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class InventarioConfirmar : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_confirmar)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val confirmar = findViewById<Button>(R.id.btn_inv_confirmar)
        back.setOnClickListener(this@InventarioConfirmar)
        confirmar.setOnClickListener(this@InventarioConfirmar)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@InventarioConfirmar, InventarioFinalizar::class.java)
            startActivity(intent)
        }
            confirmar.setOnClickListener {
            print("Diste click a confirmar")
            val intent = Intent(this@InventarioConfirmar, InventarioCompletado::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

    }

}