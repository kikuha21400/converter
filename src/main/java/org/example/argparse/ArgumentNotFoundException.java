package org.example.argparse;

/**
 * ArgumentNotFound.java
 * throws if argument not found in arguments collection (see ArgumentParser.java)
 */
public class ArgumentNotFoundException extends Exception{
    public ArgumentNotFoundException(String name){
        super("Argument " + name + " not found");
    }
}
