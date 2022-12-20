package ru.pvorobey.checkrunnerrestful.exeptions;


public class IdTypeProductNotFoundException extends RuntimeException{

    public IdTypeProductNotFoundException(String message) {
        super(message);
    }
}
