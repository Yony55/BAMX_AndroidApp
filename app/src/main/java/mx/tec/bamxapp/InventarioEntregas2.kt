package mx.tec.bamxapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class InventarioEntregas2: AppCompatActivity(), View.OnClickListener {
    lateinit var queue: RequestQueue
    lateinit var postFruta: String
    lateinit var postAba: String
    lateinit var postnoComes: String
    lateinit var postPan: String
    lateinit var postComida: String
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
    override fun onCreate(savedInstanceState: Bundle?) {

        val tipo = arrayOf("Fruta", "Verdura","Comida preparada", "Pan", "Abarrotes")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_entregas2)

        val inventarioPreferences = getSharedPreferences("Inventario", Context.MODE_PRIVATE)
        val back = findViewById<ImageButton>(R.id.btn_back_maps)
        val prueba = findViewById<Button>(R.id.btn_inv_prueba2)
        val imagen = intent.getIntExtra("Imagen", R.drawable.aurrera)
        val nombre = intent.getStringExtra("Nombre")
        val direccion = intent.getStringExtra("Direccion")
        val cantAbarrotes = inventarioPreferences.getString("Abarrotes", "0")
        val cantFruta = inventarioPreferences.getString("Frutas", "0")
        val cantPan = inventarioPreferences.getString("Pan", "0")
        val cantNo = inventarioPreferences.getString("NoComes", "0")
        val cantComida = inventarioPreferences.getString("Comida", "0")
        val id = intent.getIntExtra("id", 0)
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
            overridePendingTransition(R.anim.slide_in_2, R.anim.slide_out_2)
        }

        //Prueba JEJE
        prueba.setOnClickListener {
            val aba = "${tn_inv_abarrotes.text}"
            val fruta = "${tn_inv_fruta.text}"
            val pan = "${tn_inv_pan.text}"
            val no = "${tn_inv_nocomes.text}"
            val comida = "${tn_inv_comidaprep.text}"

            if (checkValues(aba, cantAbarrotes!!) || checkValues(fruta, cantFruta!!) ||
                checkValues(pan, cantPan!!) || checkValues(no, cantNo!!) || checkValues(
                    comida,
                    cantComida!!
                )
            ) {
                val check = AlertDialog.Builder(this@InventarioEntregas2)
                var str = ""
                if (greaterThan(aba, cantAbarrotes!!) > 0) {
                    str = "$str${greaterThan(aba, cantAbarrotes!!)} kilos más de abarrotes\n"
                }
                if (greaterThan(fruta, cantFruta!!) > 0) {
                    str = "$str${greaterThan(fruta, cantFruta!!)} kilos más de frutas y verduras\n"
                }
                if (greaterThan(pan, cantPan!!) > 0) {
                    str = "$str${greaterThan(pan, cantPan!!)} kilos más de pan\n"
                }
                if(greaterThan(no, cantNo!!) > 0){
                    str = "$str${greaterThan(no, cantNo!!)} kilos más de no comestibles\n"
                }
                if(greaterThan(comida, cantComida!!) > 0){
                    str = "$str${greaterThan(comida, cantComida!!)} kilos más de comida preparada\n"
                }

                check.setTitle("Exceso de entrega")
                    .setMessage("Estás entregando más de lo que hay en el inventario:\n${str}")
                    .setPositiveButton("Okay"){dialog, button ->
                        dialog.dismiss()
                    }.show()
            } else {
                val builder = AlertDialog.Builder(this@InventarioEntregas2)
                if (tn_inv_abarrotes.text.toString() != "") {
                    postAba = "${tn_inv_abarrotes.text} kg"
                    newAba = Keval.eval(cantAbarrotes + "-" + tn_inv_abarrotes.text.toString())
                        .toString()
                    abarrotesStr = tn_inv_abarrotes.text.toString()
                } else {
                    if (cantAbarrotes != null) {
                        newAba = cantAbarrotes
                        abarrotesStr = "0"
                        postAba = "0 kg"
                    }
                }
                if (tn_inv_fruta.text.toString() != "") {
                    postFruta = "${tn_inv_fruta.text} kg"
                    newFruta = Keval.eval(cantFruta + "-" + tn_inv_fruta.text.toString()).toString()
                    frutaStr = tn_inv_fruta.text.toString()
                } else {
                    if (cantFruta != null) {
                        newFruta = cantFruta
                        frutaStr = "0"
                        postFruta = "0 kg"
                    }
                }
                if (tn_inv_pan.text.toString() != "") {
                    postPan = "${tn_inv_pan.text} kg"
                    newPan = Keval.eval(cantPan + "-" + tn_inv_pan.text.toString()).toString()
                    panStr = tn_inv_pan.text.toString()
                } else {
                    if (cantPan != null) {
                        newPan = cantPan
                        panStr = "0"
                        postPan = "0 kg"
                    }
                }
                if (tn_inv_nocomes.text.toString() != "") {
                    postnoComes = "${tn_inv_nocomes.text} kg"
                    newNo = Keval.eval(cantNo + "-" + tn_inv_nocomes.text.toString()).toString()
                    noStr = tn_inv_nocomes.text.toString()
                } else {
                    if (cantNo != null) {
                        newNo = cantNo
                        noStr = "0"
                        postnoComes = "0 kg"
                    }
                }
                if (tn_inv_comidaprep.text.toString() != "") {
                    postComida = "${tn_inv_comidaprep.text} kg"
                    newComida =
                        Keval.eval(cantComida + "-" + tn_inv_comidaprep.text.toString()).toString()
                    comidaStr = tn_inv_comidaprep.text.toString()
                } else {
                    if (cantComida != null) {
                        newComida = cantComida
                        comidaStr = "0"
                        postComida = "0 kg"
                    }
                }
                builder.setTitle("Confirmar inventario")
                    .setMessage(
                        "Abarrotes: ${abarrotesStr} kg\n" +
                                "Fruta y verdura: ${frutaStr} kg\n" +
                                "Pan: ${panStr} kg\n" +
                                "No comestibles: ${noStr} kg\n" +
                                "Comida preparada: ${comidaStr} kg\n"
                    )
                    .setPositiveButton("Confirmar") { dialog, button ->
                        with(inventarioPreferences.edit()) {
                            putString("Abarrotes", newAba)
                            putString("Frutas", newFruta)
                            putString("Pan", newPan)
                            putString("NoComes", newNo)
                            putString("Comida", newComida)
                            commit()
                        }
                        queue = Volley.newRequestQueue(this)
                        val registroBodega = JSONObject()
                        registroBodega.put("route_id", 1)
                        registroBodega.put("bodega_id", id)
                        registroBodega.put("abarrotesStr", "${postAba}")
                        registroBodega.put("frutaStr", "${postFruta}")
                        registroBodega.put("panStr", "${postPan}")
                        registroBodega.put("noStr", "${postnoComes}")
                        registroBodega.put("comidaStr", "${postComida}")

                        val recoleccionRequest = JsonObjectRequest(
                            Request.Method.POST,
                            "http://bamx.denissereginagarcia.com/public/api/raw-item",
                            registroBodega,
                            Response.Listener { response ->
                                Log.e("VolleyResponseTrue", response.toString())
                                val intent = Intent(
                                    this@InventarioEntregas2,
                                    InventarioCompletado::class.java
                                )
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                                overridePendingTransition(R.anim.slide_in_1, R.anim.slide_out_1)
                            },
                            { error ->
                                Log.e("VolleyResponse", error.toString())
                                edt_password.setText("")
                                println("Datos Incorrectos")
                                Toast.makeText(
                                    this@InventarioEntregas2,
                                    "Datos Incorrectos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                        queue.add(recoleccionRequest)

                    }
                    .setNegativeButton("Cancelar") { dialog, button ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    override fun onClick(p0: View?) {
    }


    private fun checkValues(x: String, y: String): Boolean{
        val one: Double = x.toDouble()
        val two: Double = y.toDouble()
        if(one > two){
            return true
        } else{
            return false
        }
    }

    private fun greaterThan(x: String, y: String): Double{
        val one: Double = x.toDouble()
        val two: Double = y.toDouble()
        val result = one - two
        return if(result > 0){
            result
        } else{
            0.0
        }
    }
    override fun onBackPressed(){
        val intent = Intent(this@InventarioEntregas2, InventarioEntregas::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_2, R.anim.slide_out_2)
    }

}