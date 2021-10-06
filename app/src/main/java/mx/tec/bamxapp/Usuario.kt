package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_usuario.*

class Usuario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("usuario", "@")

        tv_usuario_nombre.text = "$username"

        back.setOnClickListener(this@Usuario)

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Usuario, MainMenu::class.java)
            startActivity(intent)
        }

        btn_logOut.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            with(sharedPreferences.edit()){
                remove("logIn")
                commit()
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        //
    }
}