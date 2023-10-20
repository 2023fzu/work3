package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 13:29
 **/
public class StockMinusException extends Exception {
    public StockMinusException() {
        super();
    }
    public StockMinusException(String name,int stock){
        super(name+"'s stock "+stock+" can not be minus");
    }
    public StockMinusException(int stock){
        super("stock "+stock+" can not be minus");
    }
}
