package org.example;

import org.json.simple.*;

/**
 * Unit Class to store info about converting units
 */
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

    /**
     * Creates JSONObject from Unit
     * Purpose: output of program
     * @return JSONObject, equivalent to unit in format {"name": name, "value": value}
     */
    public JSONObject toJSON(){
        JSONObject outJSON = new JSONObject();
        outJSON.put("name", this.getName());
        outJSON.put("value", this.getValue());
        return outJSON;
    }

}

