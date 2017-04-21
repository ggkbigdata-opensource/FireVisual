package com.fire.app.service;

/**
 * @createDate 2017年3月28日下午2:49:53
 * @author wangzhiwang
 * @description
 */
public interface UserService {


    /**
     * @createDate 2017年3月28日下午3:27:07 
     * @author wangzhiwang
     * @param name
     * @param phone
     * @return 
     * @description
     */
    Boolean selectByUsernameAndPassword(String name, String phone);

    /**
     * @createDate 2017年4月21日下午5:46:51 
     * @author wangzhiwang
     * @param name
     * @param verifCode
     * @param name2
     * @return 
     * @description
     */
    Boolean selectByUsernameAndPassword(String name, String phone, String verifCode);

}
