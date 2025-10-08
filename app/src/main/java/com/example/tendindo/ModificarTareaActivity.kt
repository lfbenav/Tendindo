package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.ListTareaElement
import com.example.tendindo.utilidades.VolleySingleton

class ModificarTareaActivity : AppCompatActivity() {

    //La ventana que perimite modificar la información de una tarea.
    //Llamado por: DescripcionTareaActiviy
    //Llama a: DescripcionTareaActivity, MenuTareasActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    lateinit var tareaElement : ListTareaElement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_tarea)

        recibirDatos()
        cargarCampos()

        val btnModificar = findViewById<Button>(R.id.boton_modificar)
        btnModificar.setOnClickListener {
            modificarTarea()
        }
        val btnBorrar = findViewById<Button>(R.id.boton_eliminar)
        btnBorrar.setOnClickListener {
            eliminarTarea()
        }
        val btnRegresar = findViewById<Button>(R.id.boton_regresar)
        btnRegresar.setOnClickListener {
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

    }

    fun cargarCampos(){
        val campoNombre = findViewById<TextView>(R.id.campo_nombre_tarea)
        campoNombre.setText(tareaElement.tarea.nombre)
        val campoDescripcion = findViewById<TextView>(R.id.campo_descripcion_tarea)
        campoDescripcion.setText(tareaElement.tarea.descripcion)
        val campoResponsable = findViewById<TextView>(R.id.campo_responsable_tarea)
        campoResponsable.setText(tareaElement.tarea.cedulaResponsable)
        val campoRecursos = findViewById<TextView>(R.id.campo_recursos_economicos_tarea)
        campoRecursos.setText(tareaElement.tarea.recursos)
        val campoTiempoEstimado = findViewById<TextView>(R.id.campo_tiempo_estimado_tarea)
        campoTiempoEstimado.setText(tareaElement.tarea.tiempoEstimado)
        val campoStorypoints = findViewById<TextView>(R.id.campo_cantidad_storypoints_tarea_tarea)
        campoStorypoints.setText(tareaElement.tarea.cantidadStorypoints)
    }

    fun modificarTarea(){
        val campoDescripcion = findViewById<TextView>(R.id.campo_descripcion_tarea)
        val campoResponsable = findViewById<TextView>(R.id.campo_responsable_tarea)
        val campoRecursos = findViewById<TextView>(R.id.campo_recursos_economicos_tarea)
        val campoTiempoEstimado = findViewById<TextView>(R.id.campo_tiempo_estimado_tarea)
        val campoStorypoints = findViewById<TextView>(R.id.campo_cantidad_storypoints_tarea_tarea)
        var campo_estado = findViewById<Spinner>(R.id.campo_estado_tarea)


        var descripcion = campoDescripcion.text.toString()
        var responsable = campoResponsable.text.toString()
        var recursos = campoRecursos.text.toString()
        var tiempoEstimado = campoTiempoEstimado.text.toString()
        var storypoints = campoStorypoints.text.toString()
        var estado = campo_estado.selectedItem.toString()

        if(descripcion == "" || responsable == "" || recursos == "" || tiempoEstimado == "" || storypoints == ""){
            Toast.makeText(this, "Por favor complete todos los campos de la tarea", Toast.LENGTH_SHORT).show()
            return
        }


        VolleySingleton.getmInstance(this)
            .modificarTarea(tareaElement.tarea.idTarea, tareaElement.tarea.nombre, descripcion, tareaElement.tarea.fechaInicio, estado, responsable, tareaElement.tarea.idProyecto, tiempoEstimado, storypoints, recursos, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show()
                    tareaElement.tarea.descripcion = descripcion
                    tareaElement.tarea.estado = estado
                    tareaElement.tarea.cedulaResponsable = responsable
                    tareaElement.tarea.recursos = recursos
                    tareaElement.tarea.tiempoEstimado = tiempoEstimado
                    tareaElement.tarea.cantidadStorypoints = storypoints
                    regresar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en la actualización de la tarea", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun eliminarTarea(){

        VolleySingleton.getmInstance(this)
            .eliminarTarea(tareaElement.tarea.idTarea, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha borrado correctamente", Toast.LENGTH_SHORT).show()
                    regresarMenuTareas()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en borrar la tarea", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun regresar(){
        val intent = Intent(this,DescripcionTareaActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", proyectoElement)
        intent.putExtra("ListTareaElement", tareaElement)
        startActivity(intent)
        this.finish()
    }

    fun regresarMenuTareas(){
        val intent = Intent(this,MenuTareasActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
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