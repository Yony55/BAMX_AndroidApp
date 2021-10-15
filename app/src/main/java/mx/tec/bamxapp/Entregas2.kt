package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Entrega

class Entregas2: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas2)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val listEntregas = findViewById<ListView>(R.id.list_entregas)

        back.setOnClickListener(this@Entregas2)

        val datosEntregas = listOf(
            Entrega("1", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
            Entrega("2", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
            Entrega("3", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg"),
            Entrega("4", "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS",
                "Frutas 10kg, Verduras 20kg, No comestibles 23kg, Pan 22kg, Comida preparada 20kg")
        )

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Entregas2, MainMenu::class.java)
            startActivity(intent)
        }

        val adapter = EntregaAdapter(this, R.layout.entrega_layout, datosEntregas)
        listEntregas.adapter = adapter
    }

    override fun onClick(p0: View?) {
        //
    }
}




