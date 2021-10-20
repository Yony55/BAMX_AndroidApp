package mx.tec.bamxapp.model

data class Recoleccion (

    var numRecoleccion: Int,
    var socio: String,
    var socio_id: Int,
    var determinante: String,
    var direccion:String,
    var descripcion: String,
    val latitude: String,
    val longitude: String
)

