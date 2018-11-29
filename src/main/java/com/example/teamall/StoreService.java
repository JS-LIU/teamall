package com.example.teamall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public Store createStore(){
        Store store = new Store();
        storeRepository.save(store);
        return store;
    }
}
