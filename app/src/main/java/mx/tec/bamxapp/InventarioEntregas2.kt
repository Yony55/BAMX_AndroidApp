package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.notkamui.keval.Keval
import kotlinx.android.synthetic.main.activity_inventario2.*
import kotlinx.android.synthetic.main.activity_inventario_entregas2.*
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tn_inv_abarrotes
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tn_inv_comidaprep
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tn_inv_fruta
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tn_inv_nocomes
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tn_inv_pan
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tv_inv_dir
import kotlinx.android.synthetic.main.activity_inventario_entregas2.tv_inv_socio


class InventarioEntregas2: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var newAba: String
        lateinit var newFruta: String
        lateinit var newPan: String
        lateinit var newNo: String
        lateinit var newComida: String
        lateinit var abarrotesStr: String
        lateinit var frutaStr: String
        lateinit var panStr: String
        lateinit var noStr: String
        lateinit var comidaStr: String
        val tipo = arrayOf("Fruta", "Verdura","Comida preparada", "Pan", "Abarrotes")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_entregas2)

        val inventarioPreferences = getSharedPreferences("Inventario", Context.MODE_PRIVATE)
        val back = findViewById<ImageButton>(R.id.btn_back_inventario)
        val prueba = findViewById<Button>(R.id.btn_inv_prueba2)
        val imagen = intent.getIntExtra("Imagen", R.drawable.aurrera)
        val nombre = intent.getStringExtra("Nombre")
        val direccion = intent.getStringExtra("Direccion")
        val cantAbarrotes = inventarioPreferences.getString("Abarrotes", "0")
        val cantFruta = inventarioPreferences.getString("Frutas", "0")
        val cantPan = inventarioPreferences.getString("Pan", "0")
        val cantNo = inventarioPreferences.getString("NoComes", "0")
        val cantComida = inventarioPreferences.getString("Comida", "0")
        //iv_inv_socio.setImageResource(imagen)
        tv_inv_socio.text = nombre
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
            val intent = Intent(this@InventarioEntregas2, InventarioEntregas::class.java)
            startActivity(intent)
        }

        //Prueba JEJE
        prueba.setOnClickListener {
            val builder = AlertDialog.Builder(this@InventarioEntregas2)
            if(tn_inv_abarrotes.text.toString() != ""){
                newAba = Keval.eval(cantAbarrotes + "-" + tn_inv_abarrotes.text.toString()).toString()
                abarrotesStr = tn_inv_abarrotes.text.toString()
            } else{
                if (cantAbarrotes != null) {
                    newAba = cantAbarrotes
                    abarrotesStr = "0"
                }
            }
            if(tn_inv_fruta.text.toString() != ""){
                newFruta = Keval.eval(cantFruta + "-" + tn_inv_fruta.text.toString()).toString()
                frutaStr = tn_inv_fruta.text.toString()
            } else{
                if (cantFruta != null) {
                    newFruta = cantFruta
                    frutaStr = "0"
                }
            }
            if(tn_inv_pan.text.toString() != ""){
                newPan = Keval.eval(cantPan + "-" + tn_inv_pan.text.toString()).toString()
                panStr = tn_inv_pan.text.toString()
            } else{
                if (cantPan != null) {
                    newPan = cantPan
                    panStr = "0"
                }
            }
            if(tn_inv_nocomes.text.toString() != ""){
                newNo = Keval.eval(cantNo + "-" + tn_inv_nocomes.text.toString()).toString()
                noStr = tn_inv_nocomes.text.toString()
            } else{
                if (cantNo != null) {
                    newNo = cantNo
                    noStr = "0"
                }
            }
            if(tn_inv_comidaprep.text.toString() != ""){
                newComida = Keval.eval(cantComida + "-" + tn_inv_comidaprep.text.toString()).toString()
                comidaStr = tn_inv_comidaprep.text.toString()
            } else{
                if (cantComida != null) {
                    newComida = cantComida
                    comidaStr = "0"
                }
            }
            builder.setTitle("Confirmar inventario")
                .setMessage("Abarrotes: ${abarrotesStr} kg\n" +
                        "Fruta y verdura: ${frutaStr} kg\n" +
                        "Pan: ${panStr} kg\n" +
                        "No comestibles: ${noStr} kg\n" +
                        "Comida preparada: ${comidaStr} kg\n")
                .setPositiveButton("Confirmar"){dialog, button ->
                    val intent = Intent(this@InventarioEntregas2, InventarioCompletado::class.java)
                    with(inventarioPreferences.edit()){
                        putString("Abarrotes", newAba)
                        putString("Frutas", newFruta)
                        putString("Pan", newPan)
                        putString("NoComes", newNo)
                        putString("Comida", newComida)
                        commit()
                    }
                    //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
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