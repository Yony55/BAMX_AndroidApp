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
import com.google.android.gms.maps.OnMapReadyCallback
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
import mx.tec.bamxapp.databinding.ActivityRutasBinding

class Rutas : AppCompatActivity(), OnMapReadyCallback {
    lateinit var currentLocation: LatLng
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityRutasBinding
    private val LOCATION_PERMISSION_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        val back = findViewById<ImageButton>(R.id.btn_back_maps1)

        back.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getLocationAccess()
        //val zoomLevel = 15f
        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
    }

    private fun getLocationAccess(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
        } else{
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_PERMISSION_REQUEST){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                map.isMyLocationEnabled = true
            } else{
                finish()
            }
        }
    }
}