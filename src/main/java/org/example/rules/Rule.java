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
    }

    /**
     * <p> Import rule from JSONObject</p>
     * @param obj source JSONObject
     * @throws ArithmeticException - if unit convert value is 0
     */
    Rule (JSONObject obj) throws ArithmeticException {

        this.meters = (Double.parseDouble("" + obj.get("value")));
        this.name = (String) obj.get("unit_name");

        if (this.meters == 0) throw new ArithmeticException("Unit convert value can't be 0");
    }

    public double getMeters() {
        return this.meters;
    }

    public String getName() {
        return this.name;
    }

    public String toString(){
        return String.format("{\"unit_name\": \"%s\", \"value\": %s }", this.name, "" + this.meters);

    }

    /**
     * <p> Export unit to JSONObject with specification {"unit_name": name, "value": meters}</p>
     * @return result JSONObject
     */
    public JSONObject exportJSON(){
        // creating temporary pointer to add value to object
        JSONObject res = new JSONObject();

        res.put("unit_name", this.name);
        res.put("value", this.meters);

        return res;

    }
}
