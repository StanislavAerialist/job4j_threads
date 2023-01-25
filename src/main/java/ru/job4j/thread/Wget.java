package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String[] name = url.split("/");
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(name[name.length - 1])) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = currentTimeMillis();
            int downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long procedure = currentTimeMillis() - start;
                    if (procedure < 1000) {
                        Thread.sleep(1000 - procedure);
                    }
                    downloadData = 0;
                    start = currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Недостаточное количество аргументов");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}