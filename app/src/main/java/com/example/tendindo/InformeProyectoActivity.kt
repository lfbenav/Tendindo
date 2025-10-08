package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.VolleySingleton

class InformeProyectoActivity : AppCompatActivity() {

    //La ventana que perimite visualizar el informe de un proyecto.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionProyectoActivity

    lateinit var campoInforme : TextView;
    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_proyecto)
        recibirDatos()
        mostrarInforme()
    }

    fun mostrarInforme(){
        campoInforme = findViewById<TextView>(R.id.campo_Escribir_informe)
        campoInforme.setText("")
        cargarInformeTiempo()
    }

    fun cargarInformeTiempo(){

        VolleySingleton.getmInstance(this)
            .getAverageTimeForProjectTasks(proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    campoInforme.setText(campoInforme.text.toString() + "\n")
                    campoInforme.setText(campoInforme.text.toString() +"El tiempo promedio de las tareas del proyecto ${proyectoElement.nombre} es $response horas")
                    cargarInformeRecursos()
                }

                override fun onError(context : Context) {}
            })

    }

    fun cargarInformeRecursos(){

        VolleySingleton.getmInstance(this)
            .getAverageResourcesOneProject(proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    campoInforme.setText(campoInforme.text.toString() + "\n\n")
                    campoInforme.setText(campoInforme.text.toString() +"Los recursos promedio de las tareas del proyecto ${proyectoElement.nombre} es $response colones")
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