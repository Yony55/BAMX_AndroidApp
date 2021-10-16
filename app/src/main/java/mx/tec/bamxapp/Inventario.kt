package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton
import android.widget.ListView
import mx.tec.bamxapp.model.Socio

class Inventario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_inventario)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val listSocio = findViewById<ListView>(R.id.list_Socios)
        //val prueba = findViewById<Button>(R.id.btn_inv_prueba)

        val datosSocios = listOf(
            Socio(R.drawable.superama, "Superama", "4544", "Manuel Ávila Camacho #606",),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.lacomer, "La Comer", "163", "Enrique Flores Magón #27"),
            Socio(R.drawable.fresko, "Fresko", "412", "Río Balsas #102"),
            Socio(R.drawable.citymarket, "City Market", "418", "Río Yaqui #28"),
            Socio(R.drawable.chedraui, "Chedraui", "238", "Paseo Cuauhnáhuac #16"),
            Socio(R.drawable.kfc, "KFC", "324", "Av. Emiliano Zapata #801"),
            Socio(R.drawable.alboa, "Alboa", "s/n", "CC Bugambilia Plaza / Río Balsas s/n")
        )

        
        back.setOnClickListener(this@Inventario)
        //prueba.setOnClickListener(this@Inventario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Inventario, InventarioOpciones::class.java)
            startActivity(intent)
        }

        //Prueba JEJE
        //prueba.setOnClickListener {
        //    print("Diste click a prueba")
        //    val intent2 = Intent(this@Inventario, Inventario2::class.java)
        //    startActivity(intent2)
        //}

        val adapter = SocioAdapter(this, R.layout.socio_layout, datosSocios)
        listSocio.adapter = adapter

        listSocio.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, Inventario2::class.java)
            intent.putExtra("Nombre", datosSocios[position].nombre)
            intent.putExtra("Imagen", datosSocios[position].imagen)
            intent.putExtra("Determinante", datosSocios[position].determinante)
            intent.putExtra("Direccion", datosSocios[position].direccion)
            startActivity(intent)
        }

    }

    override fun onClick(p0: View?) {
        //
    }
}