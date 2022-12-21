package com.ecommerce.elasticsearch.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.elasticsearch.address.entity.CommuneEntity;
import com.ecommerce.elasticsearch.address.source.CommuneSource;
import com.ecommerce.elasticsearch.address.sql.CommuneSql;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@Service("commune-service")
public class CommuneService implements ElasticeService<CommuneEntity, CommuneSource>{
    @Autowired
    CommuneElasticRepository communeElasticRep;

    @Override
    public ResultSetData<CommuneEntity> getListSource(CommuneSource source) {
        String sql = CommuneSql.sqlSearch(source);
        Pageable pageable = PageRequest.of(source.getOffset() - 1, source.getLimit());
        Page<CommuneEntity> page = communeElasticRep.findByName(sql, pageable);
        return new ResultSetData<>(page.getContent(), page.getTotalElements());
    }

    @Override
    public ResultData<CommuneEntity> createSource(CommuneEntity source) {
        return new ResultData<CommuneEntity>(communeElasticRep.save(source));
    }

}
