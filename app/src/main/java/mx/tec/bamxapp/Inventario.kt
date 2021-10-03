package mx.tec.bamxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class Inventario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_inventario)
    }
}