package com.harvey.pojo;

import java.text.SimpleDateFormat;
import java.util.List;

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
    public int getId() {
        return id;
    }

    public Bill() {

    }
    public Bill(int id, int customerId, java.sql.Timestamp billDate) {
        this.customerId = customerId;
        this.billDate = billDate;
        this.id = id;
    }

    public Bill(int customerId, java.sql.Timestamp billDate) {
        this.customerId = customerId;
        this.billDate = billDate;

    }

    private int id ;

    private List<Good> goods;
    private int count;
    private int customerId  ;

    // 一次订单的总价
    private double price;

    private java.sql.Timestamp billDate    ;
    private String formatDate(String billDate)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //格式化date
        return sdf.format(billDate);
    }


    public String goodsStrFormat(){
        StringBuilder str = new StringBuilder();
        for (Good good:goods){
            str.append(good.toString()).append('\n');
        }
        return String.valueOf(str);
    }

    public String strFormat(){
        String formatStr = this.getClass().getSimpleName() +
                "{" +
                "id=" + "%05d" +
                ", count=" + "%5d" +
                ", customerId=" +"%05d" +
                ", price=" + "%06.2f" +
                ", billDate=" + "%s"+
                '}';
        return String.format(formatStr, id,count, customerId, price,billDate);

    }

    private static final int HALF_SPACE = 0x20;
    private static final int FULL_SPACE = 0x3000;
    private static final int HALF_TOTAL = 0x7F;
    private static final int HALF_TO_FULL = 0xFEE0;
    private String fullName(String string){
        char[] fullChar = string.toCharArray();
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
        for (int i = 0; i < Good.NAME_FORMAT_LEN- string.length(); i++) {
            spaceStr.append(space);
        }
        return String.valueOf(fullChar)+spaceStr;
    }
    public static String listToString(List<Bill> bills){
        StringBuilder strList = new StringBuilder(
                "Bill List\n" +
                "ID\t\t\t\tcount\t\t\tCustomer ID\t\tPrice\t\tBill Date\n");
        String  formatStr =
                "%05d\t%5d\t\t%05d\t\t%06.2f\t%s\n";
        for (Bill bill : bills){
            strList.append(String.format(
                    formatStr,
                    bill.id,
                    bill.count,
                    bill.customerId,
                    bill.price,
                    bill.billDate
            ));
        }
        return strList.toString();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", count=" + count +
                ", customerId=" + customerId +
                ", price=" + price +
                ", billDate=" + billDate +
                ", \ngoods=" + goods +
                '}';
    }

    public void setGoods(List<Good> goods){
        this.goods = goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBillDate(java.sql.Timestamp billDate) {
        this.billDate = billDate;
    }
}
