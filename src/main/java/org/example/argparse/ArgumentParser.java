package org.example.argparse;

import java.util.Collection;
import java.util.HashSet;

/**
 * ArgumentParser.java <div>
 * Describes Argument Parser class, that parses command-line arguments
 */
public class ArgumentParser {
    String helpMessage;
    String contextHelp;
    Collection<Argument> arguments;

    /**
     * Creates arguments parser
     * @param contextHelp prints on printContextHelp()
     * @param helpMessage prints on printHelp()
     */
    public ArgumentParser(String contextHelp, String helpMessage){
       this.contextHelp = contextHelp;
       this.helpMessage = helpMessage;
    }

    /**
     * Prints error on -h argument
     */
    public void printHelp() {
        System.err.println(this.helpMessage);
    }

    /**
     * <p> Prints help, if some error in parsing command-line arguments occurred</p>
     */
    public void printContextHelp() {
        System.err.println(contextHelp);
    }

    /**
     * Adds non-key argument to arguments collection
     * @param name argument's name
     * @param value argument's value
     */
    public void addArgument(String name, String value){
       arguments.add(new Argument(name, value));
    }

    /**
     * Adds key argument to arguments collection
     * @param name argument's name
     */
    public void addArgument(String name){
       arguments.add(new Argument(name));
    }

    /**
     * Get count of arguments
     * @return count
     */
    public int getLength(){
        return arguments.size();
    }

    /**
     * Processes args from main() method of program or from other String arrays
     * <p> Available constructions: </p>
     * <p> -name - key argument</p>
     * <p> -name value - non-key argument</p>
     * <p> can't process -name1name2... arguments </p>
     * <p> processes always first values of arguments:</p>
     * <p> -p 1000 -p 32132 : value of p is 1000 </p>
     * @param args String[] args from main
     * @throws InvalidArgumentException throws if there is construction like -p 1000 1000
     */
    public void processArguments(String[] args) throws InvalidArgumentException {
        boolean canValue = false;
        String argName = "";
        String value;

        // for now, we are using hashset to store arguments to exclude duplicate arguments
        arguments = new HashSet<>();

        for (String arg: args){

            // if we got the name of argument (- at the beginning),
            // we can get value next or the name of the next argument
            if (arg.charAt(0) == '-'){

                // if we got construction like -p -l ..., we need to add -p as a key argument and process -l
                if (!argName.equals("")) {
                    this.addArgument(argName);
                }

                // by default, we store name of the argument to temp variable
                argName = arg;
                canValue = true;

            } else{

                if (canValue){

                    // here we are adding non-key argument and clearing argName variable
                    value = arg;
                    canValue = false;
                    this.addArgument(argName, value);
                    argName = "";

                } else {
                    // throw on, for example, "-p 1000 1000" or "1000" at the beginning
                    throw new InvalidArgumentException(arg);
                }
            }

        }

        // add last key argument, if there is such argument in the array
        if (!argName.isEmpty()){
            this.addArgument(argName);
        }
    }

    /**
     * Checks, if there is argument in list with such name
     * @param name name of argument to search
     * @return - true if argument with such name has been found
     */
    public boolean haveArgument(String name){
       for (Argument argument: arguments) {
          if (argument.getName().equals(name)){
              return true;
          }
       }
       return false;
    }

    /**
     * Gets the value of non-key argument by name
     * Needs to check argument name by haveArgument() to prevent ArgumentNotFoundException
     * @param name name of the argument to search
     * @return value of argument
     * @throws IsKeyException if argument is a key argument
     * @throws ArgumentNotFoundException argument not found in set
     */
    public String getValue(String name) throws IsKeyException, ArgumentNotFoundException {
        if (haveArgument(name)) {
            for (Argument argument: arguments){
                if (argument.getName().equals(name)){
                    return argument.getValue();
                }
            }
        }

        throw new ArgumentNotFoundException(name);

    }


}
