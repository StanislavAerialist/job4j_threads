package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String content(Predicate<Character> filter) throws IOException {
        String output = "";
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        }
        return output;
    }

    public synchronized String getContent() throws IOException {
        Predicate<Character> filter = data -> true;
        return content(filter);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        Predicate<Character> filter = data -> data < 0x80;
        return content(filter);
    }
}