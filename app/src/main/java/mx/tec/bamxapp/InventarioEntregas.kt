package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Almacenes


class InventarioEntregas : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_entregas)


        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val listAlmacenes = findViewById<ListView>(R.id.list_Almacenes)

        val datosAlmacenes = listOf(
            Almacenes(R.drawable.boxicon, "BODEGA TLAHUAPAN", "PALMA REAL, CP 62553 TLAHUAPAN JIUTEPEC MORELOS"),
            Almacenes(R.drawable.boxicon, "Almacen Cuernavaca", "Plan de Ayala #45"),
            Almacenes(R.drawable.boxicon, "BODEGA REFRIGERADOS", "C 21 ESTE 62500 CIVAC JIUTEPEC MORELOS"),
            Almacenes(R.drawable.boxicon, "Banco de Alimentos TEMIXCO", "PLUTARCO ELIAS CALLES #99 C.P. 62580 TEMIXCO CENTRO, MORELOS"),
            Almacenes(R.drawable.boxicon, "Banco de Alimentos TEMIXCO", "PLUTARCO ELIAS CALLES #99 C.P. 62580 TEMIXCO CENTRO, MORELOS")

        )


        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@InventarioEntregas, InventarioOpciones::class.java)
            startActivity(intent)
        }

        val adapter = AlmacenesAdapter(this, R.layout.almacen_layout, datosAlmacenes)
        listAlmacenes.adapter = adapter

        listAlmacenes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, InventarioEntregas2::class.java)
            intent.putExtra("Nombre", datosAlmacenes[position].nombre)
            intent.putExtra("Imagen", datosAlmacenes[position].imagen)
            intent.putExtra("Direccion", datosAlmacenes[position].direccion)
            startActivity(intent)

        }
    }

    override fun onClick(p0: View?) {

    }
}