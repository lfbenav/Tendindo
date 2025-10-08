package com.example.tendindo

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tendindo.utilidades.ListProyectoAdapter
import com.example.tendindo.utilidades.ListProyectoElement
import com.example.tendindo.utilidades.Proyecto
import com.example.tendindo.utilidades.Usuario
import com.example.tendindo.utilidades.VolleySingleton
import com.example.tendindo.utilidades.VolleySingleton.VolleyCallBack
import com.google.android.material.navigation.NavigationView

class MenuPrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //La ventana que muestra el menu principal de la aplicacion.
    //Llamado por: LoginActivity, DescripcionProyectoActivity, VerPerfilActivity, InformeGeneralActivity, MenuPrincipalActivity
    //Llama a: LoginActivity, MenuPrincipalActivity, VerPerfilActivity, DescripcionProyectoActivity, InformeGeneralActivity, CrearProyectoActivity.

    private lateinit var drawer : DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle
    lateinit var correo : String
    var proyectos = ArrayList<ListProyectoElement>()
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        cargarMenuHamburguesa()
        recibirCorreo()

    }

    private fun cargarMenuHamburguesa(){
        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_menu_principal)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout_menu_principal)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("")

        val navigationView : NavigationView = findViewById(R.id.nav_view_menu_principal)
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun recibirCorreo() {
        val bundle = intent.extras
        val dato = bundle?.getString("Correo")
        var correo = dato as String

        var usuario : Usuario
        VolleySingleton.getmInstance(this)
            .obtenerDatosUsuario(correo, this, object : VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    usuario = response as Usuario
                    var nombre = usuario.getNombre()
                    var correoTemp = usuario.getCorreo()

                    val campoNombre = findViewById<TextView>(R.id.nav_header_nombre)
                    campoNombre.setText(nombre)

                    val campoCorreo = findViewById<TextView>(R.id.nav_header_correo)
                    campoCorreo.setText(correoTemp)

                    var contextMenu = context as MenuPrincipalActivity
                    contextMenu.correo = correoTemp

                    initListaProyectos()
                }

                override fun onError(context : Context) {}
            })


    }

    private fun initListaProyectos() {

        VolleySingleton.getmInstance(this)
            .proyectosDelUsuario(this.correo, this, object : VolleyCallBack {

                override fun onSuccess(response: Any, context : Context) {

                    var ObjetosProyecto = response as ArrayList<Proyecto>
                    for(elem in ObjetosProyecto){
                        proyectos.add(ListProyectoElement(elem))
                    }

                    if(proyectos.isEmpty()){
                        var titulo = findViewById<TextView>(R.id.text_view_PROYECTOS)
                        titulo.setText("No tienes proyectos!")
                    }else{
                        cargarProyectos()
                    }

                }

                override fun onError(context : Context) {}
            })

    }

    fun cargarProyectos(){

        var titulo = findViewById<TextView>(R.id.text_view_PROYECTOS)
        titulo.setText("Proyectos")

        var listAdapter = ListProyectoAdapter(proyectos, this, ListProyectoAdapter.OnItemClickListener { item -> irProyecto(item)})
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_menu_principal)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
    }

    private fun irProyecto(item: ListProyectoElement) {
        val intent = Intent(this,DescripcionProyectoActivity::class.java)
        intent.putExtra("Correo", correo)
        intent.putExtra("ListProyectoElement", item)
        startActivity(intent)
        this.finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_perfil -> irVerPerfil()
            R.id.nav_item_informes -> irInformes()
            R.id.nav_item_crear_proytecto -> irCrearProyecto()
            R.id.nav_item_cerrar_sesion -> cerrarSesion()
            R.id.nav_item_refrescar  -> refrescar()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun irInformes(){
        val intent = Intent(this,InformeGeneralActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }

    fun cerrarSesion(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    fun refrescar(){
        val intent = Intent(this,MenuPrincipalActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }

    fun irCrearProyecto(){
        val intent = Intent(this,CrearProyectoActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }

    fun irVerPerfil(){
        val intent = Intent(this,VerPerfilActivity::class.java)
        intent.putExtra("Correo", correo)
        startActivity(intent)
        this.finish()
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(backPressedTime + 1000 > System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(this, "Presione de nuevo para salir.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}