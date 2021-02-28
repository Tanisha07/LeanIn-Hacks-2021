package com.example.foodfightersnew

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_datails.*
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*
import kotlin.collections.HashMap

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    // declaring firebase variables
    var mAuth : FirebaseAuth?=null
    val mAuthListener: FirebaseAuth.AuthStateListener? = null
    var firebaseDatabase : FirebaseDatabase? = null
    var mRef: DatabaseReference? = null
    val locFromFB: ArrayList<String> = ArrayList()
    val nameFromFB: ArrayList<String> = ArrayList()
    val foodFromFB: ArrayList<String> = ArrayList()
    val latFromFB: ArrayList<String> = ArrayList()
    val longFromFB: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // getting firebase instance and initializing
        try {
            mAuth = FirebaseAuth.getInstance()
            firebaseDatabase = FirebaseDatabase.getInstance()
            mRef = firebaseDatabase!!.reference
        }
        catch (e : Exception){
            Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    lateinit var locationManager: LocationManager
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        var userLastLoc = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))




        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location?) {
                if(p0 != null){
                    // getting user location and adding a marker
                    mMap.clear()

                    var userLoc = LatLng(p0!!.latitude, p0!!.longitude)
                    mMap.addMarker(MarkerOptions().position(userLoc).title("You"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLoc, 15f))

                    // add location to firebase
                    try {
//
                        val uuid = Global.curr_uuid
                        val uuidString = uuid.toString()
                        val lat = p0!!.latitude.toString()
                        val long = p0!!.longitude.toString()

                        mRef!!.child("User").child(uuidString).child("latitude").setValue(lat)
                        mRef!!.child("User").child(uuidString).child("longitude").setValue(long)
                        mRef!!.child("User").child(uuidString).child("loc").setValue(userLoc)
                        Toast.makeText(applicationContext, "Location Updated", Toast.LENGTH_LONG).show()

                    }
                    catch (e : Exception){
                        e.printStackTrace()
                        Toast.makeText(applicationContext, "error 4535", Toast.LENGTH_LONG)
                    }
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
//                TODO("Not yet implemented")
            }

            override fun onProviderEnabled(p0: String?) {
//                TODO("Not yet implemented")
            }

            override fun onProviderDisabled(p0: String?) {
//                TODO("Not yet implemented")
                Toast.makeText(applicationContext, "idk y this happening", Toast.LENGTH_LONG).show()
            }

        }

        // checking permissions
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),1)
        }
        else{
            // showing location on map
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2, 2f, locationListener)
            var lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(lastLocation!=null) {
                var userLastLoc = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(userLastLoc).title("You"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLoc, 15f))
            }

            // adding coordinates to firebase
            try {
//
                val uuid = Global.curr_uuid
                val uuidString = uuid.toString()
                val lat = lastLocation!!.latitude.toString()
                val long = lastLocation!!.longitude.toString()

                mRef!!.child("User").child(uuidString).child("latitude").setValue(lat)
                mRef!!.child("User").child(uuidString).child("longitude").setValue(long)
                mRef!!.child("User").child(uuidString).child("loc").setValue(userLastLoc)
                Toast.makeText(applicationContext, "Location Updated", Toast.LENGTH_LONG).show()

            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(applicationContext, "error 4535", Toast.LENGTH_LONG)
            }



        }

        getDataFromFB()

    }

    // if permissions were initially not true
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if(requestCode==1){
            if (grantResults.isNotEmpty()){
                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){

                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun getDataFromFB() {
        var newRef = firebaseDatabase!!.getReference("User")
        newRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                nameFromFB.clear()
                locFromFB.clear()
                foodFromFB.clear()

                // clear map
//                mMap.clear()

                for (snapshot in p0.children) {
                    var hashMap: HashMap<String, Any?> = snapshot.value as HashMap<String, Any?>
                    val name = hashMap["userName"]
                    val loc = hashMap["userLoc"]
                    val lat = hashMap["latitude"].toString()
                    val long = hashMap["longitude"].toString()
                    val type = hashMap["userType"].toString()
                    val food = hashMap["item_name"].toString()

//                    val food = hashMap["loc"]
//                    if(lat==null){
//                        Toast.makeText(applicationContext, "oopsie", Toast.LENGTH_LONG).show()
//                    }
//                    else
//                    Toast.makeText(applicationContext, lat+' '+long.toDouble(), Toast.LENGTH_LONG).show()
//                    val a: List<List<String>> = food.filterIsInstance<String>()

                    if (lat != null && name != null && long!=null) {

                        if(type=="1"){
                            mMap.addMarker(
                                    MarkerOptions().position(
                                            LatLng(
                                                    lat.toDouble(),
                                                    long.toDouble()
                                            )
                                    ).snippet(food)
                            )
                        }
                        else{
                            mMap.addMarker(
                                    MarkerOptions().position(
                                            LatLng(
                                                    lat.toDouble(),
                                                    long.toDouble()
                                            )
                                    ).title(name.toString()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            )
                        }
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLastLoc, 15f))
                    }

                }

            }

        })
    }

}