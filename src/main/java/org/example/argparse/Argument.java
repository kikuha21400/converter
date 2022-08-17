package org.example.argparse;

public class Argument {
    private String name;
    private String value;
    private boolean isKey;

    Argument(String name, String value){

        this.name = name;
        this.value = value;
        this.isKey = false;
    }

    Argument(String name){
        this.name = name;
        isKey = true;
        this.value = null;
    }

    public boolean getIsKey(){
        return isKey;
    }

    public String getName(){
        return name;
    }

    public String getValue() throws IsKeyException {
        if (!isKey){
            return value;
        } else {
            throw new IsKeyException();
        }
    }
}
