package com.ecommerce.elasticsearch.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ecommerce.elasticsearch.address.entity.CommuneEntity;

public interface CommuneElasticRepository extends ElasticsearchRepository<CommuneEntity, Long> {

    @Query("{\"bool\" : {\"must\": [?0]}}")
    Page<CommuneEntity> findByName(String sql, Pageable pageable);
}
