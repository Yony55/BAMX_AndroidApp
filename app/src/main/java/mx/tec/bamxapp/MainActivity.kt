package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferences1: SharedPreferences
    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        sharedPreferences1 = getSharedPreferences("Inventario", Context.MODE_PRIVATE)

        if(sharedPreferences.getBoolean("logIn", false) == true){
            val intent = Intent(this, MainMenu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        if(sharedPreferences.getString("usuario", "@") != "@"){
            edt_user.setText(sharedPreferences.getString("usuario", "@"))
        }

        val logIn = findViewById<Button>(R.id.btn_login)
        logIn.setOnClickListener(this@MainActivity)


    }
    override fun onClick(p0: View?){
        when (p0!!.id) {
            R.id.btn_login -> {
                val key = "123"
                val user = "Palencia"
                println("Diste click al boton Login")

                login()

//                if(edt_user.text.toString() == user && edt_password.text.toString() == key){
//                    val intent = Intent(this@MainActivity, MainMenu::class.java)
//                    with(sharedPreferences.edit()){
//                        putString("usuario", edt_user.text.toString())
//                        putBoolean("logIn", true)
//                        commit()
//                    }
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    println("Datos correctos")
//                }
//                else{
//                    edt_password.setText("")
//                    println("Datos Incorrectos")
//                    Toast.makeText(this@MainActivity, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    private fun login(){
        queue = Volley.newRequestQueue(this)
        val credenciales = JSONObject()
        credenciales.put("email", edt_user.text.toString())
        credenciales.put("password", edt_password.text.toString())

        val loginRequest = JsonObjectRequest(
            Request.Method.POST,
            "http://bamx.denissereginagarcia.com/public/login",
            credenciales,
            Response.Listener { response ->
                Log.e("VolleyResponseTrue", response.toString())
                val intent = Intent(this@MainActivity, MainMenu::class.java)
                    with(sharedPreferences.edit()){
                        putString("usuario", response.getString("name"))
                        putBoolean("logIn", true)
                        commit()
                    }
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    println("Datos correctos")
            },
            { error ->
                Log.e("VolleyResponse", error.toString())
                edt_password.setText("")
                println("Datos Incorrectos")
                Toast.makeText(this@MainActivity, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(loginRequest)
    }
}