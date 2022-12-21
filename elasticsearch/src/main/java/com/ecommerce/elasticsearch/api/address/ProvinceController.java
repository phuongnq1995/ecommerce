package com.ecommerce.elasticsearch.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.elasticsearch.address.entity.ProvinceEntity;
import com.ecommerce.elasticsearch.address.source.ProvinceSource;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@RestController
public class ProvinceController {

    @Autowired
    @Qualifier("province-service")
    ElasticeService<ProvinceEntity, ProvinceSource> provinceService;

    @PostMapping("/provinces")
    public ResponseEntity<ResultSetData<ProvinceEntity>> getData(
           @RequestBody ProvinceSource source) {
        return ResponseEntity.ok(provinceService.getListSource(source));
    }

    @PostMapping("/provice")
    public ResponseEntity<ResultData<ProvinceEntity>> createData(@RequestBody ProvinceEntity addressEntity) {
        return ResponseEntity.ok(provinceService.createSource(addressEntity));
    }
}
