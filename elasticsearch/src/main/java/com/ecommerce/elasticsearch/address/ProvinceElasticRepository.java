package com.ecommerce.elasticsearch.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ecommerce.elasticsearch.address.entity.ProvinceEntity;

public interface ProvinceElasticRepository extends ElasticsearchRepository<ProvinceEntity, Long> {

    @Query("{\"bool\" : {\"must\": [?0]}}")
    Page<ProvinceEntity> findByName(String sql, Pageable pageable);
}
