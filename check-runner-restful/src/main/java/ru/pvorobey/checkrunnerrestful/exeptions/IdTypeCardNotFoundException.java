package ru.pvorobey.checkrunnerrestful.exeptions;

public class IdTypeCardNotFoundException extends RuntimeException{

    public IdTypeCardNotFoundException(String message) {
        super(message);
    }
}
