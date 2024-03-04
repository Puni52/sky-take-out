package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /*
    *
    * 动态条件查询
    * */
    List<ShoppingCart> list(ShoppingCart shoppingCart);



    /*
    *
    *
    * 根据id修改商品数量
    * */
    @Update("update shopping_cart set number=#{number} where id =#{id}")
    void updateNumberById(ShoppingCart shoppingCart);



    /*
    *
    *
    * 插入购物车
    * */
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time,number)" +
            "values(#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{amount},#{createTime},#{number}) ")
    void insert(ShoppingCart shoppingCart);



    /*
    *
    *
    * 根据用户ID删除购物车数据
    * */
    @Delete("delete from shopping_cart where user_id= #{userId}")
    void deleteByUserId(Long userId);



    /*
    *
    *
    * 根据菜品id获取购物车中该菜品的数量
    * */
    @Select("select number from shopping_cart where dish_id=#{dishId}")
    int getNumberByDishId(Long dishId);

    /*
    *
    * 将购物车中该菜品数量减一
    * */
    @Update("update shopping_cart set number =#{number}-1")
    void subNumberByDishId(Long dishId, Integer number);


    /*
    *
    *
    * 在购物车中删除该菜品
    * */
    @Delete("delete from shopping_cart where dish_id=#{dishId}")
    void deleteByDishId(Long dishId);
}
