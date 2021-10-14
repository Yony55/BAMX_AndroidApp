package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_entregasinventario.*

class EntregasInventario: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregasinventario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val inventarioPreferences = getSharedPreferences("Inventario", Context.MODE_PRIVATE)
        val cantAbarrotes = inventarioPreferences.getString("Abarrotes", "0") + " kg"
        val cantFruta = inventarioPreferences.getString("Frutas", "0") + " kg"
        val cantPan = inventarioPreferences.getString("Pan", "0") + " kg"
        val cantNo = inventarioPreferences.getString("NoComes", "0") + " kg"
        val cantComida = inventarioPreferences.getString("Comida", "0") + " kg"

        tv_entinv_abacant.text = cantAbarrotes
        tv_entinv_frutacant.text = cantFruta
        tv_entinv_pancant.text = cantPan
        tv_entinv_nocant.text = cantNo
        tv_entinv_comidacant.text = cantComida


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