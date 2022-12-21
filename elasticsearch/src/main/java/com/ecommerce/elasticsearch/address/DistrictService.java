package com.ecommerce.elasticsearch.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.elasticsearch.address.entity.DistrictEntity;
import com.ecommerce.elasticsearch.address.source.DistrictSource;
import com.ecommerce.elasticsearch.address.sql.DistrictSql;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@Service("district-service")
public class DistrictService implements ElasticeService<DistrictEntity, DistrictSource> {

    @Autowired
    DistrictElasticRepository districtElastic;

    @Override
    public ResultSetData<DistrictEntity> getListSource(DistrictSource source) {
        String sql = DistrictSql.sqlSearch(source);
        Pageable pageable = PageRequest.of(source.getOffset() - 1, source.getLimit());
        Page<DistrictEntity> page = districtElastic.findByName(sql, pageable);
        return new ResultSetData<>(page.getContent(), page.getTotalElements());
    }

    @Override
    public ResultData<DistrictEntity> createSource(DistrictEntity source) {
        return new ResultData<DistrictEntity>(districtElastic.save(source));
    }

}
