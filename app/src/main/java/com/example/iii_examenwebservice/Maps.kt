package com.example.iii_examenwebservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

internal class Maps : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap

    private var lat: Double = 0.0
    private var log: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment Wand get notified when the map is ready to be used.

        val coo = intent.getStringExtra("Coo").toString()
        val strs = coo.split("/").toTypedArray()

        lat =  strs.get(0).toDouble()
        log =  strs.get(1).toDouble()



        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val cor = LatLng(lat, log)
        mMap.addMarker(MarkerOptions()
            .position(cor)

            .title("Marker in ULSA"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(cor))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cor, 18.0f))






    }

}