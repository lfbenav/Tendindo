package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.tendindo.utilidades.VolleySingleton

class InformeGeneralActivity : AppCompatActivity() {

    //La ventana que perimite visualizar el informe general de todos los proyectos de la plataforma.
    //Llamado por: MenuPrincipalActivity
    //Llama a: MenuPrincipalActivity

    lateinit var correo : String
    lateinit var campoInforme : TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_general)
        recibirDatos()
        mostrarInforme()
    }

    private fun mostrarInforme(){

        campoInforme = findViewById<TextView>(R.id.campo_Escribir_informe)
        campoInforme.setText("")
        informeHorasPromedioTodosLosProyecto()

    }

    fun informeHorasPromedioTodosLosProyecto(){

        VolleySingleton.getmInstance(this)
            .getAverageTimeForAllTasks( this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    var i = 0
                    var respuesta = response as ArrayList<String>
                    campoInforme.setText(campoInforme.text.toString() + "\n")
                    while (i < respuesta.size) {
                        var str = "Proyecto: " + respuesta.get(i) +".\tHoras promedio: " + respuesta.get(i + 1) + ".\n"
                        campoInforme.setText(campoInforme.text.toString() + str)
                        i += 2
                    }
                    informeRecursosPromedioTodosLosProyecto()
                }

                override fun onError(context : Context) {}
            })

    }

    fun informeRecursosPromedioTodosLosProyecto(){

        VolleySingleton.getmInstance(this)
            .getAverageResourcesPerProject( this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    var i = 0
                    var respuesta = response as ArrayList<String>
                    campoInforme.setText(campoInforme.text.toString() + "\n\n")
                    while (i < respuesta.size) {
                        var str = "Proyecto: " + respuesta.get(i) +".\tRecursos promedio: " + respuesta.get(i + 1) + " colones.\n"
                        campoInforme.setText(campoInforme.text.toString() + str)
                        i += 2
                    }
                    informeProyectoConMasRecursos()
                }

                override fun onError(context : Context) {}
            })
    }

    fun informeProyectoConMasRecursos(){
        VolleySingleton.getmInstance(this)
            .getProjectWithHighestResources( this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    try {
                        var respuesta = response as ArrayList<String>
                        campoInforme.setText(campoInforme.text.toString() + "\n\n")
                        var str = "El proyecto con mas recursos es " + respuesta.get(1) + ", con " + respuesta.get(0) + " colones."
                        campoInforme.setText(campoInforme.text.toString() + str)
                    }catch (ex : Exception){
                        campoInforme.setText(campoInforme.text.toString() + "\n\n" + "No hay datos")
                    }
                }

                override fun onError(context : Context) {
                    campoInforme.setText("No hay datos")
                }
            })
    }

    private fun recibirDatos() {
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String
    }

    fun regresar(){
        val intent = Intent(this,MenuPrincipalActivity::class.java)
        intent.putExtra("Correo", correo)
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