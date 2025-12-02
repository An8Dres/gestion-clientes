# 📖 Guía Rápida de Uso: Sistema de Gestión de Clientes

Este manual de usuario explica cómo utilizar las funciones básicas de registro, atención y gestión de colas en el Sistema de Gestión de Clientes.

---

## 1. Interfaz Principal

La aplicación se divide en tres áreas principales:

1.  **Tabla de Datos:** Muestra la lista de clientes en la cola, el historial o el registro de acciones.
2.  **Gestión (Botones):** Contiene las funciones para cambiar de vista y procesar clientes.
3.  **Registro (Formulario):** Permite ingresar nuevos clientes a la cola.



---

## 2. 📝 Registrar un Cliente

Sigue estos pasos para ingresar un nuevo cliente a la cola de espera:

1.  **Nombre:** Escribe el nombre del cliente en el campo **"Nombre del Cliente"**.
2.  **Tipo de Solicitud:** Haz clic en el selector **"Tipo de Solicitud"** para elegir entre **SOPORTE**, **MANTENIMIENTO** o **RECLAMO**.
3.  **Prioridad:** Marca la casilla **"URGENTE"** si el cliente debe ser atendido primero. Si no la marcas, se asigna prioridad **"Normal"**.
4.  **Registrar:** Presiona el botón **"REGISTRAR"** para añadirlo a la cola.

**Ejemplo:** Registrando a "James" con prioridad Urgente y solicitud RECLAMO.


---

## 3. ▶️ Atender Clientes

La atención se realiza en la vista **"COLA DE ESPERA"**.

### Atender por Prioridad

El sistema procesa la cola dando preferencia a:
1.  **Prioridad Urgente** (primero que se registró entre los urgentes).
2.  **Prioridad Normal** (en orden de llegada).

1.  Asegúrate de que la vista superior esté en **"COLA DE ESPERA"**.
2.  Presiona el botón **"ATENDER CLIENTE"**.
    * El primer cliente en la cola (Milagros) es removido y se mueve a la lista de atendidos.



### Clientes Atendidos
Después de atender, la vista se actualiza y el cliente se encuentra ahora en la **"LISTA DE ATENDIDOS"** (Historial).



---

## 4. 👁️ Consultar el Cliente Actual

Puedes ver quién es el próximo cliente a ser atendido sin sacarlo de la cola.

1.  Asegúrate de que la vista superior esté en **"COLA DE ESPERA"**.
2.  Presiona el botón **"ACTUAL ATENDIÉNDOSE"**.

El sistema mostrará solo al cliente que sigue en la cola de espera.



---

## 5. 🔍 Buscar Clientes en el Historial

Para buscar clientes que ya fueron atendidos:

1.  Asegúrate de que la vista superior esté en **"LISTA DE ATENDIDOS"**.
2.  Utiliza la barra de **"Buscar..."** en la parte superior.
    * Puedes buscar por el **ID** del cliente (ej. `1`) o por el **Tipo de Solicitud** (ej. `SOPORTE`).



---

## ↩️ Deshacer y 🗑️ Eliminar

* **Deshacer:** Haz clic en el botón **"Deshacer"** (o presiona `Ctrl + Z`). Esta función revierte la última acción realizada, ya sea **Registrar**, **Atender** o **Eliminar**.
* **Eliminar:** Selecciona una fila en la tabla de **"COLA DE ESPERA"** y haz clic en el icono **"Eliminar"**.
