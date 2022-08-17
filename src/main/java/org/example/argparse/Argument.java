package org.example.argparse;

/**
 * Argument class
 * has name, value, if is not key argument and isKey flag
 */
public class Argument {
    private final String name;
    private final String value;
    private final boolean isKey;

    /**
     * Argument constructor for non-key arguments
     * isKey is false
     * @param name name of the argument to create
     * @param value value of argument
     */
    Argument(String name, String value){

        this.name = name;
        this.value = value;
        this.isKey = false;
    }

    /**
     * For key arguments, have only name
     * @param name name of argument
     */
    Argument(String name){
        this.name = name;
        isKey = true;
        this.value = null;
    }

    /**
     * Is key argument getter
     * @return the value of isKey variable
     */
    public boolean getIsKey(){
        return isKey;
    }

    /**
     * Gets name of argument, non-key or key
     * @return name member
     */
    public String getName(){
        return name;
    }

    /**
     * Get value of non-key argument
     * @return value member
     * @throws IsKeyException for key arguments
     */
    public String getValue() throws IsKeyException {
        if (!getIsKey()){
            return value;
        } else {
            throw new IsKeyException();
        }
    }
}
