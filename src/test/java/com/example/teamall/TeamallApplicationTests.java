package com.example.teamall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamallApplicationTests {

	@Test
	public void contextLoads() {
	}


	//  向商品库里添加商品
	@Test
	public void TestAddToStore(){
	    Store store = new Store();
	    store.addProduct("白咖啡");
	    assert (store.getCount() == 1);
	    System.out.println("加入成功");

    }
}
