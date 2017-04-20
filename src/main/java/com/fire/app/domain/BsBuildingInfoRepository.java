package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
     * @param name 
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM bs_building_info t where t.street_id = ?1 and t.property_company_name like %?2%", nativeQuery = true)
    List<BsBuildingInfo> findByStreetIdAndName(Long id, String name);

    List<BsBuildingInfo> findByStreetId(Long id);

    /**
     * @createDate 2017年4月18日上午10:29:21 
     * @author wangzhiwang
     * @param name
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM bs_building_info t where  t.property_company_name like %?1%", nativeQuery = true)
    List<BsBuildingInfo> findByPropertyCompanyName(String name);

    /**
     * @createDate 2017年4月20日上午9:27:01 
     * @author wangzhiwang
     * @param arr
     * @return 
     * @description
     */
    @Query(value = "SELECT * FROM bs_building_info t where  t.id in ?1", nativeQuery = true)
    List<BsBuildingInfo> findByIds(long[] arr);

}
