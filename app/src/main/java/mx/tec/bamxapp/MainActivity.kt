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
import mx.tec.bamxapp.model.DatosRetrofit
import mx.tec.bamxapp.service.APIDatos
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            edt_user.setText(sharedPreferences.getString("email", "@"))
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
                var user_id = response.getInt("id")
                with(sharedPreferences.edit()){
                    putInt("user_id", response.getInt("id"))
                    putString("nombre", response.getString("name"))
                    putString("apellido", response.getString("first_lastname"))
                    putString("email", response.getString("email"))
                    putString("dob", response.getString("date_of_birth"))
                    putString("licence", response.getString("license_vigency"))
                    putBoolean("logIn", true)
                    commit()
                }
                var retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var datosRutas1: DatosRetrofit

                val service = retrofit.create<APIDatos>(APIDatos::class.java)
                service.getDatos(user_id).enqueue(object: Callback<DatosRetrofit>{
                    override fun onResponse(
                        call: Call<DatosRetrofit>,
                        response: retrofit2.Response<DatosRetrofit>
                    ) {
                        datosRutas1 = response.body()!!
                        with(sharedPreferences.edit()){
                            putInt("route_id", datosRutas1.data.id)
                            putInt("vehicle_id", datosRutas1.data.vehicle_id)
                            commit()
                        }
                    }
                    override fun onFailure(call: Call<DatosRetrofit>, t: Throwable) {
                        Log.e("RetrofitError", t.message!!)
                    }
                })
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

    override fun onBackPressed() {
        return
    }
}
