package com.fire.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{


    /**
     * @createDate 2017年3月28日下午3:27:45 
     * @author wangzhiwang
     * @param name
     * @param phone
     * @return 
     * @description
     */
    User findByUsernameAndPassword(String name, String password);

    /**
     * @createDate 2017年6月8日下午2:39:08 
     * @author wangzhiwang
     * @param name
     * @param verifCode
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM user t where t.mobile = ?1 and t.password = ?2", nativeQuery = true)
    User findByPhoneAndPassword(String name, String password);
    

}
