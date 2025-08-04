# CREDAC

![CREDAC Logo](logo.jpeg)

CREDAC es un lenguaje de programación personalizado desarrollado con ANTLR4 que permite escribir código que se traduce automáticamente a C++ para su compilación y ejecución.

## 🚀 Características

- Sintaxis intuitiva y fácil de aprender
- Traducción automática a C++
- Compilación directa con GCC
- Análisis léxico y sintáctico robusto con ANTLR4

## 📋 Requisitos Previos

Antes de usar CREDAC, asegúrate de tener instalado:

- **Java** (JDK 21 o superior)
- **ANTLR4** 
- **GCC** (GNU Compiler Collection)
- **Visual Studio Code** (recomendado para desarrollo)

### Instalación de ANTLR4

```bash
# En Ubuntu/Debian
sudo apt-get install antlr4

# En macOS con Homebrew
brew install antlr

# O descargar el JAR directamente
wget https://www.antlr.org/download/antlr-4.x.x-complete.jar
```

## 🛠️ Instalación y Configuración

1. **Clona el repositorio:**
```bash
git clone https://github.com/daciaespinoza/cgcredac.git
cd credac
```

2. **Genera el analizador con ANTLR4:**
```bash
antlr4 AnalizadorParser.g4 -visitor
```

3. **Compila los archivos Java:**
```bash
javac *.java
```

## 🎨 Extensión para Visual Studio Code

Para una mejor experiencia de desarrollo con CREDAC, hemos creado una extensión personalizada para VS Code que incluye resaltado de sintaxis y soporte completo para archivos `.dec`.

### Instalación de la Extensión CREDAC

1. **Descarga la extensión:**
   - Localiza el archivo `credac-language-support-0.0.1.vsix` en el repositorio

2. **Instala en VS Code:**
   - Abre Visual Studio Code
   - Navega hasta la carpeta donde descargaste el archivo `.vsix`
   - Haz clic derecho sobre `credac-language-support-0.0.1.vsix`
   - Selecciona **"Install Extension VSIX"**
   - Reinicia VS Code si es necesario

3. **Verificación:**
   - Abre cualquier archivo `.dec`
   - Deberías ver el resaltado de sintaxis automáticamente

### 🌙 Tema Recomendado

Para una experiencia visual aún mejor, recomendamos instalar el tema **After Dark**:

1. Ve a Extensions (Ctrl+Shift+X)
2. Busca "After Dark"
3. Instala el tema
4. Ve a File > Preferences > Color Theme
5. Selecciona "After Dark"

¡La combinación de nuestra extensión CREDAC con After Dark te dará una experiencia de desarrollo increíble! 🚀

## 💻 Uso del CodeGenerator

### Estructura de Archivos

Tu proyecto debe tener la siguiente estructura:
```
credac/
├── AnalizadorParser.g4    # Gramática de ANTLR4
├── Main.java             # Clase principal
├── *.java               # Archivos generados por ANTLR4
├── ejemplo.dec          # Tu código fuente en CREDAC
└── README.md
```

### Pasos para Ejecutar

1. **Crea tu archivo CREDAC:**
   - El archivo debe tener la extensión `.dec`
   - Por ejemplo: `ejemplo.dec`

2. **Ejecuta el analizador y traductor:**
```bash
java Main
```
Este comando:
- Lee el archivo `ejemplo.dec`
- Analiza la sintaxis usando ANTLR4
- Genera el código C++ correspondiente en `output.cpp`

3. **Compila el código C++ generado:**
```bash
g++ ejemplo.cpp -o programa
```

4. **Ejecuta tu programa:**
```bash
./programa
```

### Comando Completo (Una Línea)

```bash
antlr4 AnalizadorParser.g4 -visitor && javac *.java && java Main && g++ output.cpp -o programa && ./programa
```

## 📝 Ejemplo de Uso

1. **Crear archivo `ejemplo.dec`:**
```credac
comenzar
    crear como entero secreto igual a 7::
    crear como entero intento igual a 0::
    
    mostrar "Adivina el número secreto entre 1 y 10!"::

    hacer {
        mostrar "Ingresa tu intento:"::
        leer intento::

        si (intento menor que secreto) {
            mostrar "Muy bajo. Intenta de nuevo."::
        }
        sino {
            si (intento mayor que secreto) {
                mostrar "Muy alto. Intenta de nuevo."::
            }
        }
    } mientras (intento noes igual a secreto)::

    mostrar "¡Correcto! El número era:", secreto::
terminar
```

2. **Ejecutar la secuencia completa:**
```bash
# Generar analizador
antlr4 AnalizadorParser.g4 -visitor

# Compilar Java
javac *.java

# Traducir CREDAC a C++
java Main

# Compilar C++
g++ output.cpp -o programa

# Ejecutar
./programa
```

## 🔧 Solución de Problemas

### Errores Comunes

- **"No se encuentra ejemplo.dec"**: Asegúrate de que el archivo existe en el directorio actual
- **Errores de compilación Java**: Verifica que ANTLR4 esté correctamente instalado
- **Errores de GCC**: Revisa que el código C++ generado sea válido

## 📚 Documentación

### Sintaxis de CREDAC

```credac
clase Estudiante {
    publico atributo nombre como texto::
    publico atributo nota como decimal::

    publico metodo mostrar_estado() {
        mostrar "Estudiante:", este.nombre::
        si (este.nota mayor o igual que 65) {
            mostrar "Estado: Aprobado"::
        }
        sino {
            mostrar "Estado: Reprobado"::
        }
    }
}

comenzar
    crear como Estudiante e1::

    mostrar "Ingrese el nombre del estudiante:"::
    leer e1.nombre::

    mostrar "Ingrese la nota del estudiante:"::
    leer e1.nota::

    e1.mostrar_estado()::
terminar
```

### Arquitectura

- **AnalizadorParser.g4**: Define la gramática del lenguaje CREDAC
- **Main.java**: Punto de entrada, maneja la traducción
- **Visitor Pattern**: Utilizado para recorrer el AST y generar C++

## ✨ Autores

- **Dacia Espinoza** 
- **Eduardo Oyuela**
- **Cristhian Betancourth**

---