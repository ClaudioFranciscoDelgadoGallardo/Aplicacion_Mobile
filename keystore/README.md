# 🔐 Generación de APK Firmado
## Level Up Gamer

---

## 📋 OPCIÓN 1: Usar el Script Automático (RECOMENDADO)

### Pasos:

1. **Abrir terminal CMD** en la raíz del proyecto
2. **Ejecutar el script:**
   ```cmd
   generate_apk.bat
   ```
3. **Seguir las instrucciones** en pantalla
4. **Completar información del keystore** cuando se solicite

El script hará automáticamente:
- ✅ Crear directorio `keystore/`
- ✅ Generar keystore `levelup-gamer.jks`
- ✅ Limpiar build anterior
- ✅ Compilar APK firmado
- ✅ Mostrar ubicación del APK

---

## 📋 OPCIÓN 2: Generación Manual

### Paso 1: Generar Keystore

Abrir terminal CMD y ejecutar:

```cmd
keytool -genkey -v -keystore keystore\levelup-gamer.jks ^
    -alias levelupgamer ^
    -keyalg RSA ^
    -keysize 2048 ^
    -validity 10000 ^
    -storepass LevelUpGamer2025 ^
    -keypass LevelUpGamer2025
```

**Información a proporcionar:**
- Nombre y apellido: `[Tu Nombre Completo]`
- Unidad organizativa: `DUOC UC`
- Organización: `DUOC UC`
- Ciudad: `Santiago`
- Estado/Provincia: `Region Metropolitana`
- Código de país: `CL`

### Paso 2: Limpiar Proyecto

```cmd
gradlew clean
```

### Paso 3: Generar APK Firmado

```cmd
gradlew assembleRelease
```

### Paso 4: Ubicar el APK

El APK firmado estará en:
```
app\build\outputs\apk\release\app-release.apk
```

---

## 🔑 CREDENCIALES DEL KEYSTORE

**IMPORTANTE - Guarda esta información:**

```
Archivo: keystore/levelup-gamer.jks
Alias: levelupgamer
Store Password: LevelUpGamer2025
Key Password: LevelUpGamer2025
Validez: 10,000 días (~27 años)
```

---

## ⚠️ ADVERTENCIAS DE SEGURIDAD

### ❌ NO HACER:
- **NO** subir el keystore (.jks) a GitHub
- **NO** compartir las contraseñas públicamente
- **NO** perder el keystore (no podrás actualizar la app)

### ✅ SÍ HACER:
- **SÍ** guardar el keystore en un lugar seguro
- **SÍ** hacer backup del keystore
- **SÍ** documentar las contraseñas en lugar privado
- **SÍ** usar el mismo keystore para futuras versiones

---

## 📱 INSTALAR APK EN DISPOSITIVO

### Opción A: Emulador
1. Arrastrar `app-release.apk` al emulador
2. O usar: `adb install app\build\outputs\apk\release\app-release.apk`

### Opción B: Dispositivo Físico
1. Transferir APK al dispositivo
2. Habilitar "Fuentes desconocidas" en Configuración
3. Abrir APK en el dispositivo
4. Instalar

---

## 🔍 VERIFICAR FIRMA DEL APK

Para verificar que el APK está correctamente firmado:

```cmd
keytool -list -printcert -jarfile app\build\outputs\apk\release\app-release.apk
```

Deberías ver información del certificado con:
- Owner: Tu información
- Issuer: Tu información
- Serial number
- Valid from / Valid until

---

## 🐛 SOLUCIÓN DE PROBLEMAS

### Error: "keytool no se reconoce como comando"
**Solución:** Instala Java JDK y agrégalo al PATH
```cmd
set PATH=%PATH%;C:\Program Files\Java\jdk-XX\bin
```

### Error: "Keystore was tampered with, or password was incorrect"
**Solución:** Verifica que las contraseñas en `build.gradle.kts` coincidan

### Error: "Cannot find keystore file"
**Solución:** Verifica que el keystore existe en `keystore/levelup-gamer.jks`

### Error de compilación
**Solución:** 
1. Ejecuta `gradlew clean`
2. Sincroniza proyecto en Android Studio
3. Vuelve a ejecutar `gradlew assembleRelease`

---

## 📊 CONFIGURACIÓN EN build.gradle.kts

```kotlin
signingConfigs {
    create("release") {
        storeFile = file("../keystore/levelup-gamer.jks")
        storePassword = "LevelUpGamer2025"
        keyAlias = "levelupgamer"
        keyPassword = "LevelUpGamer2025"
    }
}

buildTypes {
    release {
        isMinifyEnabled = true
        isShrinkResources = true
        signingConfig = signingConfigs.getByName("release")
        proguardFiles(...)
    }
}
```

---

## 📋 CHECKLIST PARA LA DEFENSA

Antes de la defensa técnica, verifica:

- [ ] Keystore generado y guardado
- [ ] APK firmado compilado exitosamente
- [ ] APK probado en emulador/dispositivo
- [ ] Firma verificada con keytool
- [ ] Contraseñas documentadas
- [ ] Screenshots del proceso guardados
- [ ] APK copiado en USB/Drive de respaldo

---

## 📝 PARA LA DOCUMENTACIÓN

Incluir en el informe:

1. **Screenshot del comando keytool** generando keystore
2. **Screenshot de gradlew assembleRelease** exitoso
3. **Screenshot del APK** en la carpeta outputs
4. **Screenshot de la firma verificada**
5. **Screenshot del APK instalado** en dispositivo

---

## 🎯 JUSTIFICACIÓN TÉCNICA (Para la Defensa)

**¿Por qué firma digital?**
- Identifica al desarrollador
- Previene modificaciones maliciosas
- Requerido para Play Store
- Garantiza autenticidad de actualizaciones

**¿Por qué keystore?**
- Almacena certificados de forma segura
- Permite firmar múltiples apps
- Formato estándar de Java
- Compatible con herramientas Android

**¿Por qué estos parámetros?**
- RSA 2048 bits: Balance seguridad/performance
- Validez 10,000 días: Evita expiración prematura
- JKS format: Compatible con Android build tools

---

**Generado:** 26/11/2025
**Proyecto:** Level Up Gamer
**Versión:** 1.0

