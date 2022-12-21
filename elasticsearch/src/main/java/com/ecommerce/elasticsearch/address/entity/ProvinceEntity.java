package com.ecommerce.elasticsearch.address.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName =  "province", shards = 1, replicas = 0)
public class ProvinceEntity {

    @Id
    private String id;
    private String code;
    private String name;

}
