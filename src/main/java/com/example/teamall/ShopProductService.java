package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopProductService {
    @Autowired
    ShopProductRepository shopProductRepository;

    public void createShopProduct(long productId) {

    }

}
