package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @fileName AppPunishmentRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:41:53
 * @version 1.0
 * @function
 */

public interface AppPunishmentRepository extends JpaRepository<AppPunishment, Long> {

    /**
     * @createDate 2017年3月30日下午3:48:35 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @param seal
     * @return 
     * @description 查询不是临时查封的数据
     */
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.execute_time)=?1 AND MONTH(t.execute_time)=?2 and t.punish_method != '临时查封'", nativeQuery = true)
    List<AppPunishment> findDateToMonth(String string, String string2);

    /**
     * @createDate 2017年3月30日下午3:52:01 
     * @author wangzhiwang
     * @param string
     * @param string2
     * @return 
     * @description 查找临时查封的数据
     */
    @Query(value = "SELECT * FROM app_punishment t where YEAR(t.seal_up_time_begin)=?1 AND MONTH(t.seal_up_time_begin)=?2 and t.punish_method = '临时查封'", nativeQuery = true)
    List<AppPunishment> findSealDateToMonth(String string, String string2);

}
