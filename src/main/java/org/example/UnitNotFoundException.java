package org.example;

/**
 * <h1> Unit not found exception</h1>
 * <p> throws if unit does not exist in the system</p>
 */
public class UnitNotFoundException extends Exception{
    final String message;

    UnitNotFoundException(String message){
        this.message = message;

    }

    @Override
    public String toString(){
       return this.message;
    }
}
