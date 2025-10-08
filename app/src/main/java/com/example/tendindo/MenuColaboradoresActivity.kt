package com.example.tendindo

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tendindo.utilidades.ListColaboradorAdapter
import com.example.tendindo.utilidades.ListColaboradorElement
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.ListTareaAdapter
import com.example.tendindo.utilidades.ListTareaElement
import com.example.tendindo.utilidades.Tarea
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton

class MenuColaboradoresActivity : AppCompatActivity() {

    //La ventana que perimite ver los colaboradores de un proyecto.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionProyectoActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    var colaboradores = ArrayList<ListColaboradorElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_colaboradores)

        recibirDatos()
        initListaColaboradores()

        val btnAgregar = findViewById<Button>(R.id.boton_agregar_colaborador)
        btnAgregar.setOnClickListener {
            agregarColaborador()
        }
    }


    private fun initListaColaboradores() {

        VolleySingleton.getmInstance(this)
            .colaboradoresDelProyecto(this.proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    colaboradores = response as java.util.ArrayList<ListColaboradorElement>

                    obtenerNombres(0)
                }

                override fun onError(context : Context) {}
            })
    }

    fun obtenerNombres(indice : Int){

        if(indice >= colaboradores.size){
            mostrarColaboradores()
            return
        }

        var elem = colaboradores.get(indice)

        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuarioPorCedula(elem.estado, this, object : VolleySingleton.VolleyCallBack {
                    override fun onSuccess(response: Any, context: Context) {
                        var usuario = response as Usuario

                        elem.setNombre(usuario.nombre)
                        elem.setUsuario(usuario)

                        obtenerNombres(indice+1)
                    }

                    override fun onError(context: Context) {
                        colaboradores.remove(elem)
                        obtenerNombres(indice)
                    }
                })


    }

    fun mostrarColaboradores(){
        val textCargando = findViewById<TextView>(R.id.text_view_cargando_colaboradores)
        textCargando.setText("")

        var listAdapter = ListColaboradorAdapter(colaboradores, this, ListColaboradorAdapter.OnItemClickListener { item -> verColaborador(item)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_colaboradores)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    fun verColaborador(item : ListColaboradorElement){

        var builder = AlertDialog.Builder(this)
        builder.setTitle(item.getUsuario().nombre + " - " + item.getUsuario().cedula)
            .setMessage("¿Quieres quitar a este usuario del proyecto?")//llamar al get cedula
            .setCancelable(true)
            .setPositiveButton("Sí", DialogInterface.OnClickListener { dialog, which -> quitarColaborador(item)})
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> dialog.cancel()})
            .show()
    }

    fun quitarColaborador(item : ListColaboradorElement){

        if(item.usuario.cedula == proyectoElement.proyecto.ced_responsable){
            Toast.makeText(this, "No puedes borrar al responsable del proyecto", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .eliminarColaborador(proyectoElement.proyecto.idProyecto, item.usuario.cedula, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha borrado el colaborador correctamente", Toast.LENGTH_SHORT).show()
                    refrescar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "No se pudo borrar este colaborador", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun agregarColaborador(){

        var campo_cedula = findViewById<EditText>(R.id.campo_cedula_colaborador)
        var cedula = campo_cedula.text.toString()

        if(cedula == ""){
            Toast.makeText(this, "Por favor ingrese una cedula válida.", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .registrarColaborador(proyectoElement.proyecto.idProyecto, cedula, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha agregado el colaborador correctamente", Toast.LENGTH_SHORT).show()
                    refrescar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "No se pudo agregar este colaborador", Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun refrescar(){
        val intent = Intent(this,MenuColaboradoresActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
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