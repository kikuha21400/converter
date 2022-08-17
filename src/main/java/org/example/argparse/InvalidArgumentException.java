package org.example.argparse;

public class InvalidArgumentException extends Exception{
    InvalidArgumentException(String value){
        super ("Argument value cannot be" + value);
    }

}
