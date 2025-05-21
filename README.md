
# VOIDWEAR

**VoidWear** es una aplicación de escritorio desarrollada en Java para la gestión completa de una tienda de ropa. Permite administrar productos, usuarios, pedidos, logs del sistema y exportar los datos a XML validado por esquema XSD.

---

## 🧩 Características principales

- Gestión de productos (CRUD)
- Gestión de usuarios y clientes con control de roles (`GESTOR`, `EMPLEADO`, `CLIENTE`)
- Registro de pedidos con líneas de productos
- Visualización de logs del sistema
- Exportación de todos los datos a un archivo XML validado con `voidwear.xsd`
- Interfaz gráfica con Swing

---

## 📦 Estructura del proyecto

- `src/` → Código fuente Java
  - `bd/` → Acceso a datos (DAOs)
  - `modelo/` → Clases de dominio: `Producto`, `Usuario`, `Pedido`, `Log`, etc.
  - `vista/` → Interfaz gráfica (Swing): paneles, diálogos y frames
  - `util/` → Funciones utilitarias: `LogManager`, `ExportadorXML`
- `recursos/` → Archivos de configuración y multimedia
  - `config/` → XML, XSD, ODB
  - `imagenes/` → Logos e interfaz gráfica
- `lib/` → Librerías externas (`ObjectDB`, `MySQL Connector`)
- `log/` → Registros del sistema

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Swing (interfaz gráfica)
- MySQL (persistencia SQL)
- ObjectDB (persistencia de usuarios)
- XML + XSD (exportación validada)
- JDBC (conexión a base de datos)

---

## 🚀 Cómo ejecutar el proyecto

1. Asegúrate de tener Java 17 o superior instalado.
2. Abre el proyecto en tu IDE (Eclipse, IntelliJ, etc.).
3. Incluye las dependencias:
   - `lib/objectdb-2.9.2.jar`
   - `lib/mysql-connector-j-9.x.x.jar`
4. Configura la conexión en `ConexionBD.java` si es necesario.
5. Ejecuta `Main.java`.

---

## 🧪 Validación XML

Al exportar el archivo `voidwear.xml`, se genera automáticamente con estructura `pretty print` y se valida contra `voidwear.xsd`.

---

## 📄 Licencia

Proyecto académico para uso formativo. No distribuible comercialmente sin autorización.

