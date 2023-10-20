package com.harvey.exc;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 16:38
 **/
public class LowLargerThanHighException extends Exception {
    public LowLargerThanHighException() {
        super("Low Larger Than High");
    }

    public LowLargerThanHighException(double low, double high) {
        super("Low " + low + " Larger Than High " + high);
    }
}
