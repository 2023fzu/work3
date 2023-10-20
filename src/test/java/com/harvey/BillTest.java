package com.harvey;

import com.harvey.exc.ForeignKeyException;
import com.harvey.pojo.Bill;
import com.harvey.utils.BillMappers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HarveyBlocks
 * @date 2023/10/19 20:33
 **/
public class BillTest {
    private static final Logger LOGGER = LoggerFactory.getLogger("ShopLog");
    private static final BillMappers BILL_MAPPERS = BillMappers.billMappersFactory();
    @Test
    public void addTest()  {
        try {
            BILL_MAPPERS.add(new Bill(19,235,34.3));
            LOGGER.info("添加成功");
        } catch (ForeignKeyException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Test
    public void delTest() {

        try {
            BILL_MAPPERS.del(23);
            LOGGER.info("删除成功");
        } catch (ForeignKeyException e) {
            LOGGER.error(e.getMessage());
        }
    }
    @Test
    public void updateTest()  {
        Bill bill = new Bill(244, 235, 34.3);
        bill.setId(35);

        try {
            BILL_MAPPERS.update(bill);
            LOGGER.info("更新成功");
        } catch (ForeignKeyException e) {
            LOGGER.error(e.getMessage());
        }

    }
    @Test
    public void selectTest(){
        //对toString还要做一个更改
        BILL_MAPPERS.selectAll().forEach(s->LOGGER.info(s.toString()));
    }
    @Test
    public void foreignKeyTest()  {
        LOGGER.info(Bill.listToString(BILL_MAPPERS.selectGoodName()));
    }
}
