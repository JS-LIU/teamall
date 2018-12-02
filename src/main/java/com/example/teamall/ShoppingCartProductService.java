package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartProductService {
    @Autowired
    ShoppingCartProductRepository shoppingCartProductRepository;
    @Autowired
    ProductService productService;

    public ShoppingCartProduct createProduct(Product p) {
        ShoppingCartProduct shoppingCartProduct = productService.convertToShoppingCartProduct(p);
        shoppingCartProductRepository.save(shoppingCartProduct);
        return shoppingCartProduct;
    }

}
