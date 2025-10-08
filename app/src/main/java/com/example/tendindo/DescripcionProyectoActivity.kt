package com.example.tendindo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tendindo.utilidades.ConnectSql
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton
import com.google.android.material.navigation.NavigationView

class DescripcionProyectoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    //La ventana que perimite visualizar la informacion de un proyecto.
    //Llamado por: MenuPrincipalActivity, InformeProyectoActivity, MenuTareasActivity, CrearTareaActivity, CrearReunionActivity
    //Llama a: MenuColaboradoresActivity, InformeProyectoActivity, MenuTareasActivity, CrearTareaActivity, CrearReunionActivity

    private lateinit var drawer : DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle
    lateinit var correo : String
    lateinit var nombreProyecto : String
    lateinit var proyectoElement : ListProyectoElement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_proyecto)

        cargarMenuHamburguesa()
        recibirDatos()
        recibirCorreo()

    }

    private fun cargarMenuHamburguesa(){
        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_menu_principal)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout_proyectos)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("")

        val navigationView : NavigationView = findViewById(R.id.nav_view_menu_principal)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_colaboradores -> irColaboradores()
            R.id.nav_item_informe -> irInformes()
            R.id.nav_item_foro -> irForo()
            R.id.nav_item_ver_tareas -> irVerTareas()
            R.id.nav_item_crear_tarea -> irCrearTarea()
            R.id.nav_item_crear_reunion -> irCrearReunion()

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun irInformes(){
        val intent = Intent(this,InformeProyectoActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
    }

    fun irForo(){
        val intent = Intent(this,ForoActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
    }

    fun irCrearReunion(){
        val intent = Intent(this,CrearReunionActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
    }

    fun irColaboradores(){
        val intent = Intent(this,MenuColaboradoresActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
    }

    fun irVerTareas(){
        val intent = Intent(this,MenuTareasActivity::class.java)
        intent.putExtra("Correo", this.correo)
        intent.putExtra("ListProyectoElement", this.proyectoElement)
        startActivity(intent)
        this.finish()
    }

    fun irCrearTarea(){
        val intent = Intent(this,CrearTareaActivity::class.java)
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

        this.nombreProyecto = proyectoElement.proyecto.nombre_proyecto

        var proyectoObjeto = proyectoElement.proyecto

        val campoNombre = findViewById<TextView>(R.id.text_view_nombre_proyecto)
        campoNombre.setText(this.nombreProyecto)

        val campoDescripcion = findViewById<TextView>(R.id.text_view_descripcion_proyecto)
        campoDescripcion.setText(proyectoObjeto.descripcion)

        val campoResponsable = findViewById<TextView>(R.id.text_view_nombre_encargado)
        campoResponsable.setText(proyectoObjeto.ced_responsable)

        val campoFecha = findViewById<TextView>(R.id.text_view_fecha_inicio)
        campoFecha.setText(proyectoObjeto.fechaInicio)

        val campoPresupuesto = findViewById<TextView>(R.id.text_view_presupuesto)
        campoPresupuesto.setText("₡"+proyectoObjeto.presupuesto)

        val campoRecursos = findViewById<TextView>(R.id.text_view_recursos_necesarios)
        campoRecursos.setText(proyectoObjeto.recursosNecesarios)

        val campoEstado = findViewById<TextView>(R.id.text_view_estado)
        campoEstado.setText(proyectoObjeto.estado)
    }

    private fun recibirCorreo() {

        var usuario : Usuario
        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuario(this.correo, this, object : VolleySingleton.VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    usuario = response as Usuario
                    var nombre = usuario.getNombre()

                    val campoNombre = findViewById<TextView>(R.id.nav_header_nombre)
                    campoNombre.setText(nombre)
                }

                override fun onError(context : Context) {}
            })

        val campoCorreo = findViewById<TextView>(R.id.nav_header_correo)
        campoCorreo.setText(this.correo)
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