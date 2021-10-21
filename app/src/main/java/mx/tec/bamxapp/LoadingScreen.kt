package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class LoadingScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_screen)

        Handler().postDelayed({
            val intent = Intent(this@LoadingScreen, InventarioCompletado::class.java)
            startActivity(intent)

        }, 2500)
    }

    override fun onBackPressed() {
        return
    }
}