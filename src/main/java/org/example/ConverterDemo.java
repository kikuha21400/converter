package org.example;

import org.example.argparse.ArgumentParser;
import org.example.argparse.IsKeyException;
import org.example.rules.Rules;
import org.example.rules.RulesReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * <h1>Demonstration class</h1>
 * <p> Shows, how converter work and parses command-line arguments</p>
 */
public class ConverterDemo {

    static String helpMessage = """
                Usage: java -jar converter.jar -i input.json [-r rules.json -a -c default.json]
                Converts length units from one to another. Input and output files are
                in JSON format. Program reading rules from default.json file by default.
                Examples you can check in build directory.
                Parameters:
                -r              uses rules.json file as an additional file with convert rules
                -i              input file
                -a              append default rules file with rules from rules.json
                -c              specify different default rules file to use
                                
                Full documentation at https://github.com/kikuha21400/converter
                P.S. converter.jar - executable name, it can differ from it""";
    static String contextMessage = """
                Usage: java -jar converter.jar -i input.json [-r rules.json -a -c default.json]
                Enter java -jar converter.jar -h for more info
                P.S. converter.jar - executable name, it can differ""" ;

    /**
     * <p> Parses command-line arguments, manages Converter class</p>
     * @param args command-line arguments
     * @throws Exception - IOException and etc.
     */
    public static void main(String[] args) throws Exception {
        ArgumentParser argParser = new ArgumentParser(contextMessage, helpMessage);

        argParser.processArguments(args);

        if (argParser.haveArgument("-h")){
            if (argParser.getLength() == 1) {
                argParser.printHelp();
                return;
            } else {
                argParser.printContextHelp();
                return;
            }
        }

        if (argParser.haveArgument("-i")){

            JSONObject inputData = null;
            Rules convertRules;

            String defaultRules = "default.json";
            RulesReader rulesReader = new RulesReader(new String[] {defaultRules});

            try {
                inputData = (JSONObject) (new JSONParser().parse(new FileReader(argParser.getValue("-i"))));
            } catch (IsKeyException e){
                argParser.printContextHelp();
                return;
            }


            if (argParser.haveArgument("-c")){
                try {
                    defaultRules = argParser.getValue("-c");
                } catch (IsKeyException e){
                    argParser.printContextHelp();
                    return;
                }

            }

            if (argParser.haveArgument("-r")) {

                try{
                    rulesReader.addFile(argParser.getValue("-r"));
                } catch (IsKeyException e){
                    argParser.printContextHelp();
                    return;
                }

            }


            rulesReader.processFiles();
            convertRules = rulesReader.getRules();

            if (argParser.haveArgument("-a")){
                rulesReader.writeTo(defaultRules);
            }

            JSONObject outData = Converter.convertData(inputData, convertRules);
            System.out.println(Converter.printOutput(outData));
        }

    }
}
