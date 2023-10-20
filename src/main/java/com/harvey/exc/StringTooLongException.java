package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 23:47
 **/
public class StringTooLongException extends Exception {
    public StringTooLongException() {
        super("String is too long");
    }

    public StringTooLongException(String string){
        super("String "+string+" is too long");
    }
    public StringTooLongException(String string,int limit){
        super("String "+string+" is longer than the limit: "+ limit);
    }
}
