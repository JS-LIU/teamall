package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShopService {

    @Autowired
    ShopProductService shopProductService;
    @Autowired
    ShopRepository shopRepository;
    public void addProduct(Long shopId, Long productId) {
        Shop shop = findShopById(shopId);
        if(shop.findShopProductById(productId) == null){
            ShopProduct shopProduct = shopProductService.createShopProduct(productId,shopId);
            shop.addShopProduct(shopProduct);
        }

    }

    public Shop findShopById(Long shopId) {
        return shopRepository.findById(shopId).orElse(null);
    }


    public Shop createShop(String name) {
        return shopRepository.save(new Shop(name));
    }

    public int getShopProductLength(Long shopId) {
        Shop shop = findShopById(shopId);
        return shop.shopProductList.size();
    }

    public String getShopProductListString(Long shopId) {
        Shop shop = findShopById(shopId);
        StringBuilder ShopProductListString = new StringBuilder("[");
        List<ShopProduct> shopProductList = shop.getShopProductList();
        for(ShopProduct shopProduct:shopProductList){
            ShopProductListString.append("{shopProductName:").append(shopProduct.getName()).append(",productId:").append(shopProduct.getProductId()).append("},");
        }
        return ShopProductListString+"]";
    }
}
