package com.sky.mapper;


import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    /*
    *
    * 用户下单
    * */
    void insert(Orders orders);


    /**
     * 根据订单号和用户id查询订单
     * @param orderNumber
     * @param userId
     */
    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String orderNumber, Long userId);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);


    /**
     * 分页条件查询并按下单时间排序
     * @param ordersPageQueryDTO
     */
    Page<Orders> history(OrdersPageQueryDTO ordersPageQueryDTO);


    /**
     * 根据id查询订单
     * @param id
     */
    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);



    /**
     * 根据状态统计订单数量
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);




//    根据订单状态和下单时间查询订单
    @Select("select * from orders where status=#{status} and order_time<#{orderTime}")
    List<Orders>getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

/*
*
* 支付，直接改订单状态
* */
    @Update("update orders set status = 5 where user_id=#{userId} and number=#{str}")
    void pay(Long userId, String str);



    /**
     * 根据动态条件统计营业额
     * @param map
     */
    Double sumByMap(Map map);


    /**
     *根据动态条件统计订单数量
     * @param map
     */
    Integer countByMap(Map map);


    /**
     * 查询商品销量排名
     * @param begin
     * @param end
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);







}
