package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {
    public ShoppingCartService() {
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    /**
     * 加入购物车
     * @param user
     * @param productId
     * @return
     */
    public List addProduct(User user, Long productId) {
        Product p = productService.findById(productId);
        ShoppingCartProduct shoppingCartProduct = productService.convertToShoppingCartProduct(p);

        ShoppingCart shoppingCart = user.getShoppingCart();
        return shoppingCart.addProduct(shoppingCartProduct);
    }


}
