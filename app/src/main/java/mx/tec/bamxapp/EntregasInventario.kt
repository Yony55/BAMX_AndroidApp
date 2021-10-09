package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EntregasInventario: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregasinventario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventarioLay)

        back.setOnClickListener(this@EntregasInventario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@EntregasInventario, Entregas::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        //
    }
}