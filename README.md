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

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/93603d52-f381-468d-926e-69ed15c97253" />


---

### **Página principal / Lista de proyectos**
**Funciones principales:**
- Mostrar los proyectos asociados al usuario.
- Acceder a la información detallada de cada proyecto.
- Crear un nuevo proyecto.

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/eeaf2e3f-2a42-460c-845a-4fd00fb7de08" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/e81ef991-eb76-4f81-9c13-21d74ca95721" />


---

### **Información del proyecto**
**Funciones principales:**
- Ver los datos generales del proyecto (nombre, descripción, responsable, presupuesto, recursos, estado).
- Navegar hacia la lista de tareas, colaboradores, reuniones o foro del proyecto.

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/e7a2a798-6bb1-46d6-b500-42288cdf0f2b" />


---

### **Tareas del proyecto**
**Funciones principales:**
- Listar tareas según su estado: *Por hacer*, *En progreso*, *Finalizada*.
- Crear nuevas tareas o modificar existentes.
- Editar detalles como tiempo estimado, recursos, storypoints o responsable.

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/8fc51e18-a38d-4337-b231-68d4805fd1a4" />


---

### **Colaboradores del proyecto**
**Funciones principales:**
- Ver los colaboradores asociados al proyecto.
- Agregar o eliminar colaboradores según su cédula o correo.

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/a30c4205-4b68-40a4-94bb-9527a837bfc6" />


---

### **Foro de consultas**
**Funciones principales:**
- Publicar y leer mensajes asociados al proyecto actual.
- Permite comunicación simple entre miembros del equipo.

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/140a8ce9-6983-4f6d-81b4-067071e53334" />


---

## Notas técnicas
- **Backend simulado:** toda la lógica de la API fue reemplazada por una implementación *in-memory* que conserva los contratos originales.
- **Persistencia temporal:** los datos se mantienen mientras la aplicación esté abierta; se pierden al cerrarla.
- **Estructura modular:** cada pantalla corresponde a una funcionalidad principal (login, tareas, proyectos, etc.).

---

Desarrollado como parte del curso de Ingeniería en Computación (TEC).  
Adaptación local por motivos de compatibilidad y disponibilidad de backend.
