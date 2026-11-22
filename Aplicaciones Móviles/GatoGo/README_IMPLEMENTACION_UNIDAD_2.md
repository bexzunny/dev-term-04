# GatoGO – Implementación de Unidad 2

Esta versión continúa el proyecto original de GatoGO. Se conservan Kotlin, Jetpack Compose, Material 3, Hilt, Room y la estructura de paquetes existente. Los cambios agregan navegación, autenticación local, consulta/registro de gatos y la entidad de avistamientos.

## Estado funcional

- **Hilt:** `GatoGoApp` queda registrado en el `AndroidManifest.xml`; `MainActivity` utiliza `@AndroidEntryPoint` y carga `AppNavegacion()`.
- **Navegación:** las rutas se centralizan en `AppScreens`. La barra inferior conecta Inicio, Mapa, Registrar, Colección y Perfil, y deriva la selección de la ruta actual.
- **Acceso:** el usuario introduce correo y contraseña. Si el correo existe, se comprueba la contraseña; si no existe, se crea la cuenta automáticamente. Las cuentas nuevas requieren al menos ocho caracteres de contraseña. El nombre inicial se forma a partir del correo.
- **Sesión:** Preferences DataStore conserva el identificador del usuario activo. No se almacena la contraseña en DataStore.
- **Contraseñas:** las nuevas contraseñas se almacenan mediante PBKDF2, sal aleatoria y comparación constante. Las contraseñas en texto plano de la versión anterior se transforman durante la migración de Room.
- **Gatos:** el mapa consulta los gatos guardados mediante `GatoViewModel` y `Flow` de Room. El formulario nuevo usa exclusivamente los campos que ya existían en `GatoEntity`: nombre, colores, descripción y fecha generada.
- **Avistamientos:** se agrega `AvistamientoEntity`, DAO, modelo, mapper y repositorio con claves foráneas a `gato` y `usuario`. Aún no se incorpora un formulario para crear avistamientos, porque no era parte de esta entrega.
- **Colección:** se obtiene mediante la relación de avistamientos del usuario autenticado. Por ello puede permanecer vacía hasta que existan avistamientos asociados.
- **Orientación:** la navegación usa `rememberNavController`; los campos de formulario y filtros usan `rememberSaveable`; los datos de Room y la sesión se mantienen en ViewModels/DataStore.

## Alcance y limitaciones actuales

1. La persistencia es **local**, mediante Room/SQLite en cada dispositivo. No existe todavía un servidor ni sincronización entre dispositivos.
2. El mapa conserva la ilustración de la interfaz original y se identifica expresamente como ilustrativo. No representa coordenadas reales ni consume Google Maps.
3. El botón de ubicación informa que la geolocalización se integrará posteriormente. No solicita permisos ni rastrea la ubicación.
4. La lista del mapa muestra gatos reales almacenados localmente, pero sus tarjetas no son avistamientos.
5. La tabla `gato` no contiene un campo estructurado para collar. El filtro “Sin collar” solo busca esa frase en la descripción y no debe interpretarse como una clasificación fiable.
6. No se agrega fotografía, cámara, ficha detallada ni CRUD de avistamientos en esta etapa.

## Migración de Room

La base de datos pasa de la versión 1 a la 2 mediante `AppDatabase.MIGRATION_1_2`. La migración:

- conserva las tablas y filas existentes de `usuario` y `gato`;
- crea `avistamiento` con sus claves foráneas e índices;
- convierte las contraseñas heredadas a hash PBKDF2;
- no utiliza `fallbackToDestructiveMigration()` ni elimina las tablas existentes.

No borres manualmente `gatogo.db` para instalar esta versión: la actualización debe ejecutar la migración registrada.

## Archivos principales agregados o modificados

### Configuración y aplicación

- `gradle/libs.versions.toml`: dependencias de Navigation Compose 2.10.2 y Preferences DataStore 1.3.0.
- `app/build.gradle.kts`: incorpora ambas dependencias.
- `app/src/main/AndroidManifest.xml`: registra `GatoGoApp` como clase Application.
- `app/src/main/java/com/example/gatogo/MainActivity.kt`: activa Hilt y establece el host de navegación.

### Navegación e interfaz

- `ui/AppScreens.kt`: constantes de rutas.
- `ui/AppNavegacion.kt`: `NavHost`, control de sesión y barra inferior.
- `ui/LoginScreen.kt`: inicio de sesión/alta automática local.
- `ui/PrimeraPantalla.kt`: conserva inicio y conecta botones.
- `ui/SegundaPantalla.kt`: conserva la presentación del mapa, consulta Room, búsqueda y filtros.
- `ui/RegistrarGatoScreen.kt`: formulario para persistir gatos.
- `ui/ColeccionScreen.kt`: consulta de gatos vinculados a avistamientos del usuario.
- `ui/PerfilScreen.kt`: muestra la cuenta local y permite cerrar sesión.

### Datos y seguridad

- `data/security/PasswordHasher.kt`: creación y verificación de hash.
- `data/session/SesionManager.kt`: persistencia del identificador de sesión.
- `data/database/AppDatabase.kt`: versión 2 y migración 1→2.
- `data/database/entity/AvistamientoEntity.kt` y `dao/AvistamientoDao.kt`.
- `domain/model/Avistamiento.kt`, `domain/repository/AvistamientoRepository.kt`.
- `data/mapper/AvistamientoMapper.kt`, `data/repository/AvistamientoRepositoryImp.kt`.
- `data/di/DatabaseModule.kt` y `RepositoryModule.kt`: registro de DAO y repositorio.
- `GatoViewModel.kt` y `UsuarioViewModel.kt`: estados y operaciones conectadas a repositorios.

## Requisitos para compilar

El proyecto conserva el Gradle Wrapper 9.6.0 y la configuración de JDK 25 existente. También requiere Android SDK Platform 37, según `compileSdk`.

En el equipo de desarrollo:

```bash
cd GatoGo
./gradlew :app:assembleDebug
```

Para ejecutar pruebas locales e instrumentadas:

```bash
./gradlew test
./gradlew connectedDebugAndroidTest
```

El APK de depuración, después de compilar correctamente, se encontrará en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Si Android Studio utiliza otra ubicación del SDK, actualiza `sdk.dir` en `local.properties`. En Linux, concede permiso de ejecución a `gradlew` si fuera necesario: `chmod +x gradlew`.

## Comprobaciones realizadas en esta entrega

- El archivo TOML de versiones parsea correctamente.
- Los XML de recursos y el manifiesto parsean correctamente.
- Las referencias `R.string` y `R.drawable` encontradas tienen recursos definidos.
- Se probó el comportamiento de `PasswordHasher` en una prueba JVM aislada con un sustituto local de Base64 de Android.
- Se simuló en SQLite la creación de `avistamiento`; se verificó que se conservan las filas previas, que las claves foráneas rechazan identificadores inexistentes y que `ON DELETE RESTRICT` se aplica.
- Se intentó `./gradlew :app:compileDebugKotlin --no-daemon`, pero Gradle no pudo descargar la distribución 9.6.0 debido a un error DNS (`UnknownHostException: services.gradle.org`). En este entorno tampoco está instalado Android SDK. **Por tanto, la compilación Android completa y la ejecución en emulador/dispositivo quedan pendientes de validarse en Android Studio.**

No se debe considerar la aplicación como sincronizada, geolocalizada ni lista para producción; esta versión implementa una base funcional local para continuar el desarrollo académico.
