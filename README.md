# 📱 Level-Up Gamer - App Móvil Android

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

Aplicación móvil de tienda gamer desarrollada con **Kotlin** y **Jetpack Compose** para Android.

---

## 🎯 Descripción

**Level-Up Gamer** es una app móvil para tienda de productos gaming que incluye:

- ✅ Catálogo de productos con 12 artículos (Consolas, Juegos, Accesorios, PC Gaming)
- ✅ Carrito de compras con persistencia (Room Database)
- ✅ Búsqueda de productos en tiempo real
- ✅ Menú lateral con navegación
- ✅ Tema oscuro con paleta de colores gamer (Verde Neón #39FF14, Azul #1E90FF, Negro #000000)
- ✅ Interfaz moderna con Material Design 3

---

## 📋 Requisitos Previos

Antes de empezar, necesitas tener instalado:

### 1. **Java Development Kit (JDK) 17 o superior**
- **Descargar:** [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) o [OpenJDK 17](https://adoptium.net/)
- **Verificar instalación:**
  ```powershell
  java -version
  ```
  Debería mostrar algo como: `java version "17.0.x"`

### 2. **Android Studio (última versión)**
- **Descargar:** [Android Studio](https://developer.android.com/studio)
- **Versión mínima:** Android Studio Hedgehog (2023.1.1) o superior
- **Tamaño:** ~1 GB de descarga, ~5 GB instalado

---

## 🚀 Instalación de Android Studio (Paso a Paso)

### **Paso 1: Descargar Android Studio**

1. Ve a [https://developer.android.com/studio](https://developer.android.com/studio)
2. Haz clic en **"Download Android Studio"**
3. Acepta los términos y condiciones
4. Descarga el instalador (`.exe` para Windows)

### **Paso 2: Instalar Android Studio**

1. Ejecuta el instalador descargado
2. Sigue el asistente de instalación:
   - ✅ Marca **"Android Studio"**
   - ✅ Marca **"Android Virtual Device"** (para el emulador)
3. Elige la carpeta de instalación (recomendado: dejar por defecto)
4. Haz clic en **"Install"** y espera (puede tomar 10-15 minutos)
5. Haz clic en **"Finish"**

### **Paso 3: Configuración Inicial**

1. Abre Android Studio
2. En la ventana de bienvenida, selecciona **"Next"**
3. Tipo de instalación:
   - Selecciona **"Standard"** (recomendado)
   - Haz clic en **"Next"**
4. Selecciona el tema:
   - **Darcula** (oscuro) o **Light** (claro)
   - Haz clic en **"Next"**
5. Verifica los componentes:
   - ✅ Android SDK
   - ✅ Android SDK Platform
   - ✅ Android Virtual Device
6. Haz clic en **"Finish"**
7. **Espera a que descargue los componentes** (puede tomar 20-30 minutos)

---

## 📂 Abrir el Proyecto en Android Studio

### **Opción 1: Clonar desde GitHub (Recomendado)**

1. Abre Android Studio
2. En la pantalla de bienvenida, haz clic en **"Get from VCS"**
3. En **"URL"** pega:
   ```
   https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile.git
   ```
4. Elige la carpeta donde quieres guardar el proyecto
5. Haz clic en **"Clone"**
6. Espera a que se descargue el proyecto
7. Android Studio te preguntará **"Trust and Open Project?"** → Haz clic en **"Trust Project"**

### **Opción 2: Abrir Proyecto Existente**

Si ya tienes el proyecto descargado:

1. Abre Android Studio
2. Haz clic en **"Open"**
3. Navega a la carpeta del proyecto: `C:\Users\Asgard\Desktop\Aplicacion_Mobile`
4. Haz clic en **"OK"**
5. Espera a que Android Studio indexe el proyecto (verás una barra de progreso abajo)

---

## ⚙️ Configurar el Proyecto

### **Paso 1: Sincronizar Gradle**

Cuando abras el proyecto por primera vez, Android Studio automáticamente:

1. **Descargará las dependencias** (esto puede tomar 5-10 minutos la primera vez)
2. Verás en la parte inferior: **"Gradle sync in progress..."**
3. Espera hasta que veas: **"Gradle sync finished"** ✅

**Si hay errores:**
- Haz clic en **"File"** → **"Sync Project with Gradle Files"**
- O usa el atajo: `Ctrl + Shift + O`

### **Paso 2: Verificar SDK de Android**

1. Ve a **"File"** → **"Project Structure"** (o `Ctrl + Alt + Shift + S`)
2. En **"SDK Location"**, verifica que tengas un SDK instalado
3. Si no hay SDK:
   - Haz clic en **"Edit"** junto a Android SDK Location
   - Selecciona **API Level 34** (Android 14.0)
   - Haz clic en **"Apply"** y espera la descarga

---

## 📱 Configurar el Emulador (Dispositivo Virtual)

### **Crear un Dispositivo Virtual**

1. En Android Studio, haz clic en el icono de **"Device Manager"** (icono de teléfono en la barra superior)
   - O ve a **"Tools"** → **"Device Manager"**

2. Haz clic en **"Create Device"**

3. **Selecciona el Hardware:**
   - Categoría: **"Phone"**
   - Dispositivo recomendado: **"Pixel 5"** o **"Pixel 6"**
   - Haz clic en **"Next"**

4. **Selecciona la Imagen del Sistema:**
   - Release: **"S"** (API 31) o **"T"** (API 33) - **Recomendado: API 31**
   - Si no está descargado, haz clic en **"Download"** junto al nombre
   - Espera la descarga (puede tomar 10-15 minutos)
   - Haz clic en **"Next"**

5. **Configuración AVD:**
   - Nombre: Puedes dejarlo como está o cambiarlo (ej: "Pixel_5_API_31")
   - Verifica que **"Enable Device Frame"** esté marcado (para ver el marco del teléfono)
   - Haz clic en **"Finish"**

### **Configuración del Emulador (Opcional pero Recomendado)**

Para mejor rendimiento:

1. Ve a **"File"** → **"Settings"** (o `Ctrl + Alt + S`)
2. Busca **"Emulator"**
3. Marca **"Launch in a tool window"** (ejecuta el emulador dentro de Android Studio)
4. Haz clic en **"Apply"** y **"OK"**

---

## ▶️ Ejecutar la Aplicación

### **Método 1: Usando el Emulador**

1. En la barra superior de Android Studio:
   - **Verifica que esté seleccionado:**
     - Configuración: **"app"**
     - Dispositivo: Tu emulador creado (ej: "Pixel 5 API 31")

2. Haz clic en el botón **"Run"** (▶️ verde) o presiona `Shift + F10`

3. **Primera ejecución:**
   - El emulador se iniciará (puede tomar 2-3 minutos la primera vez)
   - Verás el logo de Android
   - La app se instalará automáticamente
   - Se abrirá la app **Level-Up Gamer** 🎮

4. **¡Listo!** Ahora puedes probar la app:
   - Ver productos
   - Buscar productos
   - Añadir al carrito
   - Abrir el menú lateral
   - Realizar compras

### **Método 2: Usando un Dispositivo Físico (Opcional)**

Si tienes un teléfono Android:

1. **Habilita el Modo Desarrollador en tu teléfono:**
   - Ve a **"Ajustes"** → **"Acerca del teléfono"**
   - Toca **"Número de compilación"** 7 veces
   - Verás un mensaje: **"Ahora eres un desarrollador"**

2. **Habilita la Depuración USB:**
   - Ve a **"Ajustes"** → **"Sistema"** → **"Opciones de desarrollador"**
   - Activa **"Depuración USB"**

3. **Conecta tu teléfono a la PC:**
   - Usa un cable USB
   - En el teléfono, acepta **"Permitir depuración USB"** (marca "Siempre permitir desde este equipo")

4. **En Android Studio:**
   - Selecciona tu dispositivo en la lista desplegable
   - Haz clic en **"Run"** (▶️)

---

## 🏗️ Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/levelup/gamer/
│   │   ├── MainActivity.kt              # Actividad principal
│   │   ├── model/                       # Modelos de datos
│   │   │   ├── Producto.kt              # Modelo Producto
│   │   │   ├── CarritoItem.kt           # Modelo Item del Carrito
│   │   │   └── User.kt                  # Modelo Usuario
│   │   ├── repository/                  # Repositorios
│   │   │   ├── ProductoRepository.kt    # 12 productos hardcodeados
│   │   │   ├── carrito/
│   │   │   │   ├── CarritoDao.kt        # DAO de Room
│   │   │   │   └── CarritoRepository.kt # Lógica del carrito
│   │   │   └── database/
│   │   │       └── AppDatabase.kt       # Base de datos Room
│   │   ├── ui/
│   │   │   ├── screens/
│   │   │   │   ├── HomeScreen.kt        # Pantalla principal
│   │   │   │   └── CartScreen.kt        # Pantalla del carrito
│   │   │   ├── navigation/
│   │   │   │   └── MainDrawer.kt        # Menú lateral
│   │   │   └── theme/                   # Tema de la app
│   │   │       ├── Color.kt             # Paleta de colores
│   │   │       ├── Theme.kt             # Tema Material Design 3
│   │   │       └── Type.kt              # Tipografía
│   ├── res/                             # Recursos
│   │   ├── values/
│   │   │   ├── colors.xml               # Colores
│   │   │   ├── strings.xml              # Textos
│   │   │   └── themes.xml               # Tema XML
│   └── AndroidManifest.xml              # Configuración de la app
└── build.gradle.kts                     # Dependencias
```

---

## 🎨 Paleta de Colores

La app usa una paleta inspirada en el tema gamer:

| Color | Hex | Uso |
|-------|-----|-----|
| **Negro** | `#000000` | Fondo principal |
| **Verde Neón** | `#39FF14` | Color primario, botones, acentos |
| **Azul Dodger** | `#1E90FF` | Color secundario, enlaces |
| **Gris Oscuro** | `#121212` | Tarjetas, superficies |
| **Gris Claro** | `#D3D3D3` | Texto secundario |

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Kotlin 1.9.0
- **UI Framework:** Jetpack Compose (BOM 2024.02.00)
- **Base de Datos:** Room 2.6.1
- **Navegación:** Jetpack Compose Navigation 2.7.6
- **Concurrencia:** Kotlin Coroutines 1.7.3
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Material Design:** Material 3
- **Mínimo SDK:** API 24 (Android 7.0 Nougat)
- **Target SDK:** API 34 (Android 14.0)

---

## 🐛 Solución de Problemas (Troubleshooting)

### **Problema 1: "Gradle sync failed"**

**Solución:**
1. Ve a **"File"** → **"Invalidate Caches"** → **"Invalidate and Restart"**
2. Espera a que Android Studio se reinicie
3. Si persiste, verifica tu conexión a Internet (Gradle descarga dependencias)

### **Problema 2: "SDK not found"**

**Solución:**
1. Ve a **"File"** → **"Project Structure"** → **"SDK Location"**
2. Haz clic en **"Edit"**
3. Descarga **Android SDK 34**
4. Haz clic en **"Apply"**

### **Problema 3: El emulador no arranca**

**Solución:**
1. Verifica que la virtualización esté habilitada en tu BIOS
2. En Windows, desactiva Hyper-V si está activo:
   - Abre PowerShell como Administrador
   - Ejecuta: `bcdedit /set hypervisorlaunchtype off`
   - Reinicia tu PC

### **Problema 4: "Out of Memory" al compilar**

**Solución:**
1. Aumenta la memoria de Gradle
2. Edita `gradle.properties` y añade/modifica:
   ```properties
   org.gradle.jvmargs=-Xmx4096m
   ```
3. Reinicia Android Studio

### **Problema 5: La app se cierra al abrirla**

**Solución:**
1. Ve a **"Run"** → **"Edit Configurations"**
2. Verifica que **"Module"** sea **"app"**
3. Limpia el proyecto: **"Build"** → **"Clean Project"**
4. Reconstruye: **"Build"** → **"Rebuild Project"**

---

## 📚 Recursos Adicionales

### **Documentación Oficial:**
- [Android Developers](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org/docs/home.html)
- [Room Database](https://developer.android.com/training/data-storage/room)

### **Tutoriales Recomendados:**
- [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course)
- [Jetpack Compose Pathway](https://developer.android.com/courses/pathways/compose)

---

## 👥 Equipo de Desarrollo

- **RodDev** - Desarrollador Principal
- **ClaudioDev** - Colaborador
- **GiovanniDev** - Colaborador

---

## 📄 Licencia

Este proyecto es un trabajo académico para la asignatura DSY1105.

---

## 📞 Contacto

Para dudas o problemas con el proyecto:
- **Repositorio:** [GitHub - Aplicacion_Mobile](https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile)
- **Issues:** [Reportar un problema](https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile/issues)

---

## 🎮 ¡Disfruta Level-Up Gamer!

**Versión:** 1.0.0  
**Fecha:** Octubre 2025  
**© 2025 Level-Up Gamer - Todos los derechos reservados**
