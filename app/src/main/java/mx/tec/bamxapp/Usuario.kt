package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_usuario.*
import java.util.*

class Usuario : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("nombre", "@")
        val lastname = sharedPreferences.getString("apellido", "@")
        val dob = sharedPreferences.getString("dob", "@")
        val licence = sharedPreferences.getString("licence", "@")
        val location = sharedPreferences.getString("location", "@")
        val year = dob?.substring(0, 4)?.toInt()
        val month = dob?.substring(5, 7)?.toInt()
        val day = dob?.substring(8, 10)?.toInt()

        val age = getAge(year!!, month!!, day!!)

        tv_usuario_nombre.text = "$location $lastname"
        tv_usuario_anios.text = "${age} años"
        tv_usuario_fecha.text = "$licence"

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

    private fun getAge(year: Int, month: Int, day: Int): String{
        var dob = Calendar.getInstance()
        var today = Calendar.getInstance()

        dob.set(year, month, day)

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--
        }

        var ageInt = age
        var ageString = ageInt.toString()

        return ageString
    }
}