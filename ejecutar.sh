#!/bin/bash

# Colores
RED='\033[0;31m'
GREEN='\033[0;32m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

echo "=========================================="
echo -e "${CYAN}Iniciando compilación y ejecución...${NC}"
echo "=========================================="

# Paso 1: Ejecutar el generador de código Java
echo -e "${CYAN}Generando código C++...${NC}"
java Main

if [ $? -ne 0 ]; then
    echo -e "${RED}ERROR: Falló la generación de código Java.${NC}"
    exit 1
fi

# Paso 2: Compilar el código C++ generado
echo -e "${CYAN}Compilando código C++...${NC}"
g++ salida.cpp -o programa

if [ $? -ne 0 ]; then
    echo -e "${RED}ERROR: Falló la compilación de C++.${NC}"
    exit 1
fi

# Paso 3: Ejecutar el programa
echo -e "${CYAN}Ejecutando programa...${NC}"
./programa

echo "=========================================="
echo -e "${GREEN}Proceso completado exitosamente.${NC}"
echo "=========================================="
