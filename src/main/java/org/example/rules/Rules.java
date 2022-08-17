package org.example.rules;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.HashSet;

/**
 * <h1> Rules array class</h1>
 * <p> Can import Rules array from JSONArray instance</p>
 * <p> Also can search in system for exact Rule object by name</p>
 */
public class Rules {
    Collection<Rule> data;

    Rules() {
        this.data = new HashSet<>();
    }

    /**
     * <p> Sequentially adds Rule object to array from JSONArray</p>
     * @param input source JSONArray
     */
    public void importJSON(JSONArray input) {
        for (Object obj : input) {
            data.add(new Rule((JSONObject) obj));
        }

    }

    public String toString(){
        return data.toString();
    }

    /**
     * <p> Implements search in Rules array</p>
     * @param unit String unit name to search for
     * @return result unit object or null if it does not exist
     */
    public Rule getByName(String unit) {

        for (Rule rule : this.data) {
            if (rule.getName().equals(unit)) return rule;
        }

        return null;
    }
}
