package org.example.utility;

import lombok.*;
import org.apache.commons.cli.Options;
import org.example.handler.FileHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
@Builder
public class UtilityCmd {
    private final FileHandler fileHandler = new FileHandler();

    public Options setOptions() {
        Options options = new Options();

        options.addOption("o", "outputPath", true, "Set the path to the file");
        options.addOption("p", "prefix", true, "Set a prefix");
        options.addOption("a", "addExistFile", false, "Add to existing file");
        options.addOption("s", "shortStat", false, "Short statistics");
        options.addOption("f", "fullStat", false, "Full statistics");

        return options;
    }

    public void filter(List<String> list, boolean append, String outputPath, String prefix, boolean shortStat, boolean fullStat) {

        List<Integer> integerList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();

        for (String str : list) {
            List<String> read = fileHandler.readHandler(str);
            if (read != null) {
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
            } else {
                System.out.println(str + " is null");
            }
        }

        writeToFile(Paths.get(outputPath, prefix + "integers.txt"), integerList, append);
        writeToFile(Paths.get(outputPath, prefix + "floats.txt"), floatList, append);
        writeToFile(Paths.get(outputPath, prefix + "strings.txt"), stringList, append);

        if (shortStat) {
            shortStat(integerList, floatList, stringList);
        } else if (fullStat) {
            fullStat(integerList, floatList, stringList);
        }
    }

    private <T> void writeToFile(Path filePath, List<T> list, boolean append) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeHandler(filePath.toString(), list, append);
    }

    private void shortStat(List<Integer> integerList, List<Float> floatList, List<String> stringList) {
        int intCount = integerList.size();
        int floatCount = floatList.size();
        int stringCount = stringList.size();

        System.out.println("Short Statistics:");
        System.out.println("Integers count: " + intCount);
        System.out.println("Floats count: " + floatCount);
        System.out.println("Strings count: " + stringCount);
        System.out.println();
    }

    private void fullStat(List<Integer> integerList, List<Float> floatList, List<String> stringList) {
        int intCount = integerList.size();
        int floatCount = floatList.size();
        int stringCount = stringList.size();

        int minInt = integerList.stream().mapToInt(Integer::intValue).min().orElse(0);
        int maxInt = integerList.stream().mapToInt(Integer::intValue).max().orElse(0);
        double avgInt = integerList.stream().mapToInt(Integer::intValue).average().orElse(0);
        int sumInt = integerList.stream().mapToInt(Integer::intValue).sum();

        float minFloat = (float) floatList.stream().mapToDouble(Float::floatValue).min().orElse(0);
        float maxFloat = (float) floatList.stream().mapToDouble(Float::floatValue).max().orElse(0);
        double avgFloat = floatList.stream().mapToDouble(Float::floatValue).average().orElse(0);
        double sumFloat = floatList.stream().mapToDouble(Float::floatValue).sum();

        int minStringLength = stringList.stream().mapToInt(String::length).min().orElse(0);
        int maxStringLength = stringList.stream().mapToInt(String::length).max().orElse(0);

        System.out.println("Full Statistics:");
        System.out.println("Integers count: " + intCount);
        System.out.println("Min Integer: " + minInt);
        System.out.println("Max Integer: " + maxInt);
        System.out.println("Average Integer: " + avgInt);
        System.out.println("Sum of Integers: " + sumInt);

        System.out.println("Floats count: " + floatCount);
        System.out.println("Min Float: " + minFloat);
        System.out.println("Max Float: " + maxFloat);
        System.out.println("Average Float: " + avgFloat);
        System.out.println("Sum of Floats: " + sumFloat);

        System.out.println("Strings count: " + stringCount);
        System.out.println("Min String Length: " + minStringLength);
        System.out.println("Max String Length: " + maxStringLength);
        System.out.println();
    }
}
