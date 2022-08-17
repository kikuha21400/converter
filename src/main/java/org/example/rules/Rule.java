package org.example.rules;

import org.json.simple.JSONObject;

/**
 * <h1> Rules class</h1>
 * <p> Describes one rule, that has loaded from custom source</p>
 */
public class Rule {
    double meters;
    String name;

    /**
     * <p> Object represents name of the conversion unit and it's value in meters</p>
     * @param meters how much unit costs in meters
     * @param name name of the unit
     */
    Rule(double meters, String name) {
        this.meters = meters;
        this.name = name;

        if (this.meters == 0) throw new ArithmeticException("Unit convert value can't be 0");
    }

    /**
     * <p> Import rule from JSONObject</p>
     * @param obj source JSONObject
     * @throws ArithmeticException - if unit convert value is 0
     */
    Rule (JSONObject obj) throws ArithmeticException {
        this(Double.parseDouble("" + obj.get("value")), (String) obj.get("unit_name"));
    }

    public double getMeters() {
        return this.meters;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Convert rule to string in format "{"unit_name": "name", "value": value}"
     * @return result string
     */
    public String toString(){
        return String.format("{\"unit_name\": \"%s\", \"value\": %s }", this.name, "" + this.meters);

    }
}
