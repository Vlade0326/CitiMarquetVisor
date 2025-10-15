package org.example; 
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Aplicación de Monitoreo de Mercado para Citi:
 * Consulta el precio del Promedio Industrial Dow Jones (DJIA)
 * y almacena el valor junto con la marca de tiempo en una cola segura para hilos.
 */
public class App {

    // Símbolo de cotización para el Promedio Industrial Dow Jones
    private static final String DJIA_TICKER = "^DJI";

    // Cola thread-safe (ConcurrentLinkedQueue) para almacenar los datos
    private static final Queue<String> marketDataQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        // Mensaje actualizado para reflejar la frecuencia real
        System.out.println("Citi Market Monitor iniciado. Consultando DJIA cada 60 segundos...");

        // Servicio de ejecución programada: crucial para tareas repetitivas
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Tarea (Runnable) que contiene la lógica de consulta y almacenamiento
        Runnable dataFetcher = () -> {
            try {
                // 1. CONSULTA: Obtener el objeto Stock del API de Yahoo Finance
                Stock dowJones = YahooFinance.get(DJIA_TICKER);

                // 2. EXTRACCIÓN: Usar BigDecimal para máxima precisión
                BigDecimal price = dowJones.getQuote().getPrice();
                Date timestamp = new Date();

                // 3. FORMATEO: Crear una entrada de datos legible
                String dataEntry = String.format("Timestamp: %s | Ticker: %s | Price: %s",
                                                timestamp,
                                                DJIA_TICKER,
                                                price.toPlainString());

                // 4. ALMACENAMIENTO: Añadir el dato a la cola de forma segura (FIFO)
                marketDataQueue.offer(dataEntry);

                // LOG: Confirmación de que el dato fue añadido
                System.out.println("Dato añadido a la cola: " + dataEntry);
                
                // Opcional: Para ver el tamaño de la cola
                // System.out.println("Tamaño de la cola: " + marketDataQueue.size());

            } catch (IOException e) {
                // Manejo de errores de red o API (incluido el error 429 si persiste)
                System.err.println("Error de red/API al obtener datos: " + e.getMessage());
            } catch (Exception e) {
                 // Capturar errores de datos nulos o inesperados
                 System.err.println("Error inesperado en la tarea: " + e.getMessage());
            }
        };

        // 5. PROGRAMACIÓN: Ejecutar la tarea. 
        // Parámetros: (tarea, retraso inicial, periodo de repetición, unidad de tiempo)
        // Usamos un retraso inicial de 60 segundos y un período de 60 segundos para evitar el bloqueo 429.
        scheduler.scheduleAtFixedRate(dataFetcher, 60, 60, TimeUnit.SECONDS);
    }
}
