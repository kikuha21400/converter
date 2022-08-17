package org.example.argparse;

public class IsKeyException extends Exception {

    IsKeyException(){
        super("Key argument cannot have value");
    }
}
