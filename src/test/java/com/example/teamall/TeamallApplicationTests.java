package com.example.teamall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamallApplicationTests {

	@Test
	public void contextLoads() {
	}

    @Autowired
    StoreService storeService;

    //  向商品库里添加商品
	@Test
	public void TestAddToStore(){
        Store s = storeService.createStore();
        s.addProduct(new Product("金果饮"));
        s.addProduct(new Product("八宝茶"));
        System.out.println("商品列表"+s.printProductList());


//        StoreService storeService = new StoreService();
//        storeService.addProduct("金果饮");
//	    assert (storeService.getCount() == 1);
//	    System.out.println("加入成功");

    }

}
