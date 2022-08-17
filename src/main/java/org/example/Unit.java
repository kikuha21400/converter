package org.example;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Unit{
    double value;
    String name;

    Unit(String name, double value){
        this.value = value;
        this.name = name;
    }

    public double getValue(){
       return value;
    }
    public String getName(){
       return name;
    }

    public JSONObject toJSON(){
        JSONObject outJSON = new JSONObject();
        outJSON.put("name", this.getName());
        outJSON.put("value", this.getValue());
        return outJSON;
    }

}

