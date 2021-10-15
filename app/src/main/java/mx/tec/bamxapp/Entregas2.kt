package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.model.Socio

class Entregas2: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas2)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val list = findViewById<ListView>(R.id.list_entregas)

        back.setOnClickListener(this@Entregas2)

        val datosEntregas = listOf(
            Socio(R.drawable.superama, "Superama", "4544", "Manuel Ávila Camacho #606",),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186"),
            Socio(R.drawable.aurrera, "Bodega Aurrera", "3869", "Av. Cuauhtémoc #186")
        )

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Entregas2, Entregas::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        //
    }
}




