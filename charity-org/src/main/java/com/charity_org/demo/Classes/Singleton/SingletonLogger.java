package com.charity_org.demo.Classes.Singleton;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SingletonLogger {
    private static SingletonLogger instance;
    private PrintWriter fileWriter;
    private final BlockingQueue<String> logQueue;
    private boolean isRunning;

    public enum LogLevel {
        INFO, DEBUG, WARN, ERROR
    }

    public enum FileFormat {
        PLAIN_TEXT, JSON
    }

    private FileFormat currentFormat;

    private SingletonLogger(FileFormat format) {
        this.currentFormat = format;
        this.logQueue = new LinkedBlockingQueue<>();
        this.isRunning = true;

        try {
            fileWriter = new PrintWriter(new FileWriter("app.log", true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (isRunning || !logQueue.isEmpty()) {
                try {
                    String logEntry = logQueue.take();
                    if (fileWriter != null) {
                        fileWriter.println(logEntry);
                    }
                    System.out.println(logEntry);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    public static SingletonLogger getInstance(FileFormat format) {
        if (instance == null) {
            synchronized (SingletonLogger.class) {
                if (instance == null) {
                    instance = new SingletonLogger(format);
                }
            }
        }
        return instance;
    }

    public void log(LogLevel level, String message, Object... args) {
        String formattedMessage = formatMessage(level, message, args);
        logQueue.offer(formattedMessage);
    }

    private String formatMessage(LogLevel level, String message, Object... args) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedMessage = String.format(message.replace("{}", "%s"), args);

        if (currentFormat == FileFormat.JSON) {
            return String.format("{\"timestamp\": \"%s\", \"level\": \"%s\", \"message\": \"%s\"}",
                    timestamp, level.name(), formattedMessage);
        }

        return String.format("[%s] [%s] %s", timestamp, level.name(), formattedMessage);
    }


    public void close() {
        isRunning = false;
        if (fileWriter != null) {
            fileWriter.close();
        }
    }

    public void setFileFormat(FileFormat format) {
        this.currentFormat = format;
    }
}
