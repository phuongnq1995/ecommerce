package com.ecommerce.elasticsearch.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ecommerce.elasticsearch.address.entity.DistrictEntity;

public interface DistrictElasticRepository extends ElasticsearchRepository<DistrictEntity, Long> {

    @Query("{\"bool\" : {\"must\": [?0]}}")
    Page<DistrictEntity> findByName(String sql, Pageable pageable);
}
