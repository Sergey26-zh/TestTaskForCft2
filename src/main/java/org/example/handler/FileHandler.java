package org.example.handler;

import org.example.file.FileUsage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class FileHandler {
    public static void readHandler(String fileName) {
        try {
            List<String> list = FileUsage.read(fileName);

            if (list.isEmpty()) {
                System.out.println("File is empty");
            }
        }
        catch (NoSuchFileException e) {
            System.err.println("File not found");
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    public static void writeHandler(String fileName, List<String> list) {
        try {
            FileUsage.write(fileName, list);
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
