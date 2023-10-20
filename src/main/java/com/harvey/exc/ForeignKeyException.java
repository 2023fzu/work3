package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 21:36
 **/
public class ForeignKeyException extends Exception{
    public ForeignKeyException(){super("Foreign Connect Failed");}
    public ForeignKeyException(int id) {
        super("Bill connect with "+ id);
    }

}
