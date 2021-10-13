package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import mx.tec.bamxapp.model.Socio

class Inventario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_inventario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val listSocio = findViewById<ListView>(R.id.list_Socios)
        val prueba = findViewById<Button>(R.id.btn_inv_prueba)

        val datosSocios = listOf(
            Socio(R.drawable.superama, "Superama", "4544", "Manuel Ávila Camacho #606",),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186")
        )

        
        back.setOnClickListener(this@Inventario)
        prueba.setOnClickListener(this@Inventario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Inventario, MainMenu::class.java)
            startActivity(intent)
        }

        //Prueba JEJE
        prueba.setOnClickListener {
            print("Diste click a prueba")
            val intent2 = Intent(this@Inventario, Inventario2::class.java)
            startActivity(intent2)
        }

        val adapter = SocioAdapter(this, R.layout.socio_layout, datosSocios)
        listSocio.adapter = adapter

    }

    override fun onClick(p0: View?) {
        //
    }
}