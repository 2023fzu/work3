package com.harvey.mapper;

import com.harvey.pojo.Bill;
import com.harvey.pojo.Good;
import com.harvey.pojo.GoodColumn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author HarveyBlocks
 * @date 2023/10/18 17:29
 **/
public interface BillMapper {
    //增
    void addBill(Bill bill);
    //删

    /*
     * 列
     * 适合仔细审查每一次删除是否成功
     * */
    int delById(int id);

    /*
     * 数组中可以只含一个元素来实现删除单个元素的目的
     * 而且不会出现id不存在的异常
     * 所以这个适合返回int值
     * 却不知道是哪里出了问题
     * */
    int delByIds(int[] ids);

    //改
    int updateByMessage(
            @Param("id") int id, @Param("goodId") int goodId,
            @Param("price") double price, @Param("customerId") int  customerId,
            @Param("billDate") Date billDate
    );

    int update(Bill bill);

    //查
    List<Bill> selectAll();
    List<Bill> selectWithGoodName();


    //条件
    List<Bill> selectById(
            @Param("id") int id);

    List<Bill> selectByIds(int[] id);


    //对于名字我们要注意模糊查询
    List<Bill> selectByGoodId(
            @Param("goodId") int goodId);

    List<Bill> selectByGoodId(int[] goodId);


    List<Bill> selectByCustomerId(
            @Param("customerId") int customerId);

    List<Bill> selectByCustomerIds(int[] customerId);

    //闭区间
    List<Bill> selectByPrices(
            @Param("low") double low,
            @Param("high") double high);

    List<Bill> selectByPrice(
            @Param("price") double price);



    List<Bill> selectByCondition(Good good);

    List<Bill> selectByCondition(Map<String, Objects> map);

    //分组 没有意义不做了

    //排序
    /*
     * ASC(ascending,默认)升序
     * DESC(descending)降序
     * */
    List<Bill> orderByAsc(
            @Param("column") String column);
    List<Bill> orderByDesc(
            @Param("column") String column);


    //分页
    //参数 每页的page
    List<Bill> dividePage(
            @Param("start") int start,
            @Param("len") int len);

    int getLen();


    /*
    * 外键
    * 返回一张表,返回商品名
    * */

}
