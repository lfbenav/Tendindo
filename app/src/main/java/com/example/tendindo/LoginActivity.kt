package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tendindo.utilidades.ConnectSql
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement

class LoginActivity : AppCompatActivity() {

    //La ventana que perimite iniciar sesion en la aplicacion.
    //Llamado por: SignInActivity, MenuPrincipalActivity
    //Llama a: SignInActivity, MenuPrincipalActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnRegistrarse = findViewById<Button>(R.id.boton_registrarse)
        btnRegistrarse.setOnClickListener {
            irRegistro()
        }
        val btnConfirmar = findViewById<Button>(R.id.boton_login)
        btnConfirmar.setOnClickListener {
            validarLogin()
        }
    }

    private fun irRegistro(){
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    public fun validarLogin(){
        var campo_correo = findViewById<EditText>(R.id.campo_Correo)
        var campo_contraseña = findViewById<EditText>(R.id.campo_Contraseña)

        var correo = campo_correo.text.toString()
        var contraseña = campo_contraseña.text.toString()

        if(correo == "" || contraseña == "") {
            Toast.makeText(this, "Por favor complete todos los campos del login", Toast.LENGTH_SHORT).show()
            return
        }

        VolleySingleton.getmInstance(this)
            .iniciarSesion(correo, contraseña, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {
                    Toast.makeText(context, "Se ha iniciado sesion correctamente", Toast.LENGTH_SHORT).show()
                    irMenuPrincipal(correo)
                }

                override fun onError(context : Context) {
                    Toast.makeText(context, "Las credenciales no son válidas", Toast.LENGTH_SHORT).show()
                }
            })

    }

    public fun irMenuPrincipal(correo : String){
        val intent = Intent(this,MenuPrincipalActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }


}