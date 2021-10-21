package mx.tec.bamxapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mx.tec.bamxapp.model.RecoleccionesRetrofit
import mx.tec.bamxapp.service.APIRecolecciones
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import mx.tec.bamxapp.databinding.ActivityRutasBinding
import mx.tec.bamxapp.model.EntregasAlmacen
import mx.tec.bamxapp.service.APIEntregasAlmacen

class Rutas : AppCompatActivity(), OnMapReadyCallback, LocationListener {
    lateinit var currentLocation: LatLng
    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityRutasBinding
    private val LOCATION_PERMISSION_REQUEST = 1
    //lateinit var txtLocation: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var locationManager: LocationManager
    lateinit var locationArray: MutableList<LatLng>
    lateinit var namesArray: MutableList<String>
    lateinit var locationBodegasArray: MutableList<LatLng>
    lateinit var namesBodegasArray: MutableList<String>
    lateinit var location: Location
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back = findViewById<ImageButton>(R.id.btn_back_maps1)
        currentLocation = LatLng(0.0, 0.0)

        back.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getLocationAccess()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosSociosMaps: RecoleccionesRetrofit
        locationArray = mutableListOf()
        namesArray = mutableListOf()
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)

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
                    val temp = LatLng(lati, long)
                    locationArray.add(temp)
                    namesArray.add(datosSociosMaps.data[i].socio)
                    // sharedPreferences.edit().putString("location" ,"Latitud ${datosSociosMaps.data[i].latitude}\nLongitud: ${datosSociosMaps.data[i].longitude}").commit()
                }
                for(i in locationArray.indices){
                    map.addMarker(MarkerOptions().position(locationArray[i]).title(namesArray[i])
                        .snippet("Hola jeje").icon(BitmapDescriptorFactory.fromResource(R.drawable.storeicon)))
                }
            }

            override fun onFailure(call: Call<RecoleccionesRetrofit>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })

        ///
        val retrofitAlmacen: Retrofit = Retrofit.Builder()
            .baseUrl("http://bamx.denissereginagarcia.com/public/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var datosEntregasRetrofit: EntregasAlmacen
        locationBodegasArray = mutableListOf()
        namesBodegasArray = mutableListOf()

        val serviceAlmacen = retrofitAlmacen.create<APIEntregasAlmacen>(APIEntregasAlmacen::class.java)
        serviceAlmacen.getEntregas(1).enqueue(object: Callback<EntregasAlmacen> {
            override fun onResponse(
                call: Call<EntregasAlmacen>,
                response: Response<EntregasAlmacen>
            ) {
                datosEntregasRetrofit = response.body()!!
                for(i in datosEntregasRetrofit.data.indices) {
                    val lati = datosEntregasRetrofit.data[i].latitude.toDouble()
                    val long = datosEntregasRetrofit.data[i].longitude.toDouble()
                    val temp = LatLng(lati, long)
                    locationBodegasArray.add(temp)
                    namesBodegasArray.add(datosEntregasRetrofit.data[i].bodega)
                }
                for(i in locationBodegasArray.indices){
                    map.addMarker(MarkerOptions().position(locationBodegasArray[i]).title(namesBodegasArray[i])
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.warehouseicon)))
                }
            }

            override fun onFailure(call: Call<EntregasAlmacen>, t: Throwable) {
                Log.e("RetrofitError", t.message!!)
            }

        })

        val zoomLevel = 15f
        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location?.longitude), zoomLevel))
        }
    }

    private fun getLocationAccess(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED){
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this@Rutas)
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
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
                map.isMyLocationEnabled = true
            } else{
                finish()
            }
        }
    }

    override fun onLocationChanged(p0: Location) {
        //currentLocation = LatLng(p0.latitude, p0.longitude)
        //sharedPreferences.edit().putString("location" ,"Latitud ${p0.latitude}\nLongitud: ${p0.longitude}").commit()
    }

}