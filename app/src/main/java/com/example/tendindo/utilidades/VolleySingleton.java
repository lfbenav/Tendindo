//package com.example.tendindo.utilidades;
//
//import android.content.Context;
//import androidx.appcompat.app.AppCompatActivity;
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//
//public class VolleySingleton extends AppCompatActivity {
//
//    public interface VolleyCallBack {
//        void onSuccess(Object resultado, Context context);
//        void onError(Context context);
//    }
//
//    private RequestQueue requestQueue;
//    private static VolleySingleton mInstance;
//
//    private VolleySingleton(Context context){
//        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
//    }
//
//    public static synchronized VolleySingleton getmInstance(Context context){
//
//        if(mInstance == null){
//            mInstance = new VolleySingleton(context);
//        }
//        return mInstance;
//    }
//
//    public RequestQueue getRequestQueue(){
//        return requestQueue;
//    }
//
//
//    public ArrayList<Usuario> obtenerTodosUsuarios() {
//
//        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
//
//        String url = "https://requeapi-1.onrender.com/usuarios";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String nombre = jsonObject.getString("nombre");
//                                String correo = jsonObject.getString("correo");
//                                String cedula = jsonObject.getString("cedula");
//                                String telefono = jsonObject.getString("telefono");
//                                String departamento = jsonObject.getString("departamento");
//                                String contrasnha = jsonObject.getString("contrasnha");
//
//                                Usuario usuario = new Usuario(nombre,correo,cedula,telefono,departamento,contrasnha);
//                                usuarios.add(usuario);
//
//                                System.out.println("Usuario: -----------------------");
//                                System.out.println(usuario.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//        return usuarios;
//    }
//
//    public ArrayList<Proyecto> obtenerTodosProyectos(Context context, final VolleyCallBack callBack) {
//
//        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
//
//        String url = "https://requeapi-1.onrender.com/getProjects";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String idProyecto = jsonObject.getString("idProyecto");
//                                String nombre_proyecto = jsonObject.getString("nombre_proyecto");
//                                String descripcion = jsonObject.getString("descripcion");
//                                String fechaInicio = jsonObject.getString("fechaInicio");
//                                String estado = jsonObject.getString("estado");
//                                String ced_responsable = jsonObject.getString("ced_responsable");
//                                String presupuesto = jsonObject.getString("presupuesto");
//                                String recursosNecesarios = jsonObject.getString("recursosNecesarios");
//
//                                Proyecto proyecto = new Proyecto(idProyecto,nombre_proyecto,descripcion,fechaInicio,estado,ced_responsable, presupuesto, recursosNecesarios);
//                                proyectos.add(proyecto);
//
//                                System.out.println("Proyecto: -----------------------");
//                                System.out.println(proyecto.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(proyectos, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//        return proyectos;
//    }
//
//    public void obtenerProyectoPorNombre(String nombre, Context context, final VolleyCallBack callBack) {
//
//        String url = "https://requeapi-1.onrender.com/getProjects";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String idProyecto = jsonObject.getString("idProyecto");
//                                String nombre_proyecto = jsonObject.getString("nombre_proyecto");
//                                String descripcion = jsonObject.getString("descripcion");
//                                String fechaInicio = jsonObject.getString("fechaInicio");
//                                String estado = jsonObject.getString("estado");
//                                String ced_responsable = jsonObject.getString("ced_responsable");
//                                String presupuesto = jsonObject.getString("presupuesto");
//                                String recursosNecesarios = jsonObject.getString("recursosNecesarios");
//
//                                Proyecto proyecto = new Proyecto(idProyecto,nombre_proyecto,descripcion,fechaInicio,estado,ced_responsable, presupuesto, recursosNecesarios);
//
//                                if(proyecto.nombre_proyecto.toString().equals(nombre.toString())){
//                                    callBack.onSuccess(proyecto, context);
//                                    return;
//                                }
//
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//    }
//
//
//    public void obtenerDatosUsuario(String correo, Context context, final VolleyCallBack callBack) {
//
//        String url = "https://requeapi-1.onrender.com/usuarios/correo/" + correo;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>(){
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Usuario usuario;
//                        try{
//
//                            String nombre = response.getString("nombre");
//                            String correo = response.getString("correo");
//                            String cedula = response.getString("cedula");
//                            String telefono = response.getString("telefono");
//                            String departamento = response.getString("departamento");
//                            String contrasnha = response.getString("contrasnha");
//
//                            usuario = new Usuario(nombre,correo,cedula,telefono,departamento,contrasnha);
//
//                            System.out.println("Usuario: -----------------------");
//                            System.out.println(usuario.toString());
//
//                            callBack.onSuccess(usuario, context);
//
//                        }catch(JSONException e){
//                            System.out.println("Error JSON");
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, error -> {
//                                System.out.println("Error volley");
//                            });
//
//        requestQueue.add(jsonObjectRequest);
//
//    }
//
//    public void obtenerDatosUsuarioPorCedula(String cedula, Context context, final VolleyCallBack callBack) {
//
//        String url = "https://requeapi-1.onrender.com/usuarios/" + cedula;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>(){
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Usuario usuario;
//                        try{
//
//                            String nombre = response.getString("nombre");
//                            String correo = response.getString("correo");
//                            String cedula = response.getString("cedula");
//                            String telefono = response.getString("telefono");
//                            String departamento = response.getString("departamento");
//                            String contrasnha = response.getString("contrasnha");
//
//                            usuario = new Usuario(nombre,correo,cedula,telefono,departamento,contrasnha);
//
//                            System.out.println("Usuario: -----------------------");
//                            System.out.println(usuario.toString());
//
//                            callBack.onSuccess(usuario, context);
//
//                        }catch(JSONException e){
//                            System.out.println("Error JSON");
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, error -> {
//                                System.out.println("Error volley");
//                                callBack.onError(context);
//                            });
//
//        requestQueue.add(jsonObjectRequest);
//
//    }
//
//    public void registrarUsuario(String nombre, String correo, String cedula, String telefono, String departamento, String contraseña, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/register";
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("nombre",nombre.toString());
//            params.put("correo",correo.toString());
//            params.put("cedula",cedula.toString());
//            params.put("telefono",telefono.toString());
//            params.put("departamento",departamento.toString());
//            params.put("contrasnha",contraseña.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                response -> {
//                                System.out.println("Exito");
//                                callBack.onSuccess(true, context);
//                            },
//                error ->    {
//                                System.out.println("Error volley");
//                                callBack.onError(context);
//                            });
//
//        requestQueue.add(stringRequest);
//    }
//
//
//    public void iniciarSesion(String correo, String contraseña, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/login";
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("correo",correo.toString());
//            params.put("contrasnha",contraseña.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                response -> {
//                                System.out.println("Exito");
//                                callBack.onSuccess(true, context);
//                            },
//
//                error ->    {
//                                System.out.println("Error volley");
//                                callBack.onError(context);
//                            });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void proyectosDelUsuario(String correo, Context context, final VolleyCallBack callBack){
//
//        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
//
//        String url = "https://requeapi-1.onrender.com/getProjectsByEmail/" + correo;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String idProyecto = jsonObject.getString("idProyecto");
//                                String nombre_proyecto = jsonObject.getString("nombre_proyecto");
//                                String descripcion = jsonObject.getString("descripcion");
//                                String fechaInicio = jsonObject.getString("fechaInicio");
//                                String estado = jsonObject.getString("estado");
//                                String ced_responsable = jsonObject.getString("ced_responsable");
//                                String presupuesto = jsonObject.getString("presupuesto");
//                                String recursosNecesarios = jsonObject.getString("recursosNecesarios");
//
//                                Proyecto proyecto = new Proyecto(idProyecto, nombre_proyecto, descripcion, fechaInicio, estado, ced_responsable, presupuesto, recursosNecesarios);
//                                proyectos.add(proyecto);
//
//                                System.out.println("Proyecto: -----------------------");
//                                System.out.println(proyecto.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(proyectos, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//
//    }
//
//    public void estadoUsuario(String correo, Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/getProjectsByEmail/" + correo;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        String str = "";
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String nombre_proyecto = jsonObject.getString("nombre_proyecto");
//
//                                str += "Parte del proyecto " + nombre_proyecto + "\n";
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(str, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//
//    }
//
//    public void registrarProyecto(String nombre, String descripcion, String responsable, String fechaInicio, String presupuesto, String recursosNecesarios, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/createProject";
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("nombre_proyecto",nombre.toString());
//            params.put("descripcion",descripcion.toString());
//            params.put("fecha_inicio",fechaInicio.toString());
//            params.put("ced_responsable",responsable.toString());
//            params.put("presupuesto",presupuesto.toString());
//            params.put("recursosNecesarios",recursosNecesarios.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                response -> {
//
//                    System.out.println("Exito");
//                    JSONObject jsonObject = (JSONObject)response;
//                    try {
//                        int idProyecto = jsonObject.getInt("idProyecto");
//                        callBack.onSuccess(idProyecto+1, context);
//                    } catch (JSONException e) {}
//
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    error.printStackTrace();
//                    System.out.println(params.toString());
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void modificarUsuario(String cedula, String correo, String telefono, String departamento, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/updateUser";
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("correo",correo.toString());
//            params.put("cedula",cedula.toString());
//            params.put("telefono",telefono.toString());
//            params.put("departamento",departamento.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, params,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//
//    public void registrarTarea(String nombre, String descripcion, String recursos, String fechaInicio, String tiempoEstimado, String estado, String storypoints, String responsable, String idProyecto, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/addTask";
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("nombre",nombre.toString());
//            params.put("descripcion",descripcion.toString());
//            params.put("recursos",recursos.toString());
//            params.put("fecha_inicio",fechaInicio.toString());
//            params.put("tiempo_estimado",tiempoEstimado.toString());
//            params.put("estado",estado.toString());
//            params.put("storypoints",storypoints.toString());
//            params.put("responsable",responsable.toString());
//            params.put("id_proyecto",idProyecto.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void tareasDelProyecto(String idProyecto, Context context, final VolleyCallBack callBack){
//
//        ArrayList<Tarea> tareas = new ArrayList<Tarea>();
//
//        String url = "https://requeapi-1.onrender.com/getTask/" + idProyecto;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String idTarea = jsonObject.getString("idTarea");
//                                String nombre = jsonObject.getString("nombre_Tarea");
//                                String descripcion = jsonObject.getString("descripcion");
//                                String fecha_inicio = jsonObject.getString("fechaInicio");
//                                String estado = jsonObject.getString("estado");
//                                String ced_responsable = jsonObject.getString("responsable");
//                                String id_proyecto = jsonObject.getString("idProyecto");
//                                String storypoints = jsonObject.getString("storypoints");
//                                String recursos = jsonObject.getString("recursos");
//                                String tiempo_estimado = jsonObject.getString("tiempo_estimado");
//
//                                Tarea tarea = new Tarea(idTarea, nombre, descripcion, recursos, fecha_inicio, tiempo_estimado, estado, storypoints, ced_responsable, id_proyecto);
//                                tareas.add(tarea);
//
//                                System.out.println("Tarea: -----------------------");
//                                System.out.println(tarea.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(tareas, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//
//
//    }
//    public void registrarColaborador(String idProyecto, String cedula, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/addCollaborator/" + idProyecto + "/" + cedula;
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, null,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void eliminarColaborador(String idProyecto, String cedula, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/deleteCollaborator/" + idProyecto + "/" + cedula;
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    error.printStackTrace();
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void colaboradoresDelProyecto(String idProyecto, Context context, final VolleyCallBack callBack){
//
//        ArrayList<ListColaboradorElement> colaboradores = new ArrayList<ListColaboradorElement>();
//
//        String url = "https://requeapi-1.onrender.com/getColaboradores/" + idProyecto;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String cedula = jsonObject.getString("ced_colaborador");
//
//                                ListColaboradorElement colaborador = new ListColaboradorElement("", cedula, null);
//                                colaboradores.add(colaborador);
//
//                                System.out.println("Colaborador: -----------------------");
//                                System.out.println(colaborador.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(colaboradores, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    //Hacer una que se llame obtenerEstado(cedula) que retorne una lista de proyectos en los que participa. Mejor si retorna Colaborador en el proyecto XYZ. \n
//
//    public void modificarTarea(String id_tarea, String nombre, String descripcion, String fecha_inicio, String estado, String ced_responsable, String id_proyecto, String tiempo_estimado, String storypoints, String recursos, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/updateTask/" + id_tarea;
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("id_tarea",id_tarea.toString());
//            params.put("nombre",nombre.toString());
//            params.put("descripcion",descripcion.toString());
//            params.put("fecha_inicio",fecha_inicio.toString());
//            params.put("estado",estado.toString());
//            params.put("responsable",ced_responsable.toString());
//            params.put("id_proyecto",id_proyecto.toString());
//            params.put("tiempo_estimado",tiempo_estimado.toString());
//            params.put("storypoints",storypoints.toString());
//            params.put("recursos",recursos.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, params,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void eliminarTarea(String idTarea, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/deleteTask/" + idTarea;
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    error.printStackTrace();
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void crearMensaje(String idProyecto, String nombreColaborador, String mensaje, Context context, final VolleyCallBack callBack) throws AuthFailureError {
//
//        String url = "https://requeapi-1.onrender.com/createMessage/" + idProyecto;
//
//        JSONObject params = new JSONObject();
//        try{
//            params.put("nombreColaborador",nombreColaborador.toString());
//            params.put("mensaje",mensaje.toString());
//
//            System.out.println(params.toString());
//        }catch(JSONException e){}
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, params,
//                response -> {
//                    System.out.println("Exito");
//                    callBack.onSuccess(true, context);
//                },
//                error ->    {
//                    System.out.println("Error volley");
//                    callBack.onError(context);
//                });
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void recibirMensajes(String idProyecto, Context context, final VolleyCallBack callBack){
//
//        ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
//
//        String url = "https://requeapi-1.onrender.com/getMessages/" + idProyecto;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String colaborador = jsonObject.getString("nombreColaborador");
//                                String mensaje = jsonObject.getString("mensaje");
//
//                                Mensaje mensajeObjeto = new Mensaje(mensaje,colaborador,idProyecto);
//                                mensajes.add(mensajeObjeto);
//
//                                System.out.println("Mensaje: -----------------------");
//                                System.out.println(mensajeObjeto.toString());
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(mensajes, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    public void getAverageTimeForProjectTasks(String idProyecto, Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/average-time/" + idProyecto;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        String promedio_tiempo = "0";
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                promedio_tiempo = jsonObject.getString("promedio_tiempo");
//
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(promedio_tiempo, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    public void getAverageTimeForAllTasks (Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/average-time";
//        ArrayList<String> respuesta = new ArrayList<>();
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String nombre_proyecto = jsonObject.getString("nombreProyecto");
//                                String promedio_tiempo = jsonObject.getString("promedio_tiempo");
//
//                                respuesta.add(nombre_proyecto);
//                                respuesta.add(promedio_tiempo);
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(respuesta, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    public void getAverageResourcesOneProject(String idProyecto, Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/average-resources/" + idProyecto;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        String promedio_recursos = "0";
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                promedio_recursos = jsonObject.getString("promedio_recursos");
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(promedio_recursos, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    public void getAverageResourcesPerProject(Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/average-resources";
//        ArrayList<String> respuesta = new ArrayList<>();
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String nombre_proyecto = jsonObject.getString("nombreProyecto");
//                                String promedio_recursos = jsonObject.getString("promedio_recursos");
//
//                                respuesta.add(nombre_proyecto);
//                                respuesta.add(promedio_recursos);
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(respuesta, context);
//
//                    }
//                }, error -> System.out.println("Error volley"));
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//    public void getProjectWithHighestResources(Context context, final VolleyCallBack callBack){
//
//        String url = "https://requeapi-1.onrender.com/highest-resources";
//        ArrayList<String> respuesta = new ArrayList<>();
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>(){
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for(int i = 0; i < response.length(); i++){
//                            try{
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String total_recursos = jsonObject.getString("total_recursos");
//                                String idProyecto = jsonObject.getString("nombreProyecto");
//
//                                respuesta.add(total_recursos);
//                                respuesta.add(idProyecto);
//
//                            }catch(JSONException e){
//                                System.out.println("Error JSON");
//                                e.printStackTrace();
//                            }
//                        }
//
//                        callBack.onSuccess(respuesta, context);
//
//                    }
//                }, error -> {System.out.println("Error volley"); error.printStackTrace();});
//
//        requestQueue.add(jsonArrayRequest);
//    }
//
//}

// Se volaron la API:

package com.example.tendindo.utilidades;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Offline stub of VolleySingleton.
 * Re-implements every public method to behave like the API controller,
 * but using an in-memory data store instead of HTTP calls.
 *
 * IMPORTANT:
 * - Method names, parameters, and callback payloads mimic the original
 *   so you don't need to touch the rest of the app.
 * - Internally we DO NOT use your app models to store data. We have
 *   private DB models (DBUser/DBProject/DBTask/DBMessage) so we can keep
 *   fields like password without polluting the app classes.
 */
public class VolleySingleton {

    // ====== Callback ======
    public interface VolleyCallBack {
        void onSuccess(Object resultado, Context context);
        void onError(Context context);
    }

    // ====== Singleton plumbing (preserved) ======
    private RequestQueue requestQueue;
    private static VolleySingleton mInstance;

    private VolleySingleton(Context context) {
        // Still create a queue to avoid NPEs elsewhere, but it is unused.
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        // Seed demo data once
        MemoryDB.seedOnce();
    }

    public static synchronized VolleySingleton getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    // ====== In-memory "DB" with INTERNAL MODELS ======
    private static final class MemoryDB {
        static final List<DBUser> usuarios = Collections.synchronizedList(new ArrayList<>());
        static final List<DBProject> proyectos = Collections.synchronizedList(new ArrayList<>());
        static final List<DBTask> tareas = Collections.synchronizedList(new ArrayList<>());
        static final List<DBMessage> mensajes = Collections.synchronizedList(new ArrayList<>());
        // colaboradores: relación ced_colaborador <-> idProyecto
        static final Set<String> colaboradores = Collections.synchronizedSet(new HashSet<>());
        static final List<DBMeeting> reuniones = Collections.synchronizedList(new ArrayList<>());

        // id autoincrementales (stringificadas al exponer)
        static int nextProyectoId = 1;
        static int nextTareaId = 1;
        static int nextReunionId = 1;

        static boolean seeded = false;
        static synchronized void seedOnce() {
            if (seeded) return;

            // --- Usuarios (con password interno en DBUser) ---
            DBUser u1 = new DBUser("Demo User", "demo@tec.cr", "101010101", "8888-8888", "IT", "1234");
            DBUser u2 = new DBUser("Ana Pérez", "ana@empresa.com", "202020202", "7000-1111", "Finanzas", "passana");
            DBUser u3 = new DBUser("Carlos Ruiz", "carlos@empresa.com", "303030303", "7000-2222", "Ventas", "passcarlos");

            usuarios.add(u1);
            usuarios.add(u2);
            usuarios.add(u3);

            // --- Proyectos (IDs autoincrementales locales) ---
            String p1Id = genProyectoId();
            String p2Id = genProyectoId();

            DBProject p1 = new DBProject(
                    p1Id,
                    "App Móvil Inventarios",
                    "Aplicación para gestión de inventarios en bodega",
                    "2025-09-01",
                    "0",               // estado
                    u1.cedula,         // responsable
                    "15000000",
                    "2 Android devs; 1 QA"
            );

            DBProject p2 = new DBProject(
                    p2Id,
                    "Portal de Reportes",
                    "Portal web para reportes ejecutivos mensuales",
                    "2025-08-15",
                    "1",
                    u2.cedula,
                    "10000000",
                    "Frontend + Backend + BI"
            );

            proyectos.add(p1);
            proyectos.add(p2);

            // --- Colaboradores (join cedula::idProyecto) ---
            colaboradores.add(u1.cedula + "::" + p1Id); // demo en proyecto 1
            colaboradores.add(u2.cedula + "::" + p1Id); // ana también en proyecto 1
            colaboradores.add(u2.cedula + "::" + p2Id); // ana en proyecto 2
            colaboradores.add(u3.cedula + "::" + p2Id); // carlos en proyecto 2

            String t1Id = genTareaId();
            DBTask t1 = new DBTask(t1Id, "Login y sesiones", "Implementar login y refresh tokens",
                    "3", "2025-09-02", "12", "En progreso", "5", u1.cedula, p1Id);

            String t2Id = genTareaId();
            DBTask t2 = new DBTask(t2Id, "Sincronización offline", "Colas y reintentos",
                    "4", "2025-09-05", "20", "Por hacer", "8", u2.cedula, p1Id);

            String t5Id = genTareaId();
            DBTask t5 = new DBTask(t5Id, "Diseño UI", "Prototipo en Figma y revisión con el cliente",
                    "2", "2025-09-01", "10", "Finalizada", "3", u1.cedula, p1Id);

            String t6Id = genTareaId();
            DBTask t6 = new DBTask(t6Id, "Integración API", "Conectar backend con módulos de inventario",
                    "5", "2025-09-06", "16", "En progreso", "6", u2.cedula, p1Id);

            String t7Id = genTareaId();
            DBTask t7 = new DBTask(t7Id, "Notificaciones push", "Implementar Firebase Cloud Messaging",
                    "3", "2025-09-08", "12", "Por hacer", "5", u3.cedula, p1Id);

            String t8Id = genTareaId();
            DBTask t8 = new DBTask(t8Id, "Testing unitario", "Casos de prueba para controladores",
                    "4", "2025-09-10", "18", "En progreso", "7", u1.cedula, p1Id);

            String t9Id = genTareaId();
            DBTask t9 = new DBTask(t9Id, "Optimización UI", "Reducir tiempos de carga y mejorar UX",
                    "2", "2025-09-12", "8", "Finalizada", "4", u2.cedula, p1Id);

            // --- Tareas para p2 ---
            String t3Id = genTareaId();
            DBTask t3 = new DBTask(t3Id, "Dashboard KPI", "Gráficos y filtros",
                    "5", "2025-08-16", "15", "Finalizada", "8", u2.cedula, p2Id);

            String t4Id = genTareaId();
            DBTask t4 = new DBTask(t4Id, "Exportar a PDF", "Plantillas y branding",
                    "2", "2025-08-20", "10", "Finalizada", "3", u3.cedula, p2Id);

            // --- Agregar todas ---
            tareas.add(t1);
            tareas.add(t2);
            tareas.add(t3);
            tareas.add(t4);
            tareas.add(t5);
            tareas.add(t6);
            tareas.add(t7);
            tareas.add(t8);
            tareas.add(t9);

            // --- Mensajes por proyecto ---
            mensajes.add(new DBMessage("Hola equipo, revisen los requisitos en Drive.", "Ana Pérez", p1Id));
            mensajes.add(new DBMessage("Subí el primer prototipo del login.", "Demo User", p1Id));
            mensajes.add(new DBMessage("KPIs validados con dirección.", "Carlos Ruiz", p2Id));
            mensajes.add(new DBMessage("Se aprobó el export a PDF ✔", "Ana Pérez", p2Id));

            // --- (Opcional) Reuniones demo ---
            reuniones.add(new DBMeeting(genReunionId(), "Kickoff inventarios", "2025-09-03 10:00", "Zoom", p1Id));
            reuniones.add(new DBMeeting(genReunionId(), "Revisión KPIs", "2025-08-18 14:30", "Teams", p2Id));

            seeded = true;
        }

        static synchronized String genProyectoId() {
            return String.valueOf(nextProyectoId++);
        }
        static synchronized String genTareaId() {
            return String.valueOf(nextTareaId++);
        }

        static synchronized String genReunionId() {
            return String.valueOf(nextReunionId++);
        }

        static DBUser findUserByCorreo(String correo) {
            synchronized (usuarios) {
                for (DBUser u : usuarios) {
                    if (u.correo != null && u.correo.equals(correo)) return u;
                }
            }
            return null;
        }
        static DBUser findUserByCedula(String cedula) {
            synchronized (usuarios) {
                for (DBUser u : usuarios) {
                    if (u.cedula != null && u.cedula.equals(cedula)) return u;
                }
            }
            return null;
        }
        static DBProject findProyectoById(String idProyecto) {
            synchronized (proyectos) {
                for (DBProject p : proyectos) {
                    if (p.idProyecto != null && p.idProyecto.equals(idProyecto)) return p;
                }
            }
            return null;
        }
        static List<DBTask> findTareasByProyecto(String idProyecto) {
            ArrayList<DBTask> res = new ArrayList<>();
            synchronized (tareas) {
                for (DBTask t : tareas) {
                    if (t.idProyecto != null && t.idProyecto.equals(idProyecto)) {
                        res.add(t);
                    }
                }
            }
            return res;
        }
        static List<DBProject> proyectosDeCorreo(String correo) {
            ArrayList<DBProject> res = new ArrayList<>();
            DBUser u = findUserByCorreo(correo);
            if (u == null) return res;
            String ced = u.cedula;
            synchronized (proyectos) {
                for (DBProject p : proyectos) {
                    if (colaboradores.contains(ced + "::" + p.idProyecto)) {
                        res.add(p);
                    }
                }
            }
            return res;
        }
    }

    // INTERNAL DB MODELS (not exposed to the app)
    private static final class DBUser {
        String nombre, correo, cedula, telefono, departamento;
        String password;
        DBUser(String nombre, String correo, String cedula, String telefono, String departamento, String password) {
            this.nombre = nombre;
            this.correo = correo;
            this.cedula = cedula;
            this.telefono = telefono;
            this.departamento = departamento;
            this.password = password;
        }
    }
    private static final class DBProject {
        String idProyecto, nombre_proyecto, descripcion, fechaInicio, estado, ced_responsable, presupuesto, recursosNecesarios;
        DBProject(String idProyecto, String nombre_proyecto, String descripcion, String fechaInicio, String estado, String ced_responsable, String presupuesto, String recursosNecesarios) {
            this.idProyecto = idProyecto;
            this.nombre_proyecto = nombre_proyecto;
            this.descripcion = descripcion;
            this.fechaInicio = fechaInicio;
            this.estado = estado;
            this.ced_responsable = ced_responsable;
            this.presupuesto = presupuesto;
            this.recursosNecesarios = recursosNecesarios;
        }
    }
    private static final class DBTask {
        String idTarea, nombre, descripcion, recursos, fechaInicio, tiempo_estimado, estado, storypoints, responsable, idProyecto;
        DBTask(String idTarea, String nombre, String descripcion, String recursos, String fechaInicio, String tiempo_estimado, String estado, String storypoints, String responsable, String idProyecto) {
            this.idTarea = idTarea;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.recursos = recursos;
            this.fechaInicio = fechaInicio;
            this.tiempo_estimado = tiempo_estimado;
            this.estado = estado;
            this.storypoints = storypoints;
            this.responsable = responsable;
            this.idProyecto = idProyecto;
        }
    }
    private static final class DBMessage {
        String mensaje, nombreColaborador, idProyecto;
        DBMessage(String mensaje, String nombreColaborador, String idProyecto) {
            this.mensaje = mensaje;
            this.nombreColaborador = nombreColaborador;
            this.idProyecto = idProyecto;
        }
    }
    private static final class DBMeeting {
        String id, tema, fecha, medio, idProyecto;
        DBMeeting(String id, String tema, String fecha, String medio, String idProyecto) {
            this.id = id;
            this.tema = tema;
            this.fecha = fecha;
            this.medio = medio;
            this.idProyecto = idProyecto;
        }
    }

    // Utilidad para simular asincronía (igual que una llamada HTTP)
    private void asyncDeliver(Context ctx, Runnable r) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            r.run();
        } else {
            new Handler(Looper.getMainLooper()).post(r);
        }
    }

    // =============================================================================================
    // ===============================  MÉTODOS PORTADOS  ==========================================
    // =============================================================================================

    // --- Usuarios ---

    public ArrayList<Usuario> obtenerTodosUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        synchronized (MemoryDB.usuarios) {
            for (DBUser d : MemoryDB.usuarios) {
                lista.add(toAppUsuario(d));
            }
        }
        return lista;
    }

    public void obtenerDatosUsuario(String correo, Context context, final VolleyCallBack callBack) {
        DBUser d = MemoryDB.findUserByCorreo(correo);
        if (d != null) {
            Usuario u = toAppUsuario(d);
            asyncDeliver(context, () -> callBack.onSuccess(u, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    public void obtenerDatosUsuarioPorCedula(String cedula, Context context, final VolleyCallBack callBack) {
        DBUser d = MemoryDB.findUserByCedula(cedula);
        if (d != null) {
            Usuario u = toAppUsuario(d);
            asyncDeliver(context, () -> callBack.onSuccess(u, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    public void registrarUsuario(String nombre, String correo, String cedula, String telefono, String departamento, String contraseña, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        DBUser existsByMail = MemoryDB.findUserByCorreo(correo);
        DBUser existsById = MemoryDB.findUserByCedula(cedula);
        if (existsByMail != null || existsById != null) {
            asyncDeliver(context, () -> callBack.onError(context)); // simulación de 400
            return;
        }
        MemoryDB.usuarios.add(new DBUser(nombre, correo, cedula, telefono, departamento, contraseña));
        asyncDeliver(context, () -> callBack.onSuccess(true, context));
    }

    public void iniciarSesion(String correo, String contraseña, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        DBUser d = MemoryDB.findUserByCorreo(correo);
        if (d != null && d.password != null && d.password.equals(contraseña)) {
            asyncDeliver(context, () -> callBack.onSuccess(true, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    public void modificarUsuario(String cedula, String correo, String telefono, String departamento, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        DBUser d = MemoryDB.findUserByCedula(cedula);
        if (d == null) {
            asyncDeliver(context, () -> callBack.onError(context));
            return;
        }
        d.correo = correo;
        d.telefono = telefono;
        d.departamento = departamento;
        asyncDeliver(context, () -> callBack.onSuccess(true, context));
    }

    public void estadoUsuario(String correo, Context context, final VolleyCallBack callBack) {
        List<DBProject> ps = MemoryDB.proyectosDeCorreo(correo);
        StringBuilder sb = new StringBuilder();
        for (DBProject p : ps) {
            sb.append("Parte del proyecto ").append(p.nombre_proyecto).append("\n");
        }
        asyncDeliver(context, () -> callBack.onSuccess(sb.toString(), context));
    }

    // --- Proyectos ---

    public ArrayList<Proyecto> obtenerTodosProyectos(Context context, final VolleyCallBack callBack) {
        ArrayList<Proyecto> lista = new ArrayList<>();
        synchronized (MemoryDB.proyectos) {
            for (DBProject d : MemoryDB.proyectos) lista.add(toAppProyecto(d));
        }
        ArrayList<Proyecto> result = new ArrayList<>(lista);
        asyncDeliver(context, () -> callBack.onSuccess(result, context));
        return result;
    }

    public void obtenerProyectoPorNombre(String nombre, Context context, final VolleyCallBack callBack) {
        for (DBProject d : MemoryDB.proyectos) {
            if (d.nombre_proyecto != null && d.nombre_proyecto.equals(nombre)) {
                Proyecto found = toAppProyecto(d);
                asyncDeliver(context, () -> callBack.onSuccess(found, context));
                return;
            }
        }
        asyncDeliver(context, () -> callBack.onError(context));
    }

    public void proyectosDelUsuario(String correo, Context context, final VolleyCallBack callBack) {
        ArrayList<Proyecto> lista = new ArrayList<>();
        for (DBProject d : MemoryDB.proyectosDeCorreo(correo)) {
            lista.add(toAppProyecto(d));
        }
        asyncDeliver(context, () -> callBack.onSuccess(lista, context));
    }

    public void registrarProyecto(String nombre, String descripcion, String responsable, String fechaInicio, String presupuesto, String recursosNecesarios, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        String id = MemoryDB.genProyectoId();
        DBProject p = new DBProject(
                id,
                nombre,
                descripcion,
                fechaInicio,
                "0",                // estado por defecto
                responsable,        // ced_responsable
                presupuesto,
                recursosNecesarios
        );
        MemoryDB.proyectos.add(p);
        int idEnteroMasUno = Integer.parseInt(id) + 1;
        asyncDeliver(context, () -> callBack.onSuccess(idEnteroMasUno, context));
    }

    // --- Colaboradores ---

    public void registrarColaborador(String idProyecto, String cedula, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        String key = cedula + "::" + idProyecto;
        MemoryDB.colaboradores.add(key);
        asyncDeliver(context, () -> callBack.onSuccess(true, context));
    }

    public void eliminarColaborador(String idProyecto, String cedula, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        String key = cedula + "::" + idProyecto;
        boolean removed = MemoryDB.colaboradores.remove(key);
        if (removed) {
            asyncDeliver(context, () -> callBack.onSuccess(true, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    public void colaboradoresDelProyecto(String idProyecto, Context context, final VolleyCallBack callBack) {
        ArrayList<ListColaboradorElement> lista = new ArrayList<>();
        synchronized (MemoryDB.colaboradores) {
            for (String key : MemoryDB.colaboradores) {
                String[] parts = key.split("::");
                if (parts.length == 2 && parts[1].equals(idProyecto)) {
                    String ced = parts[0];
                    lista.add(new ListColaboradorElement("", ced, null));
                }
            }
        }
        asyncDeliver(context, () -> callBack.onSuccess(lista, context));
    }

    // --- Tareas ---

    public void registrarTarea(String nombre, String descripcion, String recursos, String fechaInicio, String tiempoEstimado, String estado, String storypoints, String responsable, String idProyecto, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        String id = MemoryDB.genTareaId();
        DBTask t = new DBTask(
                id,
                nombre,
                descripcion,
                recursos,
                fechaInicio,
                tiempoEstimado,
                estado,
                storypoints,
                responsable,
                idProyecto
        );
        MemoryDB.tareas.add(t);
        asyncDeliver(context, () -> callBack.onSuccess(true, context));
    }

    public void tareasDelProyecto(String idProyecto, Context context, final VolleyCallBack callBack) {
        ArrayList<Tarea> lista = new ArrayList<>();
        for (DBTask d : MemoryDB.findTareasByProyecto(idProyecto)) {
            lista.add(toAppTarea(d));
        }
        asyncDeliver(context, () -> callBack.onSuccess(lista, context));
    }

    public void modificarTarea(String id_tarea, String nombre, String descripcion, String recursos, String fecha_inicio, String tiempo_estimado, String estado, String storypoints, String responsable, String id_proyecto, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        boolean ok = false;
        synchronized (MemoryDB.tareas) {
            for (DBTask t : MemoryDB.tareas) {
                if (t.idTarea != null && t.idTarea.equals(id_tarea)) {
                    t.nombre = nombre;
                    t.descripcion = descripcion;
                    t.recursos = recursos;
                    t.fechaInicio = fecha_inicio;
                    t.tiempo_estimado = tiempo_estimado;
                    t.estado = estado;
                    t.storypoints = storypoints;
                    t.responsable = responsable;
                    t.idProyecto = id_proyecto;
                    ok = true;
                    break;
                }
            }
        }
        if (ok) {
            asyncDeliver(context, () -> callBack.onSuccess(true, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    public void eliminarTarea(String idTarea, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        boolean removed;
        synchronized (MemoryDB.tareas) {
            removed = MemoryDB.tareas.removeIf(t -> t.idTarea != null && t.idTarea.equals(idTarea));
        }
        if (removed) {
            asyncDeliver(context, () -> callBack.onSuccess(true, context));
        } else {
            asyncDeliver(context, () -> callBack.onError(context));
        }
    }

    // --- Mensajes ---

    public void crearMensaje(String idProyecto, String nombreColaborador, String mensaje, Context context, final VolleyCallBack callBack) throws AuthFailureError {
        MemoryDB.mensajes.add(new DBMessage(mensaje, nombreColaborador, idProyecto));
        asyncDeliver(context, () -> callBack.onSuccess(true, context));
    }

    public void recibirMensajes(String idProyecto, Context context, final VolleyCallBack callBack) {
        ArrayList<Mensaje> lista = new ArrayList<>();
        synchronized (MemoryDB.mensajes) {
            for (DBMessage m : MemoryDB.mensajes) {
                if (m.idProyecto != null && m.idProyecto.equals(idProyecto)) {
                    lista.add(new Mensaje(m.mensaje, m.nombreColaborador, m.idProyecto));
                }
            }
        }
        asyncDeliver(context, () -> callBack.onSuccess(lista, context));
    }

    // --- Agregados / Promedios ---

    public void getAverageTimeForProjectTasks(String idProyecto, Context context, final VolleyCallBack callBack) {
        List<DBTask> ts = MemoryDB.findTareasByProyecto(idProyecto);
        double avg = 0.0;
        if (!ts.isEmpty()) {
            double sum = 0.0;
            int cnt = 0;
            for (DBTask t : ts) {
                try {
                    sum += Double.parseDouble(t.tiempo_estimado);
                    cnt++;
                } catch (Exception ignored) {}
            }
            if (cnt > 0) avg = sum / cnt;
        }
        String promedio = String.valueOf(avg);
        asyncDeliver(context, () -> callBack.onSuccess(promedio, context));
    }

    public void getAverageTimeForAllTasks(Context context, final VolleyCallBack callBack) {
        ArrayList<String> respuesta = new ArrayList<>();
        Map<String, double[]> acc = new HashMap<>(); // idProyecto -> [sum, cnt]
        synchronized (MemoryDB.tareas) {
            for (DBTask t : MemoryDB.tareas) {
                double[] sc = acc.computeIfAbsent(t.idProyecto, k -> new double[]{0.0, 0.0});
                try {
                    sc[0] += Double.parseDouble(t.tiempo_estimado);
                    sc[1] += 1.0;
                } catch (Exception ignored) {}
            }
        }
        for (Map.Entry<String, double[]> e : acc.entrySet()) {
            DBProject p = MemoryDB.findProyectoById(e.getKey());
            String nombre = (p != null ? p.nombre_proyecto : e.getKey());
            double[] sc = e.getValue();
            double avg = sc[1] > 0 ? sc[0] / sc[1] : 0.0;
            respuesta.add(nombre + " - " + avg);
        }
        asyncDeliver(context, () -> callBack.onSuccess(respuesta, context));
    }

    public void getAverageResourcesOneProject(String idProyecto, Context context, final VolleyCallBack callBack) {
        List<DBTask> ts = MemoryDB.findTareasByProyecto(idProyecto);
        double avg = 0.0;
        if (!ts.isEmpty()) {
            double sum = 0.0;
            int cnt = 0;
            for (DBTask t : ts) {
                try {
                    sum += Double.parseDouble(t.recursos);
                    cnt++;
                } catch (Exception ignored) {}
            }
            if (cnt > 0) avg = sum / cnt;
        }
        String finalAvg = String.valueOf(avg);
        asyncDeliver(context, () -> callBack.onSuccess(finalAvg, context));
    }

    public void getAverageResourcesPerProject(Context context, final VolleyCallBack callBack) {
        ArrayList<String> respuesta = new ArrayList<>();
        Map<String, double[]> acc = new HashMap<>(); // idProyecto -> [sum, cnt]
        synchronized (MemoryDB.tareas) {
            for (DBTask t : MemoryDB.tareas) {
                double[] sc = acc.computeIfAbsent(t.idProyecto, k -> new double[]{0.0, 0.0});
                try {
                    sc[0] += Double.parseDouble(t.recursos);
                    sc[1] += 1.0;
                } catch (Exception ignored) {}
            }
        }
        // Este método solo devuelve los promedios como Strings (sin nombre)
        for (Map.Entry<String, double[]> e : acc.entrySet()) {
            double[] sc = e.getValue();
            double avg = sc[1] > 0 ? sc[0] / sc[1] : 0.0;
            respuesta.add(String.valueOf(avg));
        }
        asyncDeliver(context, () -> callBack.onSuccess(respuesta, context));
    }

    public void getProjectWithHighestResources(Context context, final VolleyCallBack callBack) {
        // SUM recursos por proyecto y escoge el máximo. Devuelve [total_recursos, nombreProyecto]
        String bestProjectName = null;
        double bestTotal = -1;
        Map<String, Double> sum = new HashMap<>();
        synchronized (MemoryDB.tareas) {
            for (DBTask t : MemoryDB.tareas) {
                try {
                    double v = Double.parseDouble(t.recursos);
                    sum.put(t.idProyecto, sum.getOrDefault(t.idProyecto, 0.0) + v);
                } catch (Exception ignored) {}
            }
        }
        for (Map.Entry<String, Double> e : sum.entrySet()) {
            if (e.getValue() > bestTotal) {
                bestTotal = e.getValue();
                DBProject p = MemoryDB.findProyectoById(e.getKey());
                bestProjectName = (p != null ? p.nombre_proyecto : e.getKey());
            }
        }
        ArrayList<String> respuesta = new ArrayList<>();
        respuesta.add(String.valueOf(bestTotal < 0 ? 0.0 : bestTotal));
        respuesta.add(bestProjectName == null ? "" : bestProjectName);
        asyncDeliver(context, () -> callBack.onSuccess(respuesta, context));
    }

    // ====== Converters from DB models to APP models (returned via callbacks) ======
    private Usuario toAppUsuario(DBUser d) {
        return new Usuario(d.nombre, d.correo, d.cedula, d.telefono, d.departamento, d.password);
    }
    private Proyecto toAppProyecto(DBProject d) {
        return new Proyecto(d.idProyecto, d.nombre_proyecto, d.descripcion, d.fechaInicio, d.estado, d.ced_responsable, d.presupuesto, d.recursosNecesarios);
    }
    private Tarea toAppTarea(DBTask d) {
        return new Tarea(d.idTarea, d.nombre, d.descripcion, d.recursos, d.fechaInicio, d.tiempo_estimado, d.estado, d.storypoints, d.responsable, d.idProyecto);
    }
}
