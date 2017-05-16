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

    @Query(value = "SELECT * FROM bs_building_info t where t.street_id = ?1 and t.property_company_name like %?2% order by t.import_time desc", nativeQuery = true)
    List<BsBuildingInfo> findByStreetIdAndName(Long id, String name);

    List<BsBuildingInfo> findByStreetId(Long id);

    @Query(value = "SELECT * FROM bs_building_info t where  t.property_company_name like %?1% order by t.import_time desc", nativeQuery = true)
    List<BsBuildingInfo> findByPropertyCompanyName(String name);

    @Query(value = "SELECT * FROM bs_building_info t where  t.id in ?1", nativeQuery = true)
    List<BsBuildingInfo> findByIds(long[] arr);

    BsBuildingInfo findByItemNumber(String itemNumber);

}
