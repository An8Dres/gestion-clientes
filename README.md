# 🏦 Sistema de Gestión de Clientes en Cola

Sistema de escritorio desarrollado en Java para gestionar la cola de espera de clientes, su atención y un registro histórico de todas las acciones realizadas.

## ✍️ Autor
Andres Petro

## 🚀 Funcionalidades

Este sistema permite gestionar eficientemente tres estados principales del cliente:

* **Cola de Espera:** Clientes que están esperando ser atendidos, con prioridades configurables ("Normal" o "Urgente").
* **Lista de Atendidos (Historial):** Registro de todos los clientes que ya han sido procesados.
* **Registro de Acciones:** Historial detallado de operaciones del sistema (registrar, atender, eliminar, restaurar).

### Acciones Principales

| Acción | Descripción |
| :--- | :--- |
| **Registrar** | Agrega un nuevo cliente a la cola con su nombre, tipo de solicitud (Soporte, Mantenimiento, Reclamo) y prioridad. |
| **Atender** | Mueve al primer cliente de la cola de espera al historial de atendidos. |
| **Eliminar** | Remueve un cliente de la cola de espera. |
| **Deshacer** | Revierte la última acción (registrar, atender o eliminar), permitiendo restaurar el estado anterior de la cola. |
| **Buscar** | Permite buscar clientes en el historial por ID o por tipo de solicitud. |
| **Paginación** | Controla la navegación entre páginas en la visualización de colas largas. |

## 📐 Arquitectura del Proyecto

El proyecto sigue el patrón de diseño **Modelo-Vista-Controlador (MVC)**, con una separación clara de responsabilidades:

### 📦 Paquete `modelos` (El Cerebro)
Contiene la lógica de negocio y las estructuras de datos.

| Clase | Responsabilidad Principal |
| :--- | :--- |
| `Cliente` | Define la estructura de datos de un cliente (ID, nombre, prioridad, tipo de solicitud). |
| `GestionClientes` | Administra la **Cola de Espera** utilizando una estructura `ArrayDeque`. Implementa la lógica para agregar, eliminar y consultar al cliente actual. |
| `HistorialAtencion` | Administra la **Lista de Atendidos** utilizando una `LinkedList`. Permite buscar clientes por ID o tipo de solicitud. |
| `RegistroAcciones` | Administra la pila de acciones (`Stack<Accion>`) para implementar la funcionalidad de **Deshacer** (Undo/Redo). |
| `Accion` | Representa un registro de una operación realizada (`registrar`, `atender`, `eliminar`, `restaurar`). |
| `MisColores` | Clase estática con constantes para la paleta de colores de la interfaz gráfica. |

### 📦 Paquete `vistas`
Contiene los elementos visuales de la interfaz de usuario (Java Swing).

| Clase | Descripción |
| :--- | :--- |
| `Vista` | Contiene y organiza todos los componentes (`JPanel`, `JLabel`, `JButton`, etc.) de la interfaz principal. |
| `VentanaInfo` | Ventana secundaria que muestra estadísticas del sistema (total en espera, total atendidos, promedio). |

### 📦 Paquete `controladores` (El Coordinador)
Gestiona la interacción del usuario y la comunicación entre la Vista y el Modelo.

| Clase | Responsabilidad Principal |
| :--- | :--- |
| `Controlador` | **Controlador Principal.** Instancia todos los modelos y sub-controladores. Contiene la lógica central de `registrar()`, `atender()`, `deshacer()`, y `actualizarTabla()`. |
| `ControladorBotones` | Maneja los eventos de clic, foco y teclado (`MouseListener`, `FocusListener`, `KeyListener`) para todos los botones y campos de entrada. |
| `ControladorTabla` | Controla la visualización de datos en la tabla principal, incluyendo la selección de filas y la lógica de paginación. |
| `ControladorCombo` | Implementa la lógica de un componente ComboBox personalizado para la selección de tipos de solicitud y la visualización de colas. |

## 🛠️ Instalación y Configuración

El proyecto está desarrollado en Java y no requiere dependencias externas complejas (usa solo las librerías estándar de **Java Swing** y las colecciones de Java).

### Requisitos

* **Java Development Kit (JDK) 8 o superior.**
* Un IDE (NetBeans, IntelliJ IDEA, Eclipse) o el compilador Java (`javac`).

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/An8Dres/gestion-clientes](https://github.com/An8Dres/gestion-clientes)
    cd gestion-clientes
    ```

2.  **Compilar y Ejecutar:**
    El punto de entrada es la clase `Main.java` dentro del paquete `Main`.

    * **Desde IDE:** Simplemente importa el proyecto y ejecuta la clase `Main`.
    * **Desde Terminal:**
        ```bash
        # Compilar
        javac -d classes Main/Main.java controladores/*.java modelos/*.java vistas/*.java 
        # Ejecutar
        java -cp classes Main.Main
        ```

## 🚀 Uso

Al iniciar la aplicación, se cargan algunos clientes de ejemplo.

1.  **Registro:** Escribe el nombre del cliente en el campo superior, selecciona el tipo de solicitud y marca "Urgente" si es necesario. Presiona **"Registrar"**.
2.  **Atención:** Asegúrate de estar en la vista **"COLA DE ESPERA"** y presiona **"Atender"** para procesar al primer cliente.
3.  **Historial:** Usa el selector de vista para cambiar a **"LISTA DE ATENDIDOS"** para ver los clientes que ya fueron atendidos.
4.  **Deshacer:** Presiona **`Ctrl + Z`** o el botón **"Deshacer"** para revertir la última acción de registro, atención o eliminación.
