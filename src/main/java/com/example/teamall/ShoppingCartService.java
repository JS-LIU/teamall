package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShoppingCartService {
    public ShoppingCartService() {
    }

    @Autowired
    ShoppingCartRepository shoppingcartRepository;
    @Autowired
    ProductService productService;
    /**
     * 加入购物车
     * @param userId
     * @param shopId
     * @param productId
     */
    public void addProduct(Long userId, Long shopId, Long productId) {
        ShoppingCart shoppingCart = getShoppingCart(shopId,userId);
        ShoppingCartProduct shoppingCartProduct = productService.createShoppingCartProduct(shopId,productId);
        shoppingCart.addProduct(shoppingCartProduct);
    }

    /**
     * 用户的店铺购物车
     * @param userId
     * @param shopId
     * @return
     */
    private ShoppingCart getShoppingCart(Long userId,Long shopId) {
        ShoppingCart shoppingCart = findByUserIdAndShopId(userId,shopId);
        if(shoppingCart == null){
            shoppingCart = createShoppingCart(userId,shopId);
        }
        return shoppingCart;
    }

    /**
     * 创建用户的店铺购物车
     * @param userId
     * @param shopId
     * @return
     */
    private ShoppingCart createShoppingCart(Long userId,Long shopId) {
        return shoppingcartRepository.save(new ShoppingCart(userId,shopId));
    }

    /**
     * 找到用户的店铺购物车
     * @param userId
     * @param shopId
     * @return
     */
    private ShoppingCart findByUserIdAndShopId(Long userId,Long shopId) {
        return shoppingcartRepository.findByUserIdAndShopId(userId,shopId);
    }
}
