package com.example.iii_examenwebservice

import android.graphics.BitmapFactory
import android.util.Base64
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var btn_mapa: Button
    private  lateinit var  txtbName: EditText
    private  lateinit var  txtbDir: EditText
    private  lateinit var  txtbAp: EditText
    private  lateinit var  txtbAm: EditText
    private  lateinit var  txtbS: EditText
    private  lateinit var  imageView: ImageView

    private var log: String? = null
    private var lat: String? = null
    private var foto: String = "null"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtbName = findViewById(R.id.txtB_wsN)
        txtbDir = findViewById(R.id.txtB_wsD)
        txtbAp = findViewById(R.id.txtB_wsAP)
        txtbAm = findViewById(R.id.txtB_wsAM)
        txtbS = findViewById(R.id.txtB_wsS)
        btn_mapa = findViewById(R.id.btn_map)
        imageView = findViewById(R.id.imageView)


        btn_mapa.setOnClickListener {
            val intent = Intent(this,  Maps::class.java)

            intent.putExtra("Coo",  (lat.toString() +"/" +log.toString())  )
            startActivity(intent)
        }

        var url = "https://gist.githubusercontent.com/Luis106/321146a135de6e959cbc68504db008fe/raw/25dbad9ccfa1540d9f6e9fcd08f55c2f6d89945b/gistfile1.txt"
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener { response ->
            Log.d("response","La respuesta es ${response}")

            val contact = Gson().fromJson(response, info::class.java)

            txtbName.setText(contact.Nombre)
            txtbDir.setText(contact.Direccion)
            txtbAp.setText(contact.ApellidoP)
            txtbAm.setText(contact.ApellidoM)
            txtbS.setText(contact.Sangre)
                lat = contact.Latitud
                log = contact.Longitud
                foto = contact.foto

                val profilePicture = foto
                val base64Image: String = profilePicture.split(",").get(1)
                val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                imageView.setImageBitmap(decodedByte)

        },
            Response.ErrorListener {

            })

        queue.add(stringRequest)

    }
}