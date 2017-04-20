package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @fileName AppFireEventRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:41:14
 * @version 1.0
 * @function
 */

public interface BrowseRecordRepository extends JpaRepository<BrowseRecord, Long> {

    /**
     * @createDate 2017年4月20日上午9:06:57 
     * @author wangzhiwang
     * @param uid
     * @return 
     * @description
     */
    List<BrowseRecord> findByUid(Long uid);
   

}
