package org.example.handler;

import org.example.file.FileUsage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class FileHandler {
    public static List<String> readHandler(String fileName) {
        try {
            List<String> list = FileUsage.read(fileName);

            if (list.isEmpty()) {
                System.out.println("File is empty");
            }

            return list;
        }
        catch (NoSuchFileException e) {
            System.err.println("File not found");
            return null;
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
            return null;
        }
    }

    public static <T> void writeHandler(String fileName, List<T> list) {
        try {
            FileUsage.write(fileName, list);
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
