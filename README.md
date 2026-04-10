# Restaurante La Cucharita

Aplicación de escritorio para la gestión operativa de un restaurante: inventario de ingredientes, carta de platillos, pedidos y administración de personal. Desarrollada en **Java** con interfaz **JavaFX** y vistas declarativas **FXML**.

---

## Tabla de contenidos

1. [Descripción](#descripción)
2. [Alcance funcional](#alcance-funcional)
3. [Stack tecnológico](#stack-tecnológico)
4. [Arquitectura del proyecto](#arquitectura-del-proyecto)
5. [Persistencia de datos](#persistencia-de-datos)
6. [Requisitos previos](#requisitos-previos)
7. [Configuración y ejecución](#configuración-y-ejecución)
8. [Estructura del repositorio](#estructura-del-repositorio)
9. [Seguridad y buenas prácticas](#seguridad-y-buenas-prácticas)
10. [Limitaciones conocidas y líneas de mejora](#limitaciones-conocidas-y-líneas-de-mejora)
11. [Licencia](#licencia)

---

## Descripción

**La Cucharita** es un sistema de información orientado a usuarios internos del restaurante (personal autorizado). Permite iniciar sesión, gestionar empleados, mantener el inventario con unidades normalizadas, definir platillos compuestos por ingredientes con validación de stock, registrar órdenes con estados de flujo y consultar el listado de pedidos.

El punto de entrada de la aplicación es `ui.Main`, que carga la vista de inicio de sesión (`Login.fxml`) y asocia un único controlador de presentación, `LaCucharitaGUI`, reutilizado en todas las pantallas mediante `FXMLLoader.setController(this)`.

---

## Alcance funcional

| Módulo | Capacidades principales |
|--------|-------------------------|
| **Autenticación** | Login; creación de cuenta de empleado desde pantalla dedicada; cuenta “creador” con validación en capa de modelo. |
| **Personal** | Listado de empleados; alta de empleado; cambio de contraseña verificando identificación y contraseña actual. |
| **Inventario** | Alta de ingredientes (nombre, cantidad, unidad); tabla de inventario; modificación y eliminación mediante ventanas auxiliares. |
| **Carta / menú** | Visualización de platillos; creación de platillo con precio y composición por ingredientes (con comprobación de disponibilidad en inventario al armar el platillo). |
| **Pedidos** | Listado de órdenes (código UUID, estado, fecha); creación de pedido con platillos, fecha y estado; actualización del estado del pedido (p. ej. PENDIENTE → EN PROCESO → ENTREGADO). |

Los textos de interfaz y los estados de pedido están en **español**.

---

## Stack tecnológico

| Componente | Detalle |
|------------|---------|
| Lenguaje | Java **8** (compatibilidad y compilación definidas en `.settings/org.eclipse.jdt.core.prefs`) |
| UI | JavaFX (`Application`, `FXMLLoader`, `Scene`, controles y `TableView`) |
| Vistas | FXML bajo `src/ui/` |
| Modelo de dominio | Paquete `model`: entidades y agregados (`EmployeeList`, `IngredientsList`, `SaucerList`, `OrderList`, etc.) |
| IDE de referencia | Proyecto Eclipse (`.project`, `.classpath`) |

> **Nota sobre JavaFX:** En **Oracle JDK 8**, JavaFX iba incluido. Si usas **OpenJDK 8** u otra distribución, o un JDK **9+**, debes añadir el **JavaFX SDK** (o un runtime que lo incluya, p. ej. algunas builds “full” de Liberica/Azul) y configurar el *module path* o el *classpath* según la documentación de tu versión. Sin JavaFX en el classpath, la aplicación no arrancará.

---

## Arquitectura del proyecto

- **Capa de presentación:** un único controlador FXML (`LaCucharitaGUI`) concentra la lógica de navegación entre escenas, el cableado de `@FXML` y la orquestación de tablas y combo boxes. Los recursos FXML se cargan desde el mismo paquete (`getClass().getResource("...")`).
- **Capa de dominio / modelo:** listas y entidades serializables donde aplica (`Order`, etc.), con operaciones de ordenación (p. ej. empleados por comparador personalizado, platillos por nombre, ingredientes por nombre).
- **Separación:** el modelo no depende de JavaFX; la UI invoca métodos de las clases de lista y actualiza vistas con `ObservableList` / `PropertyValueFactory`.

Este diseño es coherente con prototipos o proyectos académicos; para evolucionar a producción suele recomendarse **dividir controladores por vista o por caso de uso** e inyectar servicios o repositorios, reduciendo el tamaño de la clase principal de UI.

---

## Persistencia de datos

El sistema combina:

1. **Exportación incremental a texto** (`FileWriter` con modo append en rutas bajo `data/`, p. ej. `ExportedEmployeeData.txt`, `ExportedIngredientsData.txt`, etc.).
2. **Serialización Java** (`ObjectOutputStream` / `ObjectInputStream`) hacia archivos bajo `data2/` (p. ej. `Employees.apo2`, `Ingredients.apo2`, `Saucers.apo2`, `Orders.apo2`).

Existen también métodos de **importación desde TXT** en algunas listas (formato con separador `;`), pensados para cargas iniciales o integración manual.

**Directorios:** la aplicación espera poder crear o escribir en `data/` y `data2/` **relativos al directorio de trabajo en tiempo de ejecución** (normalmente la raíz del proyecto o la carpeta desde la que se lanza el proceso). Conviene ejecutar la JVM con *working directory* establecida de forma explícita o documentada.

**Recursos gráficos:** el icono de ventana se resuelve como `pictures/LaCucharita.png` en el classpath; debe existir esa ruta en recursos compilados para evitar errores al cargar la imagen.

---

## Requisitos previos

- **JDK 8** compatible con el proyecto (o JDK superior **solo** si configuras JavaFX y el *source/target* de compilación de forma coherente).
- **JavaFX** disponible en tiempo de ejecución (según tu JDK).
- Opcional: **Eclipse IDE for Java Developers** para importar el proyecto tal cual está definido por `.project` / `.classpath`.

---

## Configuración y ejecución

### Con Eclipse

1. *File → Open Projects from File System* (o *Import → General → Existing Projects into Workspace*) y selecciona la carpeta del repositorio.
2. Asegura que el JRE/JDK y JavaFX estén configurados en el *Build Path* si tu entorno no incluye JavaFX por defecto.
3. Ejecuta la clase `ui.Main` como aplicación Java.

### Línea de comandos (referencia)

Tras compilar `src` hacia `bin` (o el directorio de salida que uses):

```bash
# Ejemplo: ajusta el separador de classpath (; en Windows, : en Linux/macOS) y las rutas a jfxrt.jar / SDK si aplica
java -cp bin[:ruta/javafx/lib/*] ui.Main
```

El *classpath* exacto depende de tu instalación de JavaFX; consulta la guía oficial de la versión que utilices.

---

## Estructura del repositorio

```
jfx--Restaurante-la-Cucharita/
├── .project / .classpath          # Metadatos Eclipse
├── .settings/                     # Preferencias del compilador (Java 8)
├── src/
│   ├── ui/
│   │   ├── Main.java              # Punto de entrada JavaFX
│   │   ├── LaCucharitaGUI.java    # Controlador FXML principal
│   │   └── *.fxml                 # Definición de vistas
│   └── model/                     # Entidades y listas persistibles
└── bin/                           # Salida de compilación (si se usa)
```

Nombre lógico del proyecto en Eclipse: `jfx-RestauranteLaCucharita` (véase `.project`).

---

## Seguridad y buenas prácticas

- Las **credenciales de la cuenta creador** están implementadas de forma **estática en código** (`EmployeeList`). Esto es aceptable solo en entornos de demostración o desarrollo local; en cualquier despliegue real deben sustituirse por **autenticación segura**, almacenamiento de secretos fuera del repositorio y **hashing de contraseñas** (p. ej. bcrypt, Argon2).
- Las contraseñas de empleados en memoria y en serialización **no deben considerarse protegidas** tal como está el diseño actual.
- Los archivos de exportación TXT y los `.apo2` pueden contener datos sensibles; añádelos a **`.gitignore`** si no deben versionarse, y documenta políticas de backup solo en entornos controlados.

---

## Limitaciones conocidas y líneas de mejora

- **Controlador monolítico:** facilita el arranque del proyecto, pero dificulta el mantenimiento; valorar MVVM, controladores por FXML o inyección de dependencias.
- **Carga al inicio:** existen métodos `load*` en las clases de lista; si se desea que al abrir la aplicación se restaure el estado desde `data2/`, debe invocarse explícitamente desde el arranque (p. ej. constructor o `init` de la aplicación) y manejar excepciones de primer arranque sin archivos.
- **Concurrencia y transacciones:** la lógica de pedidos e inventario está en memoria; un entorno multiusuario real exigiría base de datos, bloqueos o colas.
- **Internacionalización:** textos embebidos en código y FXML; extraer a recursos `ResourceBundle` si se amplía idioma.
- **Calidad de datos:** validar formatos numéricos, fechas nulas en `DatePicker` y entradas vacías de forma uniforme (evitar `NullPointerException` en combos no inicializados).

---

**Autor:** Jorge Eduardo Jojoa (Abril, 2021).
