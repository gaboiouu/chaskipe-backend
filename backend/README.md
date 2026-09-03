# Chaskipé

Chaskipé es una aplicación pensada para facilitar la comunicación entre personas sordas, mudas y oyentes. La aplicación utiliza la cámara del celular para reconocer la Lengua de Señas Peruana, convertirla en texto y reproducirla mediante audio. También puede mostrar respuestas en lenguaje de señas a través de un avatar animado.

Este repositorio contiene el backend de la aplicación. El backend recibe y administra la información de usuarios, conversaciones, mensajes, sesiones de captura, gestos detectados, audios, frases frecuentes, configuraciones y secuencias del avatar.

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL
- Supabase
- Docker
- Maven

La base de datos se encuentra alojada en Supabase. El backend se conecta a ella mediante PostgreSQL y conserva el esquema definido para Chaskipé.

## Organización del backend

El código está separado en capas generales para mantenerlo ordenado y facilitar su mantenimiento:

```text
src/main/java/pe/chaskipe/api/
├── controller/   Endpoints de la API
├── dto/          Datos que entran y salen de la API
├── model/        Entidades relacionadas con las tablas
├── repository/   Acceso a la base de datos
├── service/      Lógica de la aplicación
├── config/       Configuración de CORS y documentación
└── common/       Manejo general de errores
```

Actualmente el backend trabaja con los siguientes módulos: usuarios, configuraciones, conversaciones, sesiones de captura, gestos detectados, mensajes, audios, secuencias del avatar, frases frecuentes, feedback de precisión e historial de actividades.

## Rutas principales

Todas las rutas de la API comienzan con `/api/v1`.

```text
/usuarios
/configuraciones
/conversaciones
/sesiones-captura
/gestos
/mensajes
/audios
/secuencias-avatar
/frases-frecuentes
/feedback
/historial
```

## Estado actual

El backend está preparado para ejecutarse de forma local o dentro de Docker. La conexión con Supabase ya está configurada mediante variables de entorno y Hibernate valida el esquema existente sin modificarlo.

La autenticación JWT todavía no está incluida, por lo que esta versión corresponde a una etapa de desarrollo. La configuración de CORS permite la comunicación con el cliente móvil desarrollado con React Native y Expo.

## Accesos durante el desarrollo

Con el backend iniciado localmente, la API está disponible en:

```text
http://localhost:8080/api
```

La documentación de la API se puede consultar en:

```text
http://localhost:8080/api/docs
```

Y el estado del servicio en:

```text
http://localhost:8080/api/actuator/health
```

## Docker

El backend cuenta con un `Dockerfile` y un archivo `compose.yml`. Docker ejecuta la aplicación Spring Boot y esta mantiene la conexión con la base de datos alojada en Supabase.

El proyecto puede seguir desarrollándose normalmente; cuando se incorporen cambios, la imagen se puede volver a construir para probar la nueva versión.
