📊 Citi Market Monitor: Real-Time Stock Price Visualization

This project was developed as part of a simulation task program for Citi, demonstrating the implementation of concurrency, third-party APIs (simulated), and real-time data visualization using JavaFX.

🎯 Project Goal

The main objective is to create a low-technical-knowledge tool that allows Citi employees to monitor the price of a key stock index (the Dow Jones Industrial Average, (DJI) in real-time.

The project fulfills two essential functions:

Concurrency: Query the stock price at a fixed interval (5 seconds) without blocking the application's main thread, using the ScheduledExecutorService.

Visualization: Display historical and updated data on a dynamic line chart, facilitating real-time risk monitoring.

🏗️ Architecture and Technologies

The project is built on the Java platform and utilizes the following technologies:

Language: Java

Build System: Gradle

Visualization (UI): JavaFX

Concurrency: ScheduledExecutorService (for background tasks)

Data API: yahoofinance-api (Data simulation is used to avoid rate limiting blocks, HTTP 429).

Key Component Structure

Component

Function

Key Technology

Main.java

Core Logic, UI Initialization, and Concurrent Execution.

JavaFX Application

dataFetcher

Task that queries (simulates) the price and stores it.

ScheduledExecutorService

Chart

Displays price vs. time.

JavaFX LineChart

build.gradle

Manages dependencies and JavaFX modules.

Gradle

🚀 Installation and Execution Guide

Follow these steps to build and run the stock monitor.

Prerequisites

JDK 17 or higher

Gradle (Generally included in IDEs like IntelliJ IDEA or VS Code)

Steps to Run

Clone the Repository:

git clone [https://aws.amazon.com/es/what-is/repo/](https://aws.amazon.com/es/what-is/repo/)
cd CitiMarketMonitor


Synchronize Dependencies:
Ensure IntelliJ or your IDE has synchronized dependencies after opening the project, or run:

gradle clean
gradle build


Execute the Application:
Run the project using the Gradle run task. This will launch the JavaFX window.

gradle run


Expected Behavior

A pop-up window titled "Citi Market Monitor - DJIA Real-Time Price" will appear. The line chart will automatically update every 5 seconds with a new simulated price point, displaying real-time monitoring. The terminal console will also log each data point added to the queue.

🛠️ Development Notes

Data Simulation: To avoid the persistent HTTP 429 (Too Many Requests) errors imposed by the Yahoo Finance server, the original API call was replaced with a random price generator (BigDecimal) within the same thread. This ensures that the real-time data visualization component functions without interruption, fulfilling the functional requirement of the task.

UI Threads: The chart update logic uses Platform.runLater() to ensure that user interface changes are safely executed from the ScheduledExecutorService thread.

