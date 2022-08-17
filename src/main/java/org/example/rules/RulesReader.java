package org.example.rules;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashSet;

public class RulesReader {
    HashSet<File> rulesFiles;
    Rules rules;


    public RulesReader(String[] fileNames){
        rulesFiles = new HashSet<>();
        for (String name: fileNames){
            rulesFiles.add(new File(name));
        }
    }

    public void processFiles() throws IOException, ParseException {
        rules = new Rules();
        for (File file: rulesFiles) {
            JSONArray rulesObj = (JSONArray) (new JSONParser().parse(new FileReader(file)));
            rules.importJSON(rulesObj);
        }
    }

    public Rules getRules(){
        return rules;
    }

    public void addFile(String filename){
        rulesFiles.add(new File(filename));
    }

    public void writeTo(String filename) throws IOException {
        File output = new File(filename);

        try (FileWriter fw = new FileWriter(output, false)){
            fw.write(rules.toString());
        }
    }
    
}
