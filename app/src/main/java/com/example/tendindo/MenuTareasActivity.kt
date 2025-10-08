package com.example.tendindo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.ListTareaAdapter
import com.example.tendindo.utilidades.ListTareaElement
import com.example.tendindo.utilidades.Proyecto
import com.example.tendindo.utilidades.Tarea
import com.example.tendindo.utilidades.VolleySingleton

class MenuTareasActivity : AppCompatActivity() {

    //La ventana que perimite visualizar las tareas de un proyecto.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionTareaActivity, DescripcionProyectoActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    var tareas = ArrayList<ListTareaElement>()
    var tareasPorHacer = ArrayList<ListTareaElement>()
    var tareasEnProgreso = ArrayList<ListTareaElement>()
    var tareasFinalizadas = ArrayList<ListTareaElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tareas)

        recibirDatos()
        initListaTareas()
    }



    private fun initListaTareas() {

        VolleySingleton.getmInstance(this)
            .tareasDelProyecto(this.proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    var ObjetosTarea = response as java.util.ArrayList<Tarea>
                    for(elem in ObjetosTarea){
                        tareas.add(ListTareaElement(elem.nombre, elem))
                    }
                    for(i in tareas){
                        when (i.getTarea().estado) {
                            "Por hacer" -> tareasPorHacer.add(i)
                            "En progreso" -> tareasEnProgreso.add(i)
                            "Finalizada" -> tareasFinalizadas.add(i)
                        }
                    }
                    cargarTareasPorHacer()
                    cargarTareasEnProgreso()
                    cargarTareasFinalizadas()

                    var campoProgreso = findViewById<TextView>(R.id.txt_progreso)
                    var total : Float = tareas.size.toFloat()
                    var tareasProgreso : Float = tareasEnProgreso.size.toFloat()
                    var tareasFinalizadas : Float = tareasFinalizadas.size.toFloat()
                    var suma : Float = (tareasProgreso/2) + (tareasFinalizadas)

                    campoProgreso.setText("Progreso: "+ (suma/total)*100 +"%")
                }

                override fun onError(context : Context) {}
            })

    }

    fun cargarTareasPorHacer(){
        var listAdapter = ListTareaAdapter(tareasPorHacer, this, ListTareaAdapter.OnItemClickListener { item -> irTarea(item)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_menu_tareas_por_hacer)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    fun cargarTareasEnProgreso(){
        var listAdapter = ListTareaAdapter(tareasEnProgreso, this, ListTareaAdapter.OnItemClickListener { item -> irTarea(item)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_menu_tareas_en_progreso)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    fun cargarTareasFinalizadas(){
        var listAdapter = ListTareaAdapter(tareasFinalizadas, this, ListTareaAdapter.OnItemClickListener { item -> irTarea(item)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_menu_tareas_finalizadas)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    fun irTarea(item : ListTareaElement){
        //Toast.makeText(this, item.nombre.toString(), Toast.LENGTH_SHORT).show()
        val intent = Intent(this,DescripcionTareaActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", proyectoElement)
        intent.putExtra("ListTareaElement", item)
        startActivity(intent)
        this.finish()
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