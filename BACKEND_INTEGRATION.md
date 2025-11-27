# Integración Backend - LevelUp Gamer

## Configuración del Backend

La aplicación móvil ahora está integrada con el backend de microservicios ubicado en la carpeta `Backend/`.

### Arquitectura del Backend

El backend utiliza una arquitectura de microservicios con los siguientes componentes:

- **API Gateway** (Puerto 8080): Punto de entrada único para todas las peticiones
- **Auth Service** (Puerto 8081): Servicio de autenticación y autorización
- **User Service** (Puerto 8082): Gestión de usuarios
- **Product Service** (Puerto 8083): Gestión de productos y catálogo
- **Order Service** (Puerto 8084): Gestión de órdenes y compras
- **Analytics Service** (Puerto 8085): Analíticas y métricas
- **Notification Service** (Puerto 8086): Envío de notificaciones
- **File Service** (Puerto 8087): Gestión de archivos e imágenes
- **Config Service** (Puerto 8888): Configuración centralizada

### Levantar el Backend

#### Requisitos Previos

- Java 17 o superior
- Maven 3.8+
- Base de datos (según configuración de cada servicio)

#### Pasos para Iniciar

1. **Iniciar API Gateway:**
```powershell
cd Backend\LevelUp_Api_gateway
.\mvnw spring-boot:run
```

2. **Iniciar Auth Service:**
```powershell
cd Backend\LevelUp_Auth_service
.\mvnw spring-boot:run
```

3. **Iniciar Product Service:**
```powershell
cd Backend\LevelUp_Product_service
.\mvnw spring-boot:run
```

4. **Iniciar otros servicios según necesidad**

### Configuración de la App Android

La app está configurada para conectarse al backend usando:

- **URL Base:** `http://10.0.2.2:8080/` (para emulador Android)
- **URL Base alternativa:** `http://localhost:8080/` (para dispositivo físico en misma red)

#### Cambiar URL del Backend

Editar el archivo: `app/src/main/java/com/levelup/gamer/network/ApiConstants.kt`

```kotlin
const val BASE_URL = "http://TU_IP:8080/"  // Cambiar por IP de tu servidor
```

### Endpoints Integrados

#### Autenticación

- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/register` - Registrar usuario
- `POST /api/auth/validate` - Validar token

#### Productos

- `GET /api/productos` - Obtener todos los productos
- `GET /api/productos/destacados` - Obtener productos destacados
- `GET /api/productos/categoria/{id}` - Filtrar por categoría
- `GET /api/productos/buscar?nombre={query}` - Buscar productos
- `GET /api/productos/{id}` - Obtener producto por ID

### Funcionalidad Implementada

✅ **Login con Backend** - La autenticación ahora usa el servicio Auth
✅ **Productos desde Backend** - Los productos se cargan desde el Product Service
✅ **Fallback a datos locales** - Si el backend no está disponible, usa datos mock
✅ **Persistencia de sesión** - Las sesiones se guardan usando DataStore

### Testing

Para probar la conexión con el backend:

1. Verificar que el API Gateway esté corriendo: `http://localhost:8080/api/auth/health`
2. Verificar Product Service: `http://localhost:8080/api/productos/health`
3. Iniciar la app Android y verificar logs de Retrofit

### Troubleshooting

**Error de conexión:**
- Verificar que todos los servicios estén corriendo
- Verificar firewall (puerto 8080 debe estar abierto)
- Para emulador Android, usar `10.0.2.2` en lugar de `localhost`
- Para dispositivo físico, usar IP de tu computadora en la red local

**Timeout:**
- Aumentar timeouts en `RetrofitClient.kt` si la conexión es lenta
- Verificar que la base de datos del backend esté corriendo

### Próximos Pasos

- [ ] Implementar Order Service para gestión de compras
- [ ] Integrar File Service para carga de imágenes
- [ ] Implementar Notification Service para push notifications
- [ ] Agregar caché local con Room para modo offline
