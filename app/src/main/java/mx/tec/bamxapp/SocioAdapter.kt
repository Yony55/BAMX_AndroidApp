package mx.tec.bamxapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import mx.tec.bamxapp.model.Socio
import org.w3c.dom.Text

class SocioAdapter(val context: Context,
                   val layout: Int,
                   val dataSource: List<Socio>): BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(layout, p2, false)
        val nombre = view.findViewById<TextView>(R.id.txt_NombreSocio)
        val imagen = view.findViewById<ImageView>(R.id.img_SocioLay)
        val determinante = view.findViewById<TextView>(R.id.txt_DeterSocio)
        val direccion = view.findViewById<TextView>(R.id.txt_DirSocio)
        val elemento = getItem(p0) as Socio
        nombre.text = elemento.nombre
        imagen.setImageResource(elemento.imagen)
        determinante.text = elemento.determinante
        direccion.text = elemento.direccion
        return view
    }
}