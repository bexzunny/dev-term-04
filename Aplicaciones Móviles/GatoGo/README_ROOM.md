# GatoGO - Persistencia local con Room

Se agregaron las dos primeras tablas solicitadas:

## Tabla `usuario`
- `id_usuario`: INTEGER, llave primaria autogenerada.
- `nombre`: TEXT.
- `correo`: TEXT.
- `pass`: TEXT.
- `fecha_registro`: TEXT con formato `yyyy-MM-dd HH:mm:ss`.

## Tabla `gato`
- `id_gato`: INTEGER, llave primaria autogenerada.
- `nombre`: TEXT.
- `descripcion`: TEXT.
- `colores`: TEXT.
- `fecha_registro`: TEXT con formato `yyyy-MM-dd HH:mm:ss`.

## Arquitectura agregada
- `data/database/entity`: entidades Room.
- `data/database/dao`: operaciones CRUD.
- `data/database/AppDatabase.kt`: base de datos Room.
- `data/mapper`: conversiones Entity/Domain.
- `data/repository`: implementaciones de repositorios.
- `data/di`: módulos Hilt.
- `domain/model`: modelos.
- `domain/repository`: contratos.
- `ui/viewmodel`: ViewModels de Usuario y Gato.

Se configuraron Room, KSP, Coroutines y Hilt. La base de datos se llama `gatogo.db`.

## Notas
- Se corrigió el nombre escrito como `id_usuaio` a `id_usuario`.
- Las tablas quedan independientes porque no se proporcionó una columna `id_usuario` dentro de `gato` ni se solicitó una relación entre ambas.
- `fecha_registro` se genera automáticamente desde los ViewModels al insertar.
- `pass` se conserva con el nombre solicitado para el ejercicio. No almacenes contraseñas en texto plano en una aplicación real; debe usarse un mecanismo seguro de autenticación y almacenamiento de credenciales.
- Las pantallas actuales se conservaron. Los ViewModels y CRUD están creados, pero todavía no se agregaron formularios visuales para registrar/editar usuarios o gatos.
- La compilación no pudo verificarse en este entorno porque Gradle necesita descargar la distribución 9.6.0 y el entorno no tiene conexión de red. Abre el proyecto en Android Studio, sincroniza Gradle y ejecuta `:app:assembleDebug`.
