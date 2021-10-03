package mx.tec.bamxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logIn = findViewById<Button>(R.id.btn_login)
        logIn.setOnClickListener(this@MainActivity)


    }
    override fun onClick(p0: View?){
        when (p0!!.id) {
            R.id.btn_login -> {
                val key = ""
                val user = ""
                println("Diste click al boton Login")

                if(edt_user.text.toString() == user && edt_password.text.toString() == key){
                    val intent = Intent(this@MainActivity, MainMenu::class.java)
                    intent.putExtra("User", edt_user.text.toString())
                    intent.putExtra("Password", edt_password.text.toString())
                    startActivity(intent)
                    println("Datos correctos")
                }
                else{
                    edt_password.setText("")
                    println("Datos Incorrectos")
                    Toast.makeText(this@MainActivity, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}