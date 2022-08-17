package org.example.argparse;

/**
 * Throws on invalid argument names and values
 */
public class InvalidArgumentException extends Exception{
    InvalidArgumentException(String value){
        super ("Argument value cannot be" + value);
    }

}
