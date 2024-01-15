package org.example;


import org.apache.commons.cli.*;
import org.example.utility.UtilityCmd;

import java.util.List;

public class Main {
    private static final UtilityCmd utilityCmd = new UtilityCmd();

    public static void main(String[] args) {
        Options options = utilityCmd.setOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            List<String> fileNames = cmd.getArgList();
            String outputPath = cmd.getOptionValue("o", ".");
            String prefix = cmd.getOptionValue("p", "");
            boolean append = cmd.hasOption("a");
            boolean shortStat = cmd.hasOption("s");
            boolean fullStat = cmd.hasOption("f");

            utilityCmd.filter(fileNames, append, outputPath, prefix, shortStat, fullStat);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


