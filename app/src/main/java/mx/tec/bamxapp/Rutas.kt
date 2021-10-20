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
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.tec.bamxapp.model.RecoleccionesRetrofit
import mx.tec.bamxapp.model.Socio
import mx.tec.bamxapp.service.APIRecolecciones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rutas : AppCompatActivity(), LocationListener {
    lateinit var locationManager: LocationManager
    lateinit var map: GoogleMap
    lateinit var currentLocation: LatLng
    //lateinit var txtLocation: TextView
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutas)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosSociosMaps: RecoleccionesRetrofit
        val locationArray = mutableListOf<LatLng>()
        val namesArray = mutableListOf<String>()

        val service = retrofit.create<APIRecolecciones>(APIRecolecciones::class.java)
        service.getRecolecciones(1).enqueue(object: Callback<RecoleccionesRetrofit> {
            override fun onResponse(
                call: Call<RecoleccionesRetrofit>,
                response: Response<RecoleccionesRetrofit>
            ) {
                datosSociosMaps = response.body()!!
                for(i in datosSociosMaps.data.indices){
                    val lati = datosSociosMaps.data[i].latitude.toDouble()
                    val long = datosSociosMaps.data[i].longitude.toDouble()
                    locationArray.add(LatLng(lati, long))
                    namesArray.add(datosSociosMaps.data[i].socio)
                }
            }

            override fun onFailure(call: Call<RecoleccionesRetrofit>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })

        /////////////




        val listOfLocations = listOf(
            LatLng(18.9365141, -99.2474205),
            LatLng(18.9223617, -99.2129684),
            LatLng(18.934398, -99.1975317),
            LatLng(18.9328423, -99.229588),
            LatLng(18.9335512, -99.2188406),
            LatLng(18.920793, -99.1999568),
            LatLng(18.9536334, -99.245508),
            LatLng(18.9334435, -99.2304862)
        )



        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //txtLocation = findViewById(R.id.txt_location)
        val back = findViewById<ImageButton>(R.id.btn_back_maps1)

        checkPermissions(this)

        var mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

        mapFragment.getMapAsync{ googleMap ->
            map = googleMap
            map.isMyLocationEnabled = true

            for(i in locationArray.indices){
                val location = locationArray[i]
                map.addMarker(MarkerOptions()
                    .position(location)
                    .title(namesArray[i]))
            }
        }

        back.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

    }

    override fun onLocationChanged(p0: Location) {
        //txtLocation.text = "Latitud ${p0.latitude}\nLongitud: ${p0.longitude}"
        currentLocation = LatLng(p0.latitude, p0.longitude)
    }

    private fun checkPermissions(context: Activity){
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION)){
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Servicio de ubicación requerido")
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
                    //ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 420)
                } else{
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this@Rutas)
                }
            }
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //super.onStatusChanged(provider, status, extras)
    }

}