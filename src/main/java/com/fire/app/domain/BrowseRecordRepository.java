package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query(value = "select * from browse_record t where t.uid = ?1 order by t.browse_time ",nativeQuery = true)
    List<BrowseRecord> findByUid(Long uid);
   

}
