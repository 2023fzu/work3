package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 12:39
 **/
public class NameExistException extends Exception {
    public NameExistException(String name) {
        super("Name " + name + " has existed");
    }

    public NameExistException() {
        super();
    }
}