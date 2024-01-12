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

    public static <T> void write(String fileName, List<T> list, boolean append) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        List<String> stringList = convertToStringList(list);
        Files.write(Path.of(fileName), stringList, StandardOpenOption.CREATE, append ? StandardOpenOption.APPEND : StandardOpenOption.WRITE);
        writer.close();
    }

    private static <T> List<String> convertToStringList(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .toList();
    }
}
