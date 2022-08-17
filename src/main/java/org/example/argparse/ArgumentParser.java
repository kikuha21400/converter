package org.example.argparse;

import java.util.Collection;
import java.util.HashSet;

public class ArgumentParser {
    String helpMessage;
    String contextHelp;
    Collection<Argument> arguments;

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

    public Collection<Argument> getArguments(){
        return arguments;
    }

    public void addArgument(String name, String value){
       arguments.add(new Argument(name, value));
    }

    public void addArgument(String name){
       arguments.add(new Argument(name));
    }

    public int getLength(){
        return arguments.size();
    }

    public void processArguments(String[] args) throws InvalidArgumentException {
        boolean canValue = false;
        String argName = "";
        String value;
        arguments = new HashSet<>();

        for (String arg: args){

            if (arg.charAt(0) == '-'){

                if (!argName.equals("") && canValue) {
                    this.addArgument(argName);
                }

                argName = arg;
                canValue = true;

            } else{

                if (canValue){

                    value = arg;
                    canValue = false;
                    this.addArgument(argName, value);
                    argName = "";

                } else {
                    throw new InvalidArgumentException(arg);
                }
            }

        }

        if (!argName.isEmpty()){
            this.addArgument(argName);
        }
    }

    public boolean haveArgument(String name){
       for (Argument argument: arguments) {
          if (argument.getName().equals(name)){
              return true;
          }
       }
       return false;
    }

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
