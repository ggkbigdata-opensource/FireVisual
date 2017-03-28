package com.fire.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


    /**
     * @createDate 2017年3月28日下午3:27:45 
     * @author wangzhiwang
     * @param name
     * @param phone
     * @return 
     * @description
     */
    User findByUsernameAndPassword(String name, String phone);
    

}
