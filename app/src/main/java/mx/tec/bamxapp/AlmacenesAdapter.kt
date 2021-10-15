package mx.tec.bamxapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import mx.tec.bamxapp.model.Almacenes


class AlmacenesAdapter (val context: Context,
                        val layout: Int,
                        val dataSource: List<Almacenes>): BaseAdapter() {

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {


        val view = inflater.inflate(layout, p2, false)
        val nombre = view.findViewById<TextView>(R.id.tv_NombreAlmacen)
        val imagen = view.findViewById<ImageView>(R.id.img_AlmacenLay)
        val direccion = view.findViewById<TextView>(R.id.tv_DirAlmacen)

        val elemento = getItem(p0) as Almacenes
        nombre.text = elemento.nombre
        imagen.setImageResource(elemento.imagen)
        direccion.text = elemento.direccion
        return view
    }
}