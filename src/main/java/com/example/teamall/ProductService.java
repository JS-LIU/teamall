package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService() {
    }

    public void createProduct(String name) {
        Product p = new Product(name);
        if(findProductByName(name) == null){
            productRepository.save(p);
        }
    }
    private Product findProductByName(String name){
        Product p = productRepository.findByName(name);
        if(p == null){
            return null;
        }
        return p;
    }

    public List findAllProduct() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
//        return productRepository.findOne(id);
    }

    public ShoppingCartProduct convertToShoppingCartProduct(Product p) {
        return new ShoppingCartProduct(p);
    }
}
