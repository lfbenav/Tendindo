package com.example.tendindo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.ListTareaElement

class DescripcionTareaActivity : AppCompatActivity() {

    //La ventana que perimite visualizar la informacion de una tarea.
    //Llamado por: MenuTareasActivity
    //Llama a: MenuTareasActivity, ModificarTareaActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    lateinit var tareaElement : ListTareaElement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_tarea)

        recibirDatos()


        val botonModificar = findViewById<TextView>(R.id.boton_modificar_tarea)
        botonModificar.setOnClickListener {
            irModificarTarea()
        }

        val botonRegresar = findViewById<TextView>(R.id.boton_regresar)
        botonRegresar.setOnClickListener {
            regresar()
        }
    }

    private fun recibirDatos() {
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        val dato2 = bundle?.getSerializable("ListProyectoElement")
        this.proyectoElement = dato2 as ListProyectoElement

        val dato3 = bundle?.getSerializable("ListTareaElement")
        this.tareaElement = dato3 as ListTareaElement


        val campoNombre = findViewById<TextView>(R.id.text_view_nombre_tarea)
        campoNombre.setText(this.tareaElement.tarea.nombre)

        val campoDescripcion = findViewById<TextView>(R.id.text_view_descripcion_tarea)
        campoDescripcion.setText(this.tareaElement.tarea.descripcion)

        val campoRecursos = findViewById<TextView>(R.id.text_view_recursos_necesarios_tarea)
        campoRecursos.setText("₡"+this.tareaElement.tarea.recursos)

        val campoFechaInicio = findViewById<TextView>(R.id.text_view_fecha_inicio_tarea)
        campoFechaInicio.setText(this.tareaElement.tarea.fechaInicio)

        val campoTiempoEstimado = findViewById<TextView>(R.id.text_view_tiempo_estimado_tarea)
        campoTiempoEstimado.setText(this.tareaElement.tarea.tiempoEstimado + " horas")

        val campoStorypoints = findViewById<TextView>(R.id.text_view_cantidad_storypoints_tarea)
        campoStorypoints.setText(this.tareaElement.tarea.cantidadStorypoints)

        val campoResponsable = findViewById<TextView>(R.id.text_view_responsable_tarea)
        campoResponsable.setText(this.tareaElement.tarea.cedulaResponsable)

        val campoEstado = findViewById<TextView>(R.id.text_view_estado_tarea)
        campoEstado.setText(this.tareaElement.tarea.estado)

    }

    fun irModificarTarea(){
        val intent = Intent(this,ModificarTareaActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", proyectoElement)
        intent.putExtra("ListTareaElement", tareaElement)
        startActivity(intent)
        this.finish()
    }

    fun regresar(){
        val intent = Intent(this,MenuTareasActivity::class.java)
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