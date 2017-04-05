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
     * @createDate 2017年4月5日下午6:46:00 
     * @author wangzhiwang
     * @param name
     * @param phone
     * @return 
     * @description
     */
    Boolean selectByUsernameAndPassword2(String name, String phone);

}
