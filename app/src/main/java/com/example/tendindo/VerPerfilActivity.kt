package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Proyecto
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton

class VerPerfilActivity : AppCompatActivity() {

    //La ventana que perimite visualizar el perfil del usuario.
    //Llamado por: MenuPrincipalActivity
    //Llama a: ModificarPerfilActivity

    lateinit var correo : String
    lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_perfil)

        cargarDatos()

        val botonModificar = findViewById<TextView>(R.id.boton_modificar)
        botonModificar.setOnClickListener {
            irModificarPerfil()
        }

        val botonRegresar = findViewById<TextView>(R.id.boton_regresar)
        botonRegresar.setOnClickListener {
            regresar()
        }

    }

    fun cargarDatos(){

        val bundle = intent.extras
        val dato = bundle?.getString("Correo")
        this.correo = dato as String

        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuario(this.correo, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    var contextPerfil = context as VerPerfilActivity
                    contextPerfil.usuario = response as Usuario
                    escribirInfo()
                }
                override fun onError(context : Context) {}
            })
    }

    fun escribirInfo(){

        val campoNombre = findViewById<TextView>(R.id.text_view_nombre_usuario)
        campoNombre.setText(usuario.nombre)

        val campoCedula = findViewById<TextView>(R.id.text_view_cedula)
        campoCedula.setText(usuario.cedula)

        val campoCorreo = findViewById<TextView>(R.id.text_view_correo)
        campoCorreo.setText(usuario.correo)

        val campoTelefono = findViewById<TextView>(R.id.text_view_telefono)
        campoTelefono.setText(usuario.telefono)

        val campoDepartamento = findViewById<TextView>(R.id.text_view_departamento)
        campoDepartamento.setText(usuario.departamento)

        val campoEstado = findViewById<TextView>(R.id.text_view_estado_usuario)

        VolleySingleton.getmInstance(this)
            .estadoUsuario(this.correo, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    var str = response as String
                    if(str.equals("")){
                        campoEstado.setText("Sin ninguna tarea asignada")
                    }else{
                        campoEstado.setText(str)
                    }
                }

                override fun onError(context : Context) {}
            })

    }

    fun irModificarPerfil(){
        val intent = Intent(this,ModificarPerfilActivity::class.java)
        intent.putExtra("Usuario", usuario)
        startActivity(intent)
        this.finish()
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