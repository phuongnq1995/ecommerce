package com.ecommerce.elasticsearch.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.elasticsearch.address.entity.ProvinceEntity;
import com.ecommerce.elasticsearch.address.source.ProvinceSource;
import com.ecommerce.elasticsearch.address.sql.ProviceSql;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@Service("province-service")
public class ProvinceService implements ElasticeService<ProvinceEntity, ProvinceSource> {

    @Autowired
    ProvinceElasticRepository provinceElastic;

    @Override
    public ResultSetData<ProvinceEntity> getListSource(ProvinceSource source) {
        String sql = ProviceSql.sqlSearch(source);
        Pageable page = PageRequest.of(source.getOffset() -1, source.getLimit());
        Page<ProvinceEntity> pageProvince = provinceElastic.findByName(sql, page);
        return new ResultSetData<>(pageProvince.getContent(), pageProvince.getTotalElements());
    }

    @Override
    public ResultData<ProvinceEntity> createSource(ProvinceEntity source) {
        return new ResultData<ProvinceEntity>(provinceElastic.save(source));
    }
}
