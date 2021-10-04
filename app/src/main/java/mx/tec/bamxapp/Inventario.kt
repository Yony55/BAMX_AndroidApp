package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageButton

class Inventario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_inventario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)

        back.setOnClickListener(this@Inventario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Inventario, MainMenu::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        //
    }
}