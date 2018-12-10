package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopProductService {
    @Autowired
    ShopProductRepository shopProductRepository;
    @Autowired
    ProductService productService;

    public ShopProduct createShopProduct(Long productId,Long shopId) {
        ShopProduct shopproduct = productService.createShopProduct(productId,shopId);
        shopProductRepository.save(shopproduct);
        return shopproduct;
    }

    private ShopProduct findProductById(Long shopProductId) {
        return shopProductRepository.findById(shopProductId).orElse(null);
    }
}
