package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tendindo.utilidades.ListProyectoAdapter
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Mensaje
import com.example.tendindo.utilidades.MensajeAdapter
import com.example.tendindo.utilidades.VolleySingleton

class ForoActivity : AppCompatActivity() {

    //La ventana que perimite visualizar el foro de un proyecto.
    //Llamado por: DescripcionProyectoActivity
    //Llama a: DescripcionProyectoActivity

    lateinit var proyectoElement : ListProyectoElement
    lateinit var correo : String
    var mensajes = ArrayList<Mensaje>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foro)

        recibirDatos()
        cargarMensajes()

        val btnEnviar = findViewById<Button>(R.id.boton_enviar)
        btnEnviar.setOnClickListener {
            enviarMensaje()
        }
    }


    fun cargarMensajes(){
        //mensajes.add(Mensaje("ME TIRE UN PEDO","Luis",proyectoElement.proyecto.idProyecto))
        //mensajes.add(Mensaje("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","Alguien que ocupa ayuda",proyectoElement.proyecto.idProyecto))
        VolleySingleton.getmInstance(this)
            .recibirMensajes(proyectoElement.proyecto.idProyecto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    mensajes = response as ArrayList<Mensaje>
                    mostrarMensajes()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Error cargando mensajes", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun mostrarMensajes(){
        var listAdapter = MensajeAdapter(mensajes, this, MensajeAdapter.OnItemClickListener { item -> println(item.mensaje)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_mensajes)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    fun enviarMensaje(){
        var texto = findViewById<EditText>(R.id.edit_text_mensaje).text.toString()
        if(texto == ""){
            return
        }
        //var mensaje = Mensaje(texto,correo,proyectoElement.proyecto.idProyecto)

        VolleySingleton.getmInstance(this)
            .crearMensaje(proyectoElement.proyecto.idProyecto, correo, texto, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    refrescar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Error enviando mensaje", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun recibirDatos() {
        val bundle = intent.extras

        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        val dato2 = bundle?.getSerializable("ListProyectoElement")
        this.proyectoElement = dato2 as ListProyectoElement
    }

    fun refrescar(){
        val intent = Intent(this,ForoActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
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