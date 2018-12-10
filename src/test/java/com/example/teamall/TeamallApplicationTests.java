package com.example.teamall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamallApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StoreService storeService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    ShopService shopService;


    @Test
    public void TestCreateProduct() {
        productService.createProduct("金果饮");
        productService.createProduct("八宝茶");
        List productList = productService.findAllProduct();
    }

    @Test
    public void TestAddProduct(){
        User user = userService.findUserByTelephone("18801233565");
//        shoppingCartService.addProduct(user, 1L);
//        shoppingCartService.addProduct(user, 2L);
//        shoppingCartService.addProduct(user,1);
    }

    //  我要有多个店铺多个购物车
    /**
     * 创建店铺
     * 将商品加入到店铺中
     */
    @Test
    public void TestAddShopProductToShop(){
        shopService.createShop("1号店");
        shopService.createShop("2号店");
        shopService.addProduct(1L,1L);
        shopService.addProduct(1L,2L);
    }

    /**
     * 看一下shopId = 1 店铺的数据对不对
     */
    @Test
    public void Test1LShopProductNum(){
        int length = shopService.getShopProductLength(1L);
        assert length == 2;
        System.out.println(shopService.getShopProductListString(1L));
    }
    /**
     * 创建用户
     */
    @Test
    public void TestCreateUser(){
        userService.createUser("18801233565","刘1");
        userService.createUser("18801233565","刘2");
    }
    /**
     * 将指定店铺的商品加入到购物车中
     *
     */
    @Test
    public void TestAddShopProductToShoppingCart(){

//        User user = userService.findUserByTelephone("18801233565");

        shoppingCartService.addProduct(1L,1L,1L);
//        shoppingCartService.addProduct(1L,1L);
    }

    @Test
    public void TestEqual() {
        String a = "llala";
        String b = "llala";
        System.out.println(a == b);
        assert a == b;
        System.out.println("相等");
    }

}
