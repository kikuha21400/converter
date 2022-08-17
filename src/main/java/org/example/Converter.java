package org.example;

import org.example.rules.Rules;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * <h1> Converter utility class</h1>
 * <p> Outputs JSONObject from JSONObject input and conversion Rules JSONArray</p>
 * <p> Also performs some preparotary steps, such as converting JSON abstractions to program abstraction
 *  (Rules, Input)</p>
 */
public class Converter {

    private Converter(){
        throw new IllegalStateException ("Can't be instantiated, it is utility class");
    }
    public static JSONObject convertData(JSONObject input, Rules rules) throws Exception {
        // input -> unit_from (string), count (double); u_to (String)

        JSONObject unitFrom = (JSONObject) input.get("distance");

        double value = Double.parseDouble("" + unitFrom.get("value"));
        String uFrom = (String) unitFrom.get("unit");
        String uTo = (String) input.get("convert_to");

        // unit_from, in_to + rules -> out_unit (out_name, out_value)

        Unit outUnit = LenConverter.convert(rules, new Unit(uFrom, value), uTo, value);

        // convert unit to json
        return outUnit.toJSON();
    }

    /**
     * <p>Import result of conversion to string (same as sprintf)</p>
     * @param out JSONObject to output
     * @return JSON string
     */
    public static String printOutput(JSONObject out) {
        String unit = (String) out.get("name");
        Double value = Double.parseDouble("" + out.get("value"));
        return String.format("{\"unit\":\"%s\",\"value\":%.2f}", unit, value);
    }
}
