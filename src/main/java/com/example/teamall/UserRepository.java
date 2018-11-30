package com.example.teamall;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findUserByTelephone(String telephone);
}
