package com.harvey.pojo;

import com.harvey.exc.StockMinusException;
import com.harvey.exc.StringTooLongException;

/*
* @author Harvey Blocks
* */
public class Good {
    private int id;
    private String name;
    private double price;
    private int stock;

    private double formatPrice(double price) {
        String fPrice = String.format("%.2f", price);
        return Double.parseDouble(fPrice);
    }

    private Good() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    private static final int HALF_SPACE = 0x20;
    private static final int FULL_SPACE = 0x3000;
    private static final int HALF_TOTAL = 0x7F;
    private static final int HALF_TO_FULL = 0xFEE0;
    public static final int NAME_FORMAT_LEN = 15;
    private String fullName(){
        char[] fullChar = name.toCharArray();
        for (int i =0; i < fullChar.length; i++) {
            if (fullChar[i] == HALF_SPACE) {
                fullChar[i] = (char) FULL_SPACE;
                continue;
            }
            if (fullChar[i] < HALF_TOTAL) {
                fullChar[i] = (char) (fullChar[i] + HALF_TO_FULL);
            }
        }
        char space = (char) FULL_SPACE;
        StringBuilder spaceStr = new StringBuilder();
        for (int i = 0; i < NAME_FORMAT_LEN - name.length(); i++) {
            spaceStr.append(space);
        }
        return String.valueOf(fullChar)+spaceStr;
    }

    @Override
    public String toString() {
        String formatStr = this.getClass().getSimpleName() +
                "{" +
                "id=" + "%05d" +
                ", name=" + "%s" +
                ", price=" + "%06.2f" +
                ", stock=" + "%04d" +
                '}';
        return String.format(formatStr, id,fullName(), price, stock);
    }

    public static final int NAME_LEN_LIMIT = 15;

    public Good(String name, double price, int stock) throws StringTooLongException, StockMinusException {
        if (name.length() > NAME_LEN_LIMIT) {
            throw new StringTooLongException(name, NAME_LEN_LIMIT);
        }
        if(stock<0){
            throw new StockMinusException(name, stock);
        }
        this.name = name;
        this.price = formatPrice(price);
        this.stock = stock;
        this.id = DEFAULT_ID;
    }

    public static final int DEFAULT_ID = -1;

}
