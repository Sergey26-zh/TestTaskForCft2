package org.example.handler;

import org.example.file.FileUsage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class FileHandler {
    FileUsage fileUsage = new FileUsage();
    public List<String> readHandler(String fileName) {
        try {

            List<String> list = fileUsage.read(fileName);

            if (list.isEmpty()) {
                System.out.println(fileName + " is empty");
            }
            return list;
        }
        catch (NoSuchFileException e) {
            System.err.println(fileName + " File not found");
            return null;
        }
        catch (IOException e) {
            System.err.println(fileName + " Error " + e.getMessage());
            return null;
        }
    }

    public <T> void writeHandler(String fileName, List<T> list, boolean append) {
        try {
            fileUsage.write(fileName, list, append);
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
