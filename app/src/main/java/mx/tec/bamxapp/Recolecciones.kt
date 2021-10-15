package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Recoleccion


class Recolecciones:AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recolecciones)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)

        back.setOnClickListener(this@Recolecciones)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Recolecciones, MainMenu::class.java)
            startActivity(intent)
        }

        val listRecolecciones = findViewById<ListView>(R.id.list_recolecciones)
        //val prueba = findViewById<Button>(R.id.btn_inv_prueba)

        val datosRecolecciones = listOf(

            Recoleccion("1","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"),
            Recoleccion("2","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"),
            Recoleccion("3","Bodega Aurrera", "16568", "Plan de Ayala #655", "Frutas 10kg, Verduras 30kg, " +
                    "Pan 20kg, No comestibles 30kg y Comida Preparada 20kg"))

        val adapter = RecoleccionAdapter(this, R.layout.recoleccion_layout, datosRecolecciones)
        listRecolecciones.adapter = adapter


    }
    override fun onClick(p0: View?) {
        //
    }
}