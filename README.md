📊 Citi Market Monitor: Visualización de Precios Bursátiles en Tiempo Real
Este proyecto fue desarrollado como parte de un programa de simulación de tareas para Citi, demostrando la implementación de concurrencia, APIs de terceros (simuladas) y visualización de datos en tiempo real mediante JavaFX.

🎯 Objetivo del Proyecto
El objetivo principal es crear una herramienta de bajo conocimiento técnico que permita a los empleados de Citi monitorear el precio de un índice bursátil clave (el Promedio Industrial Dow Jones, ^DJI) en tiempo real.

El proyecto cumple dos funciones esenciales:

Concurrencia: Consultar el precio de las acciones en un intervalo fijo (5 segundos) sin bloquear el hilo de la aplicación principal, utilizando ScheduledExecutorService.

Visualización: Mostrar los datos históricos y actualizados en un gráfico de líneas dinámico, facilitando la monitorización del riesgo.

🏗️ Arquitectura y Tecnologías
El proyecto está construido sobre la plataforma Java y utiliza las siguientes tecnologías:

Lenguaje: Java

Sistema de Construcción: Gradle

Visualización (UI): JavaFX

Concurrencia: ScheduledExecutorService (para tareas en segundo plano)

API de Datos: yahoofinance-api (Se usa simulación de datos para evitar bloqueos por límites de tasa, HTTP 429).

Estructura de Componentes Clave
Componente

Función

Tecnología Clave

Main.java

Lógica Principal, Inicialización de UI y Ejecución Concurrente.

JavaFX Application

dataFetcher

Tarea que consulta (simula) el precio y lo almacena.

ScheduledExecutorService

Gráfico

Muestra el precio vs. el tiempo.

JavaFX LineChart

build.gradle

Gestión de dependencias y módulos JavaFX.

Gradle

🚀 Guía de Instalación y Ejecución
Sigue estos pasos para construir y ejecutar el monitor bursátil.

Requisitos Previos
JDK 17 o superior

Gradle (Generalmente incluido en IDEs como IntelliJ IDEA o VS Code)

Pasos para Ejecutar
Clonar el Repositorio:

git clone [https://aws.amazon.com/es/what-is/repo/](https://aws.amazon.com/es/what-is/repo/)
cd CitiMarketMonitor

Sincronizar Dependencias:
Asegúrate de que IntelliJ o tu IDE haya sincronizado las dependencias después de abrir el proyecto, o ejecuta:

gradle clean
gradle build

Ejecutar la Aplicación:
Ejecuta el proyecto utilizando la tarea run de Gradle. Esto lanzará la ventana de JavaFX.

gradle run

Comportamiento Esperado
Una ventana emergente con el título "Citi Market Monitor - DJIA Real-Time Price" aparecerá. El gráfico de líneas se actualizará automáticamente cada 5 segundos con un nuevo punto de precio simulado, mostrando el monitoreo en tiempo real. La consola de la terminal también registrará cada punto de dato añadido a la cola.

🛠️ Notas de Desarrollo
Simulación de Datos: Para evitar los errores persistentes de HTTP 429 (Too Many Requests) impuestos por el servidor de Yahoo Finance, la consulta de la API original fue reemplazada por un generador de precios aleatorios (BigDecimal) dentro del mismo hilo. Esto garantiza que el componente de visualización de datos en tiempo real funcione sin interrupciones, cumpliendo el requisito funcional de la tarea.

Hilos de UI: La lógica de actualización del gráfico usa Platform.runLater() para garantizar que los cambios de la interfaz de usuario se realicen de forma segura desde el hilo del ScheduledExecutorService.
