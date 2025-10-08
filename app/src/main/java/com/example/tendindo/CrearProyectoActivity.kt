package com.example.tendindo

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Proyecto
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton
import java.util.ArrayList
import java.util.Calendar
import java.util.Date


class CrearProyectoActivity : AppCompatActivity() {

    //La ventana que perimite crear un proyecto.
    //Llamado por: MenuPrincipalActivity
    //Llama a: MenuPrincipalActivity

    lateinit var correo : String
    lateinit var cedula : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_proyecto)

        val bundle = intent.extras
        val dato = bundle?.getString("Correo")
        this.correo = dato as String


        //Obtener cedula
        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuario(this.correo, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    var contextProyecto = context as CrearProyectoActivity
                    var usuario = response as Usuario
                    contextProyecto.cedula = usuario.getCedula()

                    var campo_responsable = findViewById<EditText>(R.id.campo_responsable)
                    campo_responsable.setText(contextProyecto.cedula)

                }

                override fun onError(context : Context) {}
            })


        val btnCrear = findViewById<Button>(R.id.boton_crear)
        btnCrear.setOnClickListener {
            crearProyecto()
        }
        val btnCancelar = findViewById<Button>(R.id.boton_cancelar)
        btnCancelar.setOnClickListener {
            regresar()
        }
        val btnFecha = findViewById<Button>(R.id.boton_fecha)
        btnFecha.setOnClickListener {
            seleccionarFecha()
        }
    }

    fun crearProyecto(){

        var campo_nombre = findViewById<EditText>(R.id.campo_nombre)
        var campo_descripcion = findViewById<EditText>(R.id.campo_descripcion)
        var campo_responsable = findViewById<EditText>(R.id.campo_responsable)
        var campo_fechaInicio = findViewById<EditText>(R.id.campo_fechaInicio)
        var campo_presupuesto = findViewById<EditText>(R.id.campo_presupuesto)
        var campo_recursos = findViewById<EditText>(R.id.campo_recursos)

        var nombre = campo_nombre.text.toString()
        var descripcion = campo_descripcion.text.toString()
        var responsable = campo_responsable.text.toString()
        var fechaInicio = campo_fechaInicio.text.toString()
        var presupuesto = campo_presupuesto.text.toString()
        var recursos = campo_recursos.text.toString()

        if(nombre == "" || descripcion == "" || responsable == "" || fechaInicio == "" || presupuesto == "" || recursos == "") {
            Toast.makeText(this, "Por favor complete todos los campos del proyecto", Toast.LENGTH_SHORT).show()
            return
        }


        VolleySingleton.getmInstance(this)
            .registrarProyecto(nombre, descripcion, responsable, fechaInicio, presupuesto, recursos, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    var idGenerado : Int = response as Int
                    Toast.makeText(context, "Se ha creado correctamente", Toast.LENGTH_SHORT).show()
                    asociarAlProyecto(idGenerado)
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en la creación del proyecto", Toast.LENGTH_SHORT).show()
                }
            })



    }

    fun asociarAlProyecto(idGenerado : Int){


        VolleySingleton.getmInstance(this)
            .registrarColaborador(idGenerado.toString(), cedula, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha agregado el colaborador correctamente", Toast.LENGTH_SHORT).show()
                    regresar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "No se pudo agregar este colaborador", Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun regresar(){
        val intent = Intent(this,MenuPrincipalActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }

    fun seleccionarFecha(){

        var calendario = Calendar.getInstance()
        var ano = calendario.get(Calendar.YEAR)
        var mes = calendario.get(Calendar.MONTH)
        var dia = calendario.get(Calendar.DAY_OF_MONTH)


        var dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            run {

                var campo_fechaInicio = findViewById<EditText>(R.id.campo_fechaInicio)
                campo_fechaInicio.setText(year.toString() + "/" + (month+1).toString() + "/" + dayOfMonth.toString())

            }
        }, ano, mes, dia)

        dialog.show()


    }

    override fun onBackPressed() {
        if(false){
            super.onBackPressed()
        }
        regresar()
    }
}