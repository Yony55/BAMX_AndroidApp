package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class Usuario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)

        back.setOnClickListener(this@Usuario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Usuario, MainMenu::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        //
    }
}