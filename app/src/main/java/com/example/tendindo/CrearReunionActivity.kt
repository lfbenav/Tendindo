package com.example.tendindo

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Reunion
import java.util.Calendar

class CrearReunionActivity : AppCompatActivity() {

    //La ventana que perimite crear una reunion.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionProyectoActivity, DescripcionReunionActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_reunion)

        recibirDatos()

        val btnFecha = findViewById<Button>(R.id.boton_fecha)
        btnFecha.setOnClickListener {
            seleccionarFecha()
        }

        val btnCrearTarea = findViewById<Button>(R.id.boton_crear)
        btnCrearTarea.setOnClickListener {
            crearReunion()
        }

        val btnCancelar = findViewById<Button>(R.id.boton_cancelar)
        btnCancelar.setOnClickListener {
            regresar()
        }
    }

    fun crearReunion(){

        var campo_tema = findViewById<EditText>(R.id.campo_tema)
        var campo_medio = findViewById<EditText>(R.id.campo_medio)
        var campo_fecha = findViewById<EditText>(R.id.campo_fecha)
        var campo_hora = findViewById<EditText>(R.id.campo_hora)
        var campo_colaboradores = findViewById<EditText>(R.id.campo_invitados)

        var tema = campo_tema.text.toString()
        var medio = campo_medio.text.toString()
        var fecha = campo_fecha.text.toString()
        var hora = campo_hora.text.toString()
        var colaboradores = campo_colaboradores.text.toString()

        if(tema == "" || medio == "" || fecha == "" || hora == "" || colaboradores == "") {
            Toast.makeText(this, "Por favor complete todos los campos de la reunión", Toast.LENGTH_SHORT).show()
            return
        }

        verReunion(Reunion(tema,medio,fecha,hora,colaboradores))
    }

    fun verReunion(reunion : Reunion){
        val intent = Intent(this,DescripcionReunionActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", proyectoElement)
        intent.putExtra("Reunion", reunion)
        startActivity(intent)
        this.finish()
    }

    private fun recibirDatos(){
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        val dato2 = bundle?.getSerializable("ListProyectoElement")
        this.proyectoElement = dato2 as ListProyectoElement
    }

    fun seleccionarFecha(){

        var calendario = Calendar.getInstance()
        var ano = calendario.get(Calendar.YEAR)
        var mes = calendario.get(Calendar.MONTH)
        var dia = calendario.get(Calendar.DAY_OF_MONTH)


        var dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            run {

                var campo_fechaInicio = findViewById<EditText>(R.id.campo_fecha)
                campo_fechaInicio.setText(year.toString() + "/" + (month+1).toString() + "/" + dayOfMonth.toString())

            }
        }, ano, mes, dia)

        dialog.show()

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