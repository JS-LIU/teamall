package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopService {

    @Autowired
    ShopProductService shopProductService;

    public void addProduct(long shopId, long productId) {
        shopProductService.createShopProduct(productId);

    }


}
