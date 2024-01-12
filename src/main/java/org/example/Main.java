package org.example;


import org.apache.commons.cli.*;
import org.example.file.FileUsage;
import org.example.handler.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static String intFileName = "integers.txt";
    private static String floatFileName = "floats.txt";
    private static String strFileName = "strings.txt";

    public static void main(String[] args) {
        Options options = setOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);

            String[] fileNames = cmd.getArgs();
            List<String> list = List.of(fileNames);

            String outputPath = cmd.getOptionValue("o", "");
            String prefix = cmd.getOptionValue("p", "");
            boolean app = cmd.hasOption("a");


            for (String fileName : fileNames) {
                Path newPath = getOutputFilePath(outputPath, fileName);
                Files.move(Paths.get(fileName), newPath, StandardCopyOption.REPLACE_EXISTING);
            }

            for (String fileName : fileNames) {
                Path newName = getFilePrefix(outputPath, fileName);
                Files.move(Paths.get(fileName), newName, StandardCopyOption.REPLACE_EXISTING);
            }

            filter(list, app);

        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Options setOptions() {
        Options options = new Options();
        options.addOption("o", "outputPath", true, "Set the path to the file");
        options.addOption("p", "prefix", true, "Set a prefix");
        options.addOption("a", "addExistFile", false, "Add to existing file");
        options.addOption("s", "shortStat", false, "Short statistics");
        options.addOption("f", "fullStat", false, "Full statistics");

        return options;
    }

    public static void filter(List<String> list, boolean append) {
        List<Integer> integerList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        for (String str : list) {
            try {
                integerList.add(Integer.parseInt(str));

            } catch (NumberFormatException e) {
                try {
                    floatList.add(Float.parseFloat(str));
                } catch (NumberFormatException e1) {
                    stringList.add(str);
                }
            }
        }

        FileHandler.writeHandler(intFileName, integerList, append);
        FileHandler.writeHandler(floatFileName, floatList, append);
        FileHandler.writeHandler(strFileName, stringList, append);
    }

    private static Path getOutputFilePath(String outputPath, String fileName) {
        return Path.of(outputPath, fileName);
    }

    private static Path getFilePrefix(String prefix, String fileName) {
        String fullName = prefix + fileName;
        return Path.of(fullName);
    }
}