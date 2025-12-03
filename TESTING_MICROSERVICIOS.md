# Guía de Testing - Microservicios LevelUp

## 📋 Endpoints Health de Todos los Servicios

| Servicio | Puerto | URL Health | Descripción |
|----------|--------|------------|-------------|
| Auth Service | 8081 | http://localhost:8081/api/auth/health | Autenticación JWT |
| User Service | 8082 | http://localhost:8082/api/usuarios/health | Gestión usuarios |
| Product Service | 8083 | http://localhost:8083/api/productos/health | Catálogo productos |
| Order Service | 8084 | http://localhost:8084/api/ordenes/health | Gestión pedidos |
| Notification Service | 8085 | http://localhost:8085/api/notificaciones/health | Sistema notificaciones |
| File Service | 8086 | http://localhost:8086/api/archivos/health | Upload archivos |
| API Gateway | 8080 | http://localhost:8080/actuator/health | Punto entrada |

---

## 🔧 Método 1: PowerShell Script (Recomendado)

### Ejecutar Script Automático
```powershell
.\scripts\test_microservices.ps1
```

### Comando Manual Individual
```powershell
# Auth Service
Invoke-RestMethod -Uri 'http://localhost:8081/api/auth/health' -Method GET

# Product Service
Invoke-RestMethod -Uri 'http://localhost:8083/api/productos/health' -Method GET

# User Service
Invoke-RestMethod -Uri 'http://localhost:8082/api/usuarios/health' -Method GET
```

---

## 🌐 Método 2: Navegador Web

Abrir en cualquier navegador las siguientes URLs:

1. **Auth Service**: http://localhost:8081/api/auth/health
2. **User Service**: http://localhost:8082/api/usuarios/health
3. **Product Service**: http://localhost:8083/api/productos/health
4. **Order Service**: http://localhost:8084/api/ordenes/health
5. **Notification Service**: http://localhost:8085/api/notificaciones/health
6. **File Service**: http://localhost:8086/api/archivos/health
7. **API Gateway**: http://localhost:8080/actuator/health

**Respuesta esperada**: JSON con status "UP" o mensaje de salud

---

## 📮 Método 3: Postman

### Configuración Postman Collection

#### 1. Crear Nueva Collection
- Name: `LevelUp Microservices`
- Description: `Testing endpoints health de todos los servicios`

#### 2. Variables de Collection
```json
{
  "auth_base_url": "http://localhost:8081",
  "user_base_url": "http://localhost:8082",
  "product_base_url": "http://localhost:8083",
  "order_base_url": "http://localhost:8084",
  "notification_base_url": "http://localhost:8085",
  "file_base_url": "http://localhost:8086",
  "gateway_base_url": "http://localhost:8080"
}
```

#### 3. Requests Health Checks

**Request 1: Auth Service Health**
```
GET {{auth_base_url}}/api/auth/health
Headers: (ninguno necesario)
```

**Request 2: User Service Health**
```
GET {{user_base_url}}/api/usuarios/health
Headers: (ninguno necesario)
```

**Request 3: Product Service Health**
```
GET {{product_base_url}}/api/productos/health
Headers: (ninguno necesario)
```

**Request 4: Order Service Health**
```
GET {{order_base_url}}/api/ordenes/health
Headers: (ninguno necesario)
```

**Request 5: Notification Service Health**
```
GET {{notification_base_url}}/api/notificaciones/health
Headers: (ninguno necesario)
```

**Request 6: File Service Health**
```
GET {{file_base_url}}/api/archivos/health
Headers: (ninguno necesario)
```

**Request 7: API Gateway Health**
```
GET {{gateway_base_url}}/actuator/health
Headers: (ninguno necesario)
```

### 4. Test Login (Auth Service)

**POST Login**
```
POST {{auth_base_url}}/api/auth/login
Headers:
  Content-Type: application/json

Body (raw JSON):
{
  "correo": "admin@levelup.cl",
  "password": "admin123"
}
```

**Respuesta esperada**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer",
  "id": 1,
  "nombre": "Admin",
  "apellidos": "LevelUp",
  "correo": "admin@levelup.cl",
  "rol": "ADMIN"
}
```

### 5. Organización en Postman

```
LevelUp Microservices/
├── 01 - Health Checks/
│   ├── Auth Service Health
│   ├── User Service Health
│   ├── Product Service Health
│   ├── Order Service Health
│   ├── Notification Service Health
│   ├── File Service Health
│   └── API Gateway Health
└── 02 - Authentication/
    ├── Login
    └── Register
```

---

## 🚀 Iniciar Servicios Faltantes

Si algún servicio falla, iniciarlo manualmente:

### Auth Service (8081)
```powershell
cd "C:\Users\Asgard\Front_level_up\Entrega EVA3\Backend\LevelUp_Auth_service"
.\mvnw clean package -DskipTests
java -jar target\LevelUp_Auth_service-0.0.1-SNAPSHOT.jar
```

### Product Service (8083)
```powershell
cd "C:\Users\Asgard\Front_level_up\Entrega EVA3\Backend\LevelUp_Product_service"
.\mvnw clean package -DskipTests
java -jar target\LevelUp_Product_service-0.0.1-SNAPSHOT.jar
```

### User Service (8082)
```powershell
cd "C:\Users\Asgard\Front_level_up\Entrega EVA3\Backend\LevelUp_User_service"
.\mvnw clean package -DskipTests
java -jar target\LevelUp_User_service-0.0.1-SNAPSHOT.jar
```

### API Gateway (8080)
```powershell
cd "C:\Users\Asgard\Front_level_up\Entrega EVA3\Backend\LevelUp_Api_gateway"
.\mvnw clean package -DskipTests
java -jar target\LevelUp_Api_gateway-0.0.1-SNAPSHOT.jar
```

---

## 📊 Respuestas Esperadas

### Health Check Exitoso
```json
{
  "status": "UP"
}
```
o
```json
{
  "message": "Service is running"
}
```

### Health Check Fallido
- **Sin respuesta**: Servicio no está corriendo
- **Error 404**: Endpoint no existe (revisar ruta)
- **Error 500**: Servicio corriendo pero con errores internos

---

## ✅ Checklist de Verificación

- [ ] Auth Service (8081) responde OK
- [ ] User Service (8082) responde OK
- [ ] Product Service (8083) responde OK
- [ ] Order Service (8084) responde OK
- [ ] Notification Service (8085) responde OK
- [ ] File Service (8086) responde OK
- [ ] API Gateway (8080) responde OK
- [ ] Login Auth devuelve token JWT válido
- [ ] Todos los servicios conectados a Supabase

---

## 🔍 Troubleshooting

### Servicio no responde
1. Verificar que esté corriendo: `netstat -an | findstr :<puerto>`
2. Revisar logs del servicio
3. Verificar credenciales Supabase en `application.properties`
4. Reiniciar servicio

### Error de base de datos
1. Verificar PostgreSQL corriendo en Docker
2. Verificar credenciales Supabase:
   - URL: `db.xsgpfadjkjgbnnxgnqhp.supabase.co:5432`
   - Password: `levelup-666_supabase`
3. Verificar firewall/red

### Error 404 en health
1. Verificar que la ruta sea correcta
2. Algunos servicios pueden usar `/actuator/health` en lugar de `/api/.../health`
3. Revisar logs del servicio para ver endpoints disponibles

---

## 🎯 Próximos Pasos

1. **Ejecutar script PowerShell** para verificar servicios activos
2. **Iniciar servicios faltantes** según resultados
3. **Probar login** desde app móvil en emulador
4. **Configurar IP WiFi** para móvil físico (opcional)
5. **Testing end-to-end** completo desde app

---

*Última actualización: 2025-12-03*
*Estado: Auth Service corriendo OK en 8081*
