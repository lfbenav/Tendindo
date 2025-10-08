# Tendindo – Aplicación de Gestión de Proyectos

> **Versión adaptada para ejecución local.**  
> Esta versión de la aplicación utiliza un backend simulado **en memoria**, ya que la base de datos y la API original dejaron de estar disponibles.  
> Las peticiones a la API fueron reemplazadas por una implementación interna (`VolleySingleton`) que replica los mismos endpoints y respuestas, pero mantiene los datos dentro de la aplicación.

---

## Descripción general

La aplicación permite gestionar proyectos colaborativos, incluyendo usuarios, tareas, reuniones, colaboradores y foros de comunicación.  
Está diseñada para su uso en Android y simula todas las operaciones CRUD de manera local.

### Funcionalidades principales
- Autenticación de usuarios (login y registro)
- Creación, visualización y edición de proyectos
- Gestión de tareas por estado
- Administración de colaboradores por proyecto
- Foro de consultas tipo chat por proyecto
- Visualización de informes y métricas generales

---

## Flujo principal y pantallas

### **Autenticación**
**Funciones principales:**
- Iniciar sesión con correo y contraseña.
- Registrar nuevos usuarios.

IMAGEN

---

### **Página principal / Lista de proyectos**
**Funciones principales:**
- Mostrar los proyectos asociados al usuario.
- Acceder a la información detallada de cada proyecto.
- Crear un nuevo proyecto.

IMAGEN

---

### **Información del proyecto**
**Funciones principales:**
- Ver los datos generales del proyecto (nombre, descripción, responsable, presupuesto, recursos, estado).
- Navegar hacia la lista de tareas, colaboradores, reuniones o foro del proyecto.

IMAGEN

---

### **Tareas del proyecto**
**Funciones principales:**
- Listar tareas según su estado: *Por hacer*, *En progreso*, *Finalizada*.
- Crear nuevas tareas o modificar existentes.
- Editar detalles como tiempo estimado, recursos, storypoints o responsable.

IMAGEN

---

### **Colaboradores del proyecto**
**Funciones principales:**
- Ver los colaboradores asociados al proyecto.
- Agregar o eliminar colaboradores según su cédula o correo.

IMAGEN

---

### **Foro de consultas**
**Funciones principales:**
- Publicar y leer mensajes asociados al proyecto actual.
- Permite comunicación simple entre miembros del equipo.

IMAGEN

---

## Notas técnicas
- **Backend simulado:** toda la lógica de la API fue reemplazada por una implementación *in-memory* que conserva los contratos originales.
- **Persistencia temporal:** los datos se mantienen mientras la aplicación esté abierta; se pierden al cerrarla.
- **Estructura modular:** cada pantalla corresponde a una funcionalidad principal (login, tareas, proyectos, etc.).

---

Desarrollado como parte del curso de Ingeniería en Computación (TEC).  
Adaptación local por motivos de compatibilidad y disponibilidad de backend.