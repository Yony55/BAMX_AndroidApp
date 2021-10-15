package mx.tec.bamxapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.tec.bamxapp.model.Entrega
import org.w3c.dom.Text


class EntregaAdapter (
    val context: Context,
    val layout: Int,
    val dataSource: List<Entrega>): BaseAdapter() {

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
        val numEntrega = view.findViewById<TextView>(R.id.tv_numEntrega)
        val bodega = view.findViewById<TextView>(R.id.tv_Bodega)
        val determinante = view.findViewById<TextView>(R.id.tv_determinante)
        val direccion = view.findViewById<TextView>(R.id.tv_direccion)
        val descripcion = view.findViewById<TextView>(R.id.tv_descripcion)
        val elemento = getItem(p0) as Entrega

        numEntrega.text = elemento.numEntrega
        bodega.text = elemento.bodega
        direccion.text = elemento.direccion
        descripcion.text = elemento.descripcion

        return view
    }
}
