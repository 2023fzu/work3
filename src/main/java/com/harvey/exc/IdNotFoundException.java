package com.harvey.exc;

import com.harvey.pojo.Good;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 11:51
 **/
public class IdNotFoundException extends Exception {
    public IdNotFoundException(int id) {
        super("Id " +
                (id != Good.DEFAULT_ID ?
                        id + " not found"
                        :
                        "haven't set"
                )
        );
    }

    public IdNotFoundException() {
        super();
    }
}
