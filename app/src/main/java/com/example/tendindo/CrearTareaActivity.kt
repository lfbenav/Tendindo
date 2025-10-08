package com.example.tendindo

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton
import java.util.Calendar

class CrearTareaActivity : AppCompatActivity() {

    //La ventana que perimite crear una tarea de un proyecto.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionProyectoActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    lateinit var cedula : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_tarea)

        recibirDatos()

        val btnFecha = findViewById<Button>(R.id.boton_fecha_tarea)
        btnFecha.setOnClickListener {
            seleccionarFecha()
        }

        val btnCrearTarea = findViewById<Button>(R.id.boton_confirmar_tarea)
        btnCrearTarea.setOnClickListener {
            crearTarea()
        }

        val btnCancelar = findViewById<Button>(R.id.boton_cancelar_tarea)
        btnCancelar.setOnClickListener {
            regresar()
        }

        //Obtener cedula
        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuario(this.correo, this, object : VolleySingleton.VolleyCallBack {
                override fun onSuccess(response: Any, context : Context) {
                    var contextProyecto = context as CrearTareaActivity
                    var usuario = response as Usuario
                    contextProyecto.cedula = usuario.getCedula()

                    var campo_responsable = findViewById<EditText>(R.id.campo_responsable_tarea)
                    campo_responsable.setText(contextProyecto.cedula)
                }
                override fun onError(context : Context) {}
            })

    }

    private fun recibirDatos() {
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        val dato2 = bundle?.getSerializable("ListProyectoElement")
        this.proyectoElement = dato2 as ListProyectoElement
    }

    fun crearTarea(){

        var campo_nombre = findViewById<EditText>(R.id.campo_nombre_tarea)
        var campo_descripcion = findViewById<EditText>(R.id.campo_descripcion_tarea)
        var campo_recursos = findViewById<EditText>(R.id.campo_recursos_tarea)
        var campo_fechaInicio = findViewById<EditText>(R.id.campo_fecha_inicio_tarea)
        var campo_tiempoEstimado = findViewById<EditText>(R.id.campo_tiempo_estimado_tarea)
        var campo_estado = findViewById<Spinner>(R.id.campo_estado_tarea)
        var campo_cantidadStorypoints = findViewById<EditText>(R.id.campo_cantidad_storypoints_tarea)
        var campo_responsable = findViewById<EditText>(R.id.campo_responsable_tarea)

        var nombre = campo_nombre.text.toString()
        var descripcion = campo_descripcion.text.toString()
        var recursos = campo_recursos.text.toString()
        var fechaInicio = campo_fechaInicio.text.toString()
        var tiempoEstimado = campo_tiempoEstimado.text.toString()
        var estado = campo_estado.selectedItem.toString()
        var storypoints = campo_cantidadStorypoints.text.toString()
        var responsable = campo_responsable.text.toString()

        if(nombre == "" || descripcion == "" || recursos == "" || fechaInicio == "" || tiempoEstimado == "" || estado == "" || storypoints == "" || responsable == "") {
            Toast.makeText(this, "Por favor complete todos los campos de la tarea", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .registrarTarea(nombre, descripcion, recursos, fechaInicio, tiempoEstimado, estado, storypoints, responsable, proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha creado correctamente", Toast.LENGTH_SHORT).show()
                    regresar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en la creación de la tarea", Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun seleccionarFecha(){

        var calendario = Calendar.getInstance()
        var ano = calendario.get(Calendar.YEAR)
        var mes = calendario.get(Calendar.MONTH)
        var dia = calendario.get(Calendar.DAY_OF_MONTH)


        var dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            run {

                var campo_fechaInicio = findViewById<EditText>(R.id.campo_fecha_inicio_tarea)
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