package com.fire.app.domain;

import java.util.Date;
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

public interface AppFireEventRepository extends JpaRepository<AppFireEvent, Long> {

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2", nativeQuery = true)
    List<AppFireEvent> findDateToMonth(String date1, String date2);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.street_id = ?3", nativeQuery = true)
    List<AppFireEvent> findStreetData(Date beginTime, Date endTime, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.street_id = ?3", nativeQuery = true)
    List<AppFireEvent> findAreaDateToMonth(String string, String string2, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.fire_type = ?3 and t.block_name like %?2% order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndBlockName(Long streetId, String name, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where t.block_id = ?1 and t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByLossIsNotNull(Long blockId);

    @Query(value = "SELECT * FROM app_fire_event t where t.block_id = ?1 and t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByHurtNumIsNotNull(Long blockId);

    @Query(value = "SELECT * FROM app_fire_event t where t.block_id = ?1 and t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByDeadNumIsNotNull(Long blockId);

    @Query(value = "SELECT * FROM app_fire_event  order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findAllData();

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.fire_type = ?2 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndFireType(Long streetId, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.block_name like %?2% order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findBystreetIdAndBlockName(Long streetId, String name);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockId(Date bTime, Date eTime, Long id);
    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 and t.fire_type=?4 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockId(Date bTime, Date eTime, Long id, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where t.block_id = ?1 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockId(Long blockId);
    
    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 and t.fire_type in ?4 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockId(Date bTime, Date eTime, Long id, List<String> types);

    @Query(value = "SELECT * FROM app_fire_event t where t.block_id = ?1 and t.fire_type = ?2 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockIdAndFireType(Long blockId, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 and t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockIdAndLossIsNotNull(Date bTime, Date eTime, Long id);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 and t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockIdAndHurtNumIsNotNull(Date bTime, Date eTime, Long id);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.block_id = ?3 and t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByBlockIdAndDeadNumIsNotNull(Date bTime, Date eTime, Long id);

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.occur_time between ?2 and ?3 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId, Date bTime, Date eTime);

    @Query(value = "SELECT * FROM app_fire_event t where t.street_id = ?1 and t.occur_time between ?2 and ?3 and t.fire_type = ?4 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId, Date bTime, Date eTime, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.street_id = ?3 and t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndLossIsNotNull(Date bTime, Date eTime, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.street_id = ?3 and t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndHurtNumIsNotNull(Date bTime, Date eTime, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?1 and ?2 and t.street_id = ?3 and t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndDeadNumIsNotNull(Date bTime, Date eTime, Long streetId);
    
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?2 AND MONTH(t.occur_time)=?3 and t.street_id = ?1 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId, String year, String month);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?2 AND MONTH(t.occur_time)=?3 and t.street_id = ?1 and t.fire_type = ?4 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long streetId, String year, String month, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.street_id = ?3 and t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndLossIsNotNull(String year, String month, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.street_id = ?3 and t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndHurtNumIsNotNull(String year, String month, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.street_id = ?3 and t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetIdAndDeadNumIsNotNull(String year, String month, Long streetId);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2  order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByTime(String year, String month);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.fire_type = ?3  order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByFireType(String year, String month, String fireType);

    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.loss is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByFireTypeAndLossIsNotNull(String year, String month);
    
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.hurt_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByFireTypeAndHurtNumIsNotNull(String year, String month);
    
    @Query(value = "SELECT * FROM app_fire_event t where YEAR(t.occur_time)=?1 AND MONTH(t.occur_time)=?2 and t.dead_num is not null order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByFireTypeAndDeadNumIsNotNull(String year, String month);

    @Query(value = "SELECT * FROM app_fire_event t where t.occur_time between ?2 and ?3 and t.street_id = ?1 and t.fire_type in ?4 order by occur_time desc", nativeQuery = true)
    List<AppFireEvent> findByStreetId(Long id, Date bTime, Date eTime, List<String> types);


}
