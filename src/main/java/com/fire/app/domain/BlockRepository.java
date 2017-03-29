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

}
