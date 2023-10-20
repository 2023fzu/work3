package com.harvey.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * create table bill(
 *     id int(8) comment '主键,账单号' primary key auto_increment,
 *     good_id int comment '商品id' not null,
 *     constraint fk_bill_good_id foreign key(good_id) references good(id) ,
 *     customer_id int(4) comment '用户id' not null ,
 *     price   double comment '订单价格,实际交易金额,包括单价*数量,折扣优惠等一系列因素',
 *     bill_date time comment '订单成交时间,年月日时分秒'
 * ) comment '账单表';
 * @author HarveyBlocks
 * @date 2023/10/18 17:11
 **/
public class Bill {
    private int id           ;
    private int goodId      ;
    private String goodName ;
    private int customerId  ;
    private double price        ;



    private Date billDate    ;
    private double formatPrice(double price){
        String fPrice = String.format("%.2f", price);
        return  Double.parseDouble(fPrice);
    }
    private String formatDate(Date billDate)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //格式化date
        Date date = new Date();
        return sdf.format(date);
    }

    public String getGoodName() {
        return goodName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getBillDate() {
        return billDate;
    }


    private Bill() {
    }

    public static final int DEFAULT_ID = -1;

    public Bill(int goodId, int customerId,double price)  {
        this.goodId = goodId;
        this.customerId = customerId;
        this.price = formatPrice(price);
        this.id = DEFAULT_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bill)) {
            return false;
        }
        Bill bill = (Bill) o;
        return id == bill.id && goodId == bill.goodId  && customerId == bill.customerId && Double.compare(price, bill.price) == 0 && Objects.equals(billDate, bill.billDate);
    }


    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", customerId=" + customerId +
                ", price=" + price +
                ", billDate=" + formatDate(billDate) +
                '}';
    }


    public String toStrWithGoodName(){
        String formatStr = this.getClass().getSimpleName() +
                "{" +
                "id=" + "%05d" +
                ", goodName=" + "%s" +
                ", customerId=" +"%05d" +
                ", price=" + "%06.2f" +
                ", billDate=" + "%s"+
                '}';
        return String.format(formatStr, id, fullName(), customerId, price, formatDate(billDate));
    }

    private static final int HALF_SPACE = 0x20;
    private static final int FULL_SPACE = 0x3000;
    private static final int HALF_TOTAL = 0x7F;
    private static final int HALF_TO_FULL = 0xFEE0;
    private String fullName(){
        char[] fullChar = goodName.toCharArray();
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
        for (int i = 0; i < Good.NAME_FORMAT_LEN- goodName.length(); i++) {
            spaceStr.append(space);
        }
        return String.valueOf(fullChar)+spaceStr;
    }
    public static String listToString(List<Bill> bills){
        StringBuilder strList = new StringBuilder(
                "Bill List\n" +
                "ID\t\t\t\tGood Name\t\t\tCustomer ID\t\tPrice\t\tBill Date\n");
        String  formatStr =
                "%05d\t%s\t\t%05d\t\t%06.2f\t%s\n";
        for (Bill bill : bills){
            strList.append(String.format(
                    formatStr,
                    bill.id,
                    bill.fullName(),
                    bill.customerId,
                    bill.price,
                    bill.formatDate(bill.billDate)
            ));
        }
        return strList.toString();
    }
}
