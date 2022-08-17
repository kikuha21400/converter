package org.example;

import org.example.rules.Rule;
import org.example.rules.Rules;


/**
 * <h1> Length units converter utility class</h1>
 * <p> Uses rules array and information about source and destination units to convert</p>
 */
public class LenConverter {

    /**
     * <p> Utility classes cannot be instantiated</p>
     */
    private LenConverter(){
        throw new IllegalStateException("This is utility class");
    }

    /**
     * <p> Conversion method </p>
     * @param rules rules JSONArray object, describes conversion rules
     * @param uFrom source unit name
     * @param uTo destination unit name
     * @param count count of source units
     * @return unit object, describes destination unit name and value
     * @throws UnitNotFoundException throws if unit does not exist in rules JSONArray
     */
    public static Unit convert(Rules rules, Unit uFrom, String uTo, double count) throws UnitNotFoundException {
        //convert unit names to "rule" abstraction
        Rule fromRule = rules.getByName(uFrom.name);
        Rule toRule = rules.getByName(uTo);

        // if one of the rules does not exist, throw exception
        if (fromRule == null || toRule == null) throw new UnitNotFoundException("Such unit name doesn't exist");

        // evaluate destination unit value
        double value = count * fromRule.getMeters() / toRule.getMeters();

        // return unit object with dest name and value
        return new Unit(uTo, value);
    }
}
