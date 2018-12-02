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
//    @Autowired
//    SingleCreatorService singleCreatorService;

    public void createUser(String telephone,String name) {
        User u = new User(telephone,name);
        //  todo telephone必须合法（否则会生成null的记录）

        if(findUserByTelephone(u.getTelephone()) == null){
            userRepository.save(u);
        }

    }
    public User findUserByTelephone(String telephone){
        return userRepository.findUserByTelephone(telephone);
    }
}
