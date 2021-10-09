package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Entregas2: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas2)

        val back = findViewById<ImageButton>(R.id.btn_back_inventarioLay)

        back.setOnClickListener(this@Entregas2)

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




