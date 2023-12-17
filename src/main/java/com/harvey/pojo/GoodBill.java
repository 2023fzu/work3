package com.harvey.pojo;

/**
 * TODO
 *
 * @author @author <a href="mailto:harvey.blocks@outlook.com">Harvey Blocks</a>
 * @version 1.0
 * @className GoodBill
 * @date 2023-12-14 19:30
 */
public class GoodBill {
    public GoodBill(int billId, int goodId, int count) {
        this.billId = billId;
        this.goodId = goodId;
        this.count = count;
    }

    int billId;
    int goodId;
    int count;
}
