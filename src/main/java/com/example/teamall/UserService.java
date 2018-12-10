package com.example.teamall;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService{

    @Autowired
    UserRepository userRepository;

    public void createUser(String telephone,String name) {
        User u = new User(telephone,name);

        if(findUserByTelephone(u.getTelephone()) == null){
            userRepository.save(u);
        }

    }
    public User findUserByTelephone(String telephone){
        return userRepository.findUserByTelephone(telephone);
    }
}
