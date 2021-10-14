package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.notkamui.keval.Keval
import kotlinx.android.synthetic.main.activity_inventario2.*


class Inventario2 : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        val tipo = arrayOf("Fruta", "Verdura","Comida preparada", "Pan", "Abarrotes")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario2)

        val inventarioPreferences = getSharedPreferences("Inventario", Context.MODE_PRIVATE)
        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val prueba = findViewById<Button>(R.id.btn_inv_prueba2)
        val imagen = intent.getIntExtra("Imagen", R.drawable.aurrera)
        val nombre = intent.getStringExtra("Nombre")
        val determinante = intent.getStringExtra("Determinante")
        val direccion = intent.getStringExtra("Direccion")
        val cantAbarrotes = inventarioPreferences.getString("Abarrotes", "0")
        val cantFruta = inventarioPreferences.getString("Frutas", "0")
        val cantPan = inventarioPreferences.getString("Pan", "0")
        val cantNo = inventarioPreferences.getString("NoComes", "0")
        val cantComida = inventarioPreferences.getString("Comida", "0")
        //iv_inv_socio.setImageResource(imagen)
        tv_inv_socio.text = nombre
        tv_inv_producto.text = determinante
        tv_inv_dir.text = direccion

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
            val abaStr = tn_inv_abarrotes.text
            val builder = AlertDialog.Builder(this@Inventario2)
            builder.setTitle("Confirmar inventario")
                .setMessage("Abarrotes: ${tn_inv_abarrotes.text} kg\n" +
                "Fruta y verdura: ${tn_inv_fruta.text} kg\n" +
                "Pan: ${tn_inv_pan.text} kg\n" +
                "No comestibles: ${tn_inv_nocomes.text} kg\n" +
                "Comida preparada: ${tn_inv_comidaprep.text} kg\n")
                .setPositiveButton("Confirmar"){dialog, button ->
                    val intent = Intent(this@Inventario2, InventarioCompletado::class.java)
                    val newAba = Keval.eval(cantAbarrotes + "+" + tn_inv_abarrotes.text.toString()).toString()
                    val newFruta = Keval.eval(cantFruta + "+" + tn_inv_fruta.text.toString()).toString()
                    val newPan = Keval.eval(cantPan + "+" + tn_inv_pan.text.toString()).toString()
                    val newNo = Keval.eval(cantNo + "+" + tn_inv_nocomes.text.toString()).toString()
                    val newComida = Keval.eval(cantComida + "+" + tn_inv_comidaprep.text.toString()).toString()
                    with(inventarioPreferences.edit()){
                        putString("Abarrotes", newAba)
                        putString("Frutas", newFruta)
                        putString("Pan", newPan)
                        putString("NoComes", newNo)
                        putString("Comida", newComida)
                        commit()
                    }
                    startActivity(intent)
                }
                .setNegativeButton("Cancelar"){dialog, button ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    override fun onClick(p0: View?) {
    }

}