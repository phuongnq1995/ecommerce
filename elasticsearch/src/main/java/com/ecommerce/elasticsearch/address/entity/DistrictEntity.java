package com.ecommerce.elasticsearch.address.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName =  "district", shards = 1, replicas = 0)
public class DistrictEntity {

    @Id
    private String id;
    private String code;
    private String name;
    private String codeProvince;
}
