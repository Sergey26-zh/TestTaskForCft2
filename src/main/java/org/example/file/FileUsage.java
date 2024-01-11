package org.example.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUsage {
    public static List<String> read(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }

    public static void write(String fileName, List<String> list) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        Files.write(Path.of(fileName), list, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        writer.close();
    }
}
