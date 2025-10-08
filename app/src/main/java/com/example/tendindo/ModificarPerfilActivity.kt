package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton

class ModificarPerfilActivity : AppCompatActivity() {

    //La ventana que perimite modificar el perfil del usuario.
    //Llamado por: VerPerfilActivity
    //Llama a: VerPerfilActivity

    lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_perfil)

        recibirDatos()
        cargarCampos()

        val btnModificar = findViewById<Button>(R.id.boton_modificar)
        btnModificar.setOnClickListener {
            modificarPerfil()
        }
        val btnRegresar = findViewById<Button>(R.id.boton_regresar)
        btnRegresar.setOnClickListener {
            regresar()
        }
    }

    fun cargarCampos(){

        val campoNombre = findViewById<TextView>(R.id.campo_nombre)
        campoNombre.setText(usuario.nombre)

        val campoCorreo = findViewById<EditText>(R.id.campo_Correo)
        campoCorreo.setText(usuario.correo)

        val campoTelefono = findViewById<EditText>(R.id.campo_Telefono)
        campoTelefono.setText(usuario.telefono)

        val campoDepartamento = findViewById<EditText>(R.id.campo_Departamento)
        campoDepartamento.setText(usuario.departamento)


    }

    fun recibirDatos(){
        val bundle = intent.extras
        val dato = bundle?.getSerializable("Usuario")
        this.usuario = dato as Usuario
    }

    fun modificarPerfil(){

        val campoCorreo = findViewById<EditText>(R.id.campo_Correo)
        val campoTelefono = findViewById<EditText>(R.id.campo_Telefono)
        val campoDepartamento = findViewById<EditText>(R.id.campo_Departamento)

        var nuevoCorreo = campoCorreo.text.toString()
        var nuevoTelefono = campoTelefono.text.toString()
        var nuevoDepartamento = campoDepartamento.text.toString()


        if(nuevoCorreo == "" || nuevoTelefono == "" || nuevoDepartamento == ""){
            Toast.makeText(this, "Por favor complete todos los campos del perfil", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .modificarUsuario(usuario.cedula, nuevoCorreo, nuevoTelefono, nuevoDepartamento, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha modificado correctamente", Toast.LENGTH_SHORT).show()
                    usuario.correo = nuevoCorreo
                    usuario.telefono = nuevoTelefono
                    usuario.departamento = nuevoDepartamento
                    regresar()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en la actualización del perfil", Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun regresar(){
        val intent = Intent(this,VerPerfilActivity::class.java)
        intent.putExtra("Correo", usuario.correo)
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