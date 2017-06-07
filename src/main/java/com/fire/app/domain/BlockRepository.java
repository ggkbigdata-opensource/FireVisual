package com.fire.app.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BlockRepository extends JpaRepository<Block, Long> {

    /**
     * @createDate 2017年3月29日上午10:34:30 
     * @author wangzhiwang
     * @param id
     * @return 
     * @description
     */
    List<Block> findByStreetId(Long id);

    /**
     * @createDate 2017年6月5日下午7:57:00 
     * @author wangzhiwang
     * @param blockName
     * @return 
     * @description
     */
    Block findByName(String blockName);

    /**
     * @createDate 2017年6月5日下午8:21:46 
     * @author wangzhiwang
     * @param blockName
     * @param streetId
     * @return 
     * @description
     */
    Block findByNameAndStreetId(String blockName, Long streetId);

}
