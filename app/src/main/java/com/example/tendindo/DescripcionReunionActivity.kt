package com.example.tendindo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Reunion

class DescripcionReunionActivity : AppCompatActivity() {

    //La ventana que perimite visualizar la informacion de una reunion.
    //Llamado por: CrearReunionActivity
    //Llama a: DescripcionProyectoActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    lateinit var reunion : Reunion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_reunion)

        recibirDatos()

        val btnRegresar = findViewById<Button>(R.id.boton_regresar)
        btnRegresar.setOnClickListener {
            regresar()
        }
    }

    private fun recibirDatos(){
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        val dato2 = bundle?.getSerializable("ListProyectoElement")
        this.proyectoElement = dato2 as ListProyectoElement

        val dato3 = bundle?.getSerializable("Reunion")
        this.reunion = dato3 as Reunion

        val campoTema = findViewById<TextView>(R.id.text_view_tema)
        campoTema.setText(this.reunion.tema)
        val campoMedio = findViewById<TextView>(R.id.text_view_medio)
        campoMedio.setText(this.reunion.medio)
        val campoFecha = findViewById<TextView>(R.id.text_view_fecha)
        campoFecha.setText(this.reunion.fecha)
        val campoHora = findViewById<TextView>(R.id.text_view_hora)
        campoHora.setText(this.reunion.hora)
        val campoColaboradores = findViewById<TextView>(R.id.text_view_invitados)
        campoColaboradores.setText(this.reunion.colaboradores)
    }

    fun regresar(){
        val intent = Intent(this,DescripcionProyectoActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", proyectoElement)
        startActivity(intent)
        this.finish()
    }

    override fun onBackPressed() {
        if(false){
            super.onBackPressed()
        }
        regresar()
    }
}