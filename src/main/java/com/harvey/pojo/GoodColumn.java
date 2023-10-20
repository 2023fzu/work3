package com.harvey.pojo;

/**
 * 商品类
 * <p>
 * create table good(
 * id int(4) comment '主键,商品号' primary key auto_increment,
 * name varchar(50) comment '商品名,唯一' unique ,
 * price double comment '商品价格,即单价' not null,
 * stock int comment '库存' check (stock>=0)
 * )comment '商品表' ;
 *
 * @author HarveyBlocks
 * @date 2023/10/18 17:26
 **/

public enum GoodColumn {
    ID,
    NAME,
    PRICE,
    STOCK
}
