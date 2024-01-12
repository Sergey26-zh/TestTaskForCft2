package org.example;


import org.apache.commons.cli.*;
import org.example.file.FileUsage;
import org.example.handler.FileHandler;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String intFileName =  "integers.txt";
    private static final String floatFileName =  "floats.txt";
    private static final String strFileName =  "strings.txt";

    public static void main(String[] args) {

    }

    public static Options setOptions() {
        Options options = new Options();
        options.addOption("o", false, "Set the path to the file");
        options.addOption("p", false, "Set a prefix");
        options.addOption("a", false, "Add to existing file");
        options.addOption("s", false, "Short statistics");
        options.addOption("f", false, "Full statistics");

        return options;
    }

    public static void filter(List<String> list) {
        List<Integer> integerList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        for (String str : list) {
            try {
                integerList.add(Integer.parseInt(str));

            } catch (NumberFormatException e) {
                try {
                    floatList.add(Float.parseFloat(str));
                }
                catch (NumberFormatException e1) {
                    stringList.add(str);
                }
            }
        }

        FileHandler.writeHandler(intFileName, integerList);
        FileHandler.writeHandler(floatFileName, floatList);
        FileHandler.writeHandler(strFileName, stringList);
    }

}