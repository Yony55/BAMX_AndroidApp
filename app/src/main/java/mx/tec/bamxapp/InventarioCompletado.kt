package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import mx.tec.bamxapp.MainMenu
import mx.tec.bamxapp.R

class InventarioCompletado : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_completado)

        val menu = findViewById<Button>(R.id.btn_inv_menu)
        menu.setOnClickListener(this@InventarioCompletado)

        menu.setOnClickListener {
            print("Diste click a menu")
            val intent = Intent(this@InventarioCompletado, MainMenu::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_1, R.anim.slide_out_1)
        }
    }

    override fun onClick(p0: View?) {

    }
    override fun onBackPressed() {
        return
    }
}