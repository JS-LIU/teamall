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

    //  向商品库里添加商品
    @Test
    public void TestAddToStore() {
        Store s = storeService.createStore();
        s.addProduct(new Product("金果饮"));
        s.addProduct(new Product("八宝茶"));
        System.out.println("商品列表" + s.printProductList());


//        StoreService storeService = new StoreService();
//        storeService.addProduct("金果饮");
//	    assert (storeService.getCount() == 1);
//	    System.out.println("加入成功");

    }

    @Test
    public void TestCreateProduct() {
        productService.createProduct("金果饮");
        productService.createProduct("八宝茶");
        List productList = productService.findAllProduct();
    }

    @Test
    public void TestCreateUser() {
        userService.createUser("18801233565", "王强");
        userService.createUser("18801233565", "六点起");
        userService.createUser("13812345678", "王强");

    }
    @Test
    public void TestAddProduct(){
        User user = userService.findUserByTelephone("18801233565");
        shoppingCartService.addProduct(user, 1L);
        shoppingCartService.addProduct(user, 2L);
//        shoppingCartService.addProduct(user,1);
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
