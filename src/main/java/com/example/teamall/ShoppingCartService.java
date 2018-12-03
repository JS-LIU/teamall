package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartService {
    public ShoppingCartService() {
    }

    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartProductService shoppingCartProductService;

    /**
     * 加入购物车
     * @param user
     * @param productId
     * @return
     */
    public void addProduct(User user, Long productId) {
        Product p = productService.findById(productId);
        ShoppingCartProduct shoppingCartProduct = shoppingCartProductService.createProduct(p);
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.addProduct(shoppingCartProduct);
    }
//    public void addProduct(User user,Long shopProductId){
//        Product p = shopProductService.findById(shopProductId);
//    }
}
