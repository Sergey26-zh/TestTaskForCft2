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
            List<String> fileNames = cmd.getArgList();
            String outputPath = cmd.getOptionValue("o", ".");
            String prefix = cmd.getOptionValue("p", "");
            boolean append = cmd.hasOption("addExistFile");

            filter(fileNames, append, outputPath, prefix);

        } catch (ParseException e) {
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

    public static void filter(List<String> list, boolean append, String outputPath, String prefix) {
        List<Integer> integerList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        for (String str : list) {
            List<String> read = FileHandler.readHandler(str);
            for (String s : read) {
                try {
                    integerList.add(Integer.parseInt(s));

                } catch (NumberFormatException e) {
                    try {
                        floatList.add(Float.parseFloat(s));
                    } catch (NumberFormatException e1) {
                        stringList.add(s);
                    }
                }
            }
        }

        writeToFile(Paths.get(outputPath, prefix + intFileName), integerList, append);
        writeToFile(Paths.get(outputPath, prefix + floatFileName), floatList, append);
        writeToFile(Paths.get(outputPath, prefix + strFileName), stringList, append);
    }

    private static <T> void writeToFile(Path filePath, List<T> list, boolean append) {
        FileHandler.writeHandler(filePath.toString(), list, append);
    }
}