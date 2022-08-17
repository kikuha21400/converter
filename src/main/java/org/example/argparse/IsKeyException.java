package org.example.argparse;

/**
 * Throws if we are trying to get value of key argument
 */
public class IsKeyException extends Exception {

    IsKeyException(){
        super("Key argument cannot have value");
    }
}
