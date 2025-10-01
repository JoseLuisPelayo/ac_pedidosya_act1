# Acceso a Datos – Modelado, Serialización y CSV

Práctica: **Gestión de clientes y pedidos**  
Alcance: **modelar entidades, serializar/deserializar a binario** y **exportar/importar en CSV**.

---

## Objetivos de la tarea

1. **Modelado y serialización de objetos**
    - Crear las clases `Cliente (id, nombre, email)` y `Pedido (id, clienteId, producto, cantidad)`.
    - Implementar `java.io.Serializable` en ambas clases.
    - Crear **al menos 3 clientes** y **5 pedidos** asociados.
    - Guardar la lista de objetos en un archivo **`datos.obj`**.
    - Recuperar la lista de objetos de un archivo **`datos.obj`**.

2. **Exportación e importación CSV**
    - Exportar clientes a **`clientes.csv`** y pedidos a **`pedidos.csv`**.
    - Importar desde ambos CSV para reconstruir las listas en memoria.

---

## Estructura de archivos
```angular2html
src/main/resources/  
├── datos.dat  
├── clientes.csv  
└── pedidos.csv  
```

> En el proyecto se utilizan estas mismas rutas desde la clase `com.jlgp.pedidosya.ops.OperacionesFicheros`.

---

## Formatos CSV

### `clientes.csv`
**Cabecera:** 
- `id,nombre,email`  

**Filas (ejemplo):**
- `1,María López,maria@mail.com`
- `2,Juan García,juan@mail.com`
- `3,Lucía Fernández,lucia@mail.com`


### `clientes.csv`
**Cabecera:**
- `id,clienteId,producto,cantidad`  

**Filas (ejemplo):**
- `101,1,Paella,2`
- `102,1,Tortilla de patatas,1`
- `103,2,Gazpacho,3`

---

## Clase de operaciones utilizada

Se centralizan las operaciones de creación de datos, serialización binaria y CSV en **`com.jlgp.pedidosya.ops.OperacionesFicheros`**:

- **Rutas de trabajo**
    - `src/main/resources/datos.dat`
    - `src/main/resources/clientes.csv`
    - `src/main/resources/pedidos.csv`

- **Métodos principales**
    - `crearDatos(List<Cliente> clientes, List<Pedido> pedidos)`  
      Genera 3 clientes y 5 pedidos de ejemplo (asociados).
    - `serializarDatos(List<Cliente> clientes, List<Pedido> pedidos)`  
      Serializa un objeto contenedor `Datos(clientes, pedidos)` a `datos.dat` usando `SerializationUtil`.
    - `deserializarDatos(): Datos`  
      Lee desde `datos.dat` y devuelve el primer objeto `Datos`.
    - `clientsToCsv(List<Cliente> list)`  
      Exporta clientes a `clientes.csv` (incluye cabecera).
    - `ordersToCsv(List<Pedido> list)`  
      Exporta pedidos a `pedidos.csv` (incluye cabecera).
    - `csvToClients(): List<Cliente>`  
      Importa desde `clientes.csv` a una lista de `Cliente`.
    - `csvToOrders(): List<Pedido>`  
      Importa desde `pedidos.csv` a una lista de `Pedido`.

> La serialización binaria se realiza mediante `com.jlgp.pedidosya.utils.SerializationUtil` y un DTO contenedor `com.jlgp.pedidosya.dto.Datos`.

---

## Flujo de trabajo

La aplicación ejecuta el siguiente flujo (ver `com.jlgp.pedidosya.AcPedidosyaAct1Application`):

1. **Crear datos de ejemplo**  
   `OperacionesFicheros.crearDatos(clientes, pedidos)` genera 3 clientes y 5 pedidos asociados.
2. **Serializar a binario (`datos.dat`)**  
   `OperacionesFicheros.serializarDatos(clientes, pedidos)` guarda un DTO contenedor `Datos(clientes, pedidos)` usando `SerializationUtil`.
3. **Mostrar en consola (antes de serializar)**  
   Se imprimen las listas iniciales de `clientes` y `pedidos`.
4. **Deserializar desde `datos.dat`**  
   `OperacionesFicheros.deserializarDatos()` lee el objeto `Datos` y reasigna las listas `clientes` y `pedidos`.
5. **Mostrar en consola (después de serializar/deserializar)**  
   Se imprimen de nuevo `clientes` y `pedidos` para verificar la integridad.
6. **Exportar a CSV**
    - `OperacionesFicheros.clientsToCsv(clientes)` → `src/main/resources/clientes.csv`
    - `OperacionesFicheros.ordersToCsv(pedidos)` → `src/main/resources/pedidos.csv`
7. **Importar desde CSV**
    - `OperacionesFicheros.csvToClients()` carga `clientes.csv` a `List<Cliente>`
    - `OperacionesFicheros.csvToOrders()` carga `pedidos.csv` a `List<Pedido>`  
      Se imprimen por consola las listas importadas.
---
