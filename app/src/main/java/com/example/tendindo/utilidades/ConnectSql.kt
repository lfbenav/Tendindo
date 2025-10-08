package com.example.tendindo.utilidades

import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tendindo.R
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement
import java.util.ArrayList

class ConnectSql {
    private val ip = "192.168.50.43:51319"
    private val db = "TendindoTasking"
    private val username = "usuarioTendindo"
    private val password = "usuarioTendindo"

    fun dbConn(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        val connString : String
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password"
            conn = DriverManager.getConnection(connString)
        }catch (ex: SQLException){
            Log.e("Error: ", ex.message!!)
        }catch (ex1: ClassNotFoundException){
            Log.e("Error: ", ex1.message!!)
        }catch (ex2: Exception){
            Log.e("Error :", ex2.message!!)
        }
        return conn
    }

    fun obtenerDatosUsuario(correo: String): List<String>{
        var datos = ArrayList<String>()
        try{
            val query: PreparedStatement = dbConn()?.prepareStatement("SELECT * FROM USUARIO WHERE correo = '$correo'")!!
            var statement : Statement = dbConn()!!.createStatement()
            var resultado = statement.executeQuery(query.toString())

            if(resultado.next()){
                datos.add(resultado.getString(1))
                datos.add(resultado.getString(2))
                datos.add(resultado.getString(3))
                datos.add(resultado.getString(4))
                datos.add(resultado.getString(5))
                datos.add(resultado.getString(6))
            }
        }catch (ex: SQLException){
        }catch (ex1: Exception){
        }
        return datos
    }

    fun registrarUsuario(nombre : String, correo : String, cedula : String, telefono : String, departamento : String, contraseña : String) : Boolean{

        try{
            val addUsuario: PreparedStatement = dbConn()?.prepareStatement("INSERT INTO USUARIO (nombre, correo, cedula, telefono, departamento, contraseña) VALUES (?,?,?,?,?,?)")!!
            addUsuario.setString(1, nombre)
            addUsuario.setString(2, correo)
            addUsuario.setString(3, cedula)
            addUsuario.setString(4, telefono)
            addUsuario.setString(5, departamento)
            addUsuario.setString(6, contraseña)
            addUsuario.executeUpdate()

            return true
        }catch (ex: SQLException){
            return false
        }catch (ex1: Exception){
            return false
        }

    }

    fun validarCredenciales(correo : String, contraseña : String) : Boolean{
        try{
            val query: PreparedStatement = dbConn()?.prepareStatement("SELECT * FROM USUARIO WHERE correo = '$correo' AND contraseña = '$contraseña'")!!
            var statement : Statement = dbConn()!!.createStatement()
            var resultado = statement.executeQuery(query.toString())

            if(!resultado.next()){
                return false
            }

            return true
        }catch (ex: SQLException){
            return false
        }catch (ex1: Exception){
            return false
        }

    }

    fun obtenerDatosProyecto(nombre: String): List<String>{
        var datos = ArrayList<String>()
        try{
            val query: PreparedStatement = dbConn()?.prepareStatement("SELECT * FROM PROYECTO WHERE nombre = '$nombre'")!!
            var statement : Statement = dbConn()!!.createStatement()
            var resultado = statement.executeQuery(query.toString())

            if(resultado.next()){
                datos.add(resultado.getString(1))
                datos.add(resultado.getString(2))
                datos.add(resultado.getString(3))
                datos.add(resultado.getString(4))
                datos.add(resultado.getString(5))
                datos.add(resultado.getString(6))
                datos.add(resultado.getString(7))
            }
        }catch (ex: SQLException){
        }catch (ex1: Exception){
        }
        return datos
    }

}