package mx.tec.bamxapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_inventario2.*


class Inventario2 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        val tipo = arrayOf("Fruta", "Verdura","Comida preparada", "Pan", "Abarrotes")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario2)

        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val prueba = findViewById<Button>(R.id.btn_inv_prueba2)
        val imagen = intent.getIntExtra("Imagen", R.drawable.aurrera)
        val nombre = intent.getStringExtra("Nombre")
        val determinante = intent.getStringExtra("Determinante")
        val direccion = intent.getStringExtra("Direccion")
        //iv_inv_socio.setImageResource(imagen)
        tv_inv_socio.text = nombre
        tv_inv_producto.text = determinante
        tv_inv_dir.text = direccion
        back.setOnClickListener(this@Inventario2)
        //Boton prueba JEJE
        prueba.setOnClickListener(this@Inventario2)

//        val arrayAdapter = ArrayAdapter(this@Inventario2, android.R.layout.simple_spinner_dropdown_item,tipo)
//        spn_tipo.adapter = arrayAdapter
//        spn_tipo.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int, id: Long) {
//                Toast.makeText(this@Inventario2, "Seleccionaste" + tipo[position], Toast.LENGTH_LONG).show()
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }

        back.setOnClickListener {
            print("Diste click a back")
            val intent = Intent(this@Inventario2, Inventario::class.java)
            startActivity(intent)
        }
        //Prueba JEJE
        prueba.setOnClickListener {
            print("Diste click a prueba")
            val intent = Intent(this@Inventario2, InventarioFinalizar::class.java)
            startActivity(intent)
        }

    }

    override fun onClick(p0: View?) {
    }

}