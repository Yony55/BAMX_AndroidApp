package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Vehiculo:AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val inventario = findViewById<Button>(R.id.btn_vehiculo_inventario)

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

    }
    override fun onClick(p0: View?) {
        //
    }
}