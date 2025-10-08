package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tendindo.utilidades.ConnectSql
import com.example.tendindo.utilidades.VolleySingleton
import java.sql.PreparedStatement
import java.sql.SQLException

class SignInActivity : AppCompatActivity() {

    //La ventana que perimite registrarse en la plataforma.
    //Llamado por: LoginActivity
    //Llama a: LoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnIniciarSesion = findViewById<Button>(R.id.boton_iniciarSesion)
        btnIniciarSesion.setOnClickListener {
            irInicioSesion()
        }
        val btnConfirmar = findViewById<Button>(R.id.boton_signin)
        btnConfirmar.setOnClickListener {
            registrar()
        }
    }

    private fun irInicioSesion(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun registrar(){
        var campo_nombre = findViewById<EditText>(R.id.campo_Nombre)
        var campo_correo = findViewById<EditText>(R.id.campo_Correo)
        var campo_cedula = findViewById<EditText>(R.id.campo_Cedula)
        var campo_telefono = findViewById<EditText>(R.id.campo_Telefono)
        var campo_departamento = findViewById<EditText>(R.id.campo_Departamento)
        var campo_contraseña = findViewById<EditText>(R.id.campo_Contraseña)
        var campo_confirmar_contraseña = findViewById<EditText>(R.id.campo_confirmarContraseña)

        var nombre = campo_nombre.text.toString()
        var correo = campo_correo.text.toString()
        var cedula = campo_cedula.text.toString()
        var telefono = campo_telefono.text.toString()
        var departamento = campo_departamento.text.toString()
        var contraseña = campo_contraseña.text.toString()
        var confirmar_contraseña = campo_confirmar_contraseña.text.toString()

        if(nombre == "" || correo == "" || cedula == "" || telefono == "" || departamento == "" || contraseña == "") {
            Toast.makeText(this, "Por favor complete todos los campos del registro", Toast.LENGTH_SHORT).show()
            return
        }
        if(contraseña != confirmar_contraseña) {
            Toast.makeText(this, "Las contraseñas no concuerdan", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .registrarUsuario(nombre, correo, cedula, telefono, departamento, contraseña, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show()
                    irInicioSesion()
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Ha habido un error en el registro", Toast.LENGTH_SHORT).show()
                }
            })


    }
}