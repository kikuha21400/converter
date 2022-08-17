package org.example.rules;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * <h1>RulesReader.java</h1>
 * <p> Reads rules from multiple files</p>
 * <p> Also can write result rules to file</p>
 */
public class RulesReader {
    Collection<File> rulesFiles;
    Rules rules;

    /**
     * Creates RulesReader instance with filenames to read rules from in future
     * @param fileNames filenames with rules in JSON format
     */
    public RulesReader(String[] fileNames){
        rulesFiles = new HashSet<>();
        for (String name: fileNames){
            rulesFiles.add(new File(name));
        }
    }

    /**
     * Get JSONArrays from files and merge them to one Rules object
     * @throws IOException IO error
     * @throws ParseException Error in JSON format of rules files
     */
    public void processFiles() throws IOException, ParseException {
        rules = new Rules();
        for (File file: rulesFiles) {
            JSONArray rulesObj = (JSONArray) (new JSONParser().parse(new FileReader(file)));

            // append rules to rules object
            rules.importJSON(rulesObj);
        }
    }

    /**
     * Get reference to rules
     * //TODO change to more protected way
     * @return Rules object reference
     */
    public Rules getRules(){
        return rules;
    }

    /**
     * Add filename to files array if needed (cannot overwrite existing file)
     * @param filename name of the file to add
     */
    public void addFile(String filename){
        rulesFiles.add(new File(filename));
    }

    /**
     * Write rules object to file
     * @param filename filename
     * @throws IOException IO error
     */
    public void writeTo(String filename) throws IOException {
        File output = new File(filename);

        try (FileWriter fw = new FileWriter(output, false)){
            // writes JSON array to file by invoking toString() method of Rules
            fw.write(rules.toString());
        }
    }
    
}
