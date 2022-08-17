package org.example.argparse;

public class ArgumentNotFoundException extends Exception{
    public ArgumentNotFoundException(String name){
        super("Argument " + name + " not found");
    }
}
