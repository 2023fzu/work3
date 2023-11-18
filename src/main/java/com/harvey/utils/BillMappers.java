package com.harvey.utils;

import com.harvey.exc.ForeignKeyException;
import com.harvey.mapper.BillMapper;
import com.harvey.pojo.Bill;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author : HarveyBlocks
 * @version : 1.0
 * @className : BillMappers
 * @date : 2023/10/20 22:57
 **/
public class BillMappers {

    // 这是一个为了实现单例而创建的对象
    private static final BillMappers BILL_MAPPERS;

    // sqlSession的工厂方法,方便本类中的方法少写一点重复的过程
    private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getFactory();

    // 静态内部类,用来实例化goodMappers,因为有个异常要抓取
    static {
        try {
            BILL_MAPPERS = new BillMappers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @throws IOException getResourceAsStream抛出的异常
     */
    private BillMappers() throws IOException {
    }

    /**
     * 工厂方法,获取goodMappers对象
     *
     * @return goodMappers对象
     */
    public static BillMappers billMappersFactory() {
        return BILL_MAPPERS;
    }

    /**
     * 把价钱保留到两位小数
     *
     * @param price 待格式化的价格
     * @return 返回格式化之后的价格
     */
    private double formatPrice(double price) {
        String fPrice = String.format("%.2f", price);
        return Double.parseDouble(fPrice);
    }

    public boolean del(int id) throws ForeignKeyException {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                // 获取BillMapper接口的代理对象
                BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
                billMapper.delById(id);
                sqlSession.commit();
            } catch (Exception e) {
                throw new ForeignKeyException(id);
            }
        }
        return true;
    }

    public void add(Bill bill) throws ForeignKeyException {
        if(bill==null){
            return;
        }
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                // 获取BillMapper接口的代理对象
                sqlSession.getMapper(BillMapper.class).addBill(bill);
                sqlSession.commit();
            } catch (Exception e) {
                throw new ForeignKeyException();
            }
        }
    }

    public int update(Bill bill) throws ForeignKeyException {
        if(bill==null){
            return 0;
        }
        int num;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取BillMapper接口的代理对象
            num = sqlSession.getMapper(BillMapper.class).update(bill);
            sqlSession.commit();
        } catch (Exception e) {
            throw new ForeignKeyException();
        }
        return num;
    }

    /**
     * 查
     *
     * @return 查询所有的记录
     */
    public List<Bill> selectAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取BillMapper接口的代理对象
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.selectAll();
        }
    }


    /**
     *
     */
    public List<Bill> selectGoodName() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取BillMapper接口的代理对象
            BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
            return billMapper.selectWithGoodName();
        }
    }
}


