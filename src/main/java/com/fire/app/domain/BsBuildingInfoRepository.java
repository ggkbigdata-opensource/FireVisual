package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @fileName BsBuildingInfoRepository.java
 * @author csk
 * @createTime 2017年3月28日 下午12:42:34
 * @version 1.0
 * @function
 */

public interface BsBuildingInfoRepository extends JpaRepository<BsBuildingInfo, Long> {

    /**
     * @createDate 2017年3月31日上午11:36:27 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description
     */
   /* @Query(value = "SELECT * FROM bs_building_info t where t.street_and_committee like %?1%", nativeQuery = true)
    List<BsBuildingInfo> findByStreetAndCommittee(String name);
*/
    /**
     * @createDate 2017年3月31日下午4:53:57 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    List<BsBuildingInfo> findByStreetId(Long id);

}
