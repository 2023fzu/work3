package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 11:54
 **/
public class IdNotSetException extends Exception {
    public IdNotSetException(String message) {
        super(message);
    }

    public IdNotSetException() {
        super();
    }

}
