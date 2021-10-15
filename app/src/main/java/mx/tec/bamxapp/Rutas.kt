package mx.tec.bamxapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Rutas : AppCompatActivity(), LocationListener {
    lateinit var locationManager: LocationManager
    lateinit var txtLocation: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutas)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        txtLocation = findViewById(R.id.txt_location)
        val back = findViewById<ImageButton>(R.id.btn_back_maps1)

        checkPermissions(this)

        back.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

    }

    override fun onLocationChanged(p0: Location) {
        txtLocation.text = "Latitud ${p0.latitude}\nLongitud: ${p0.longitude}"
    }

    private fun checkPermissions(context: Activity){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION)){
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Servicio de unicación requerido")
                    .setMessage("El acceso a la ubicación es requerido para utilizar la aplicación.")
                    .setPositiveButton("Entendido"){ _, _ ->
                        ActivityCompat.requestPermissions(context,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 69)
                    }
                val dialog = builder.create()
                dialog.show()
            } else{
                ActivityCompat.requestPermissions(context,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 69)

            }
        } else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            69 -> {
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    // no se
                } else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this@Rutas)
                }
            }
        }
    }

}