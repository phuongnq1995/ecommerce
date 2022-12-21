package com.ecommerce.elasticsearch.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.elasticsearch.address.entity.DistrictEntity;
import com.ecommerce.elasticsearch.address.source.DistrictSource;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@RestController
public class DistrictController {

    @Autowired
    @Qualifier("district-service")
    ElasticeService<DistrictEntity, DistrictSource> districtService;

    @PostMapping("/districts")
    public ResponseEntity<ResultSetData<DistrictEntity>> getData(
           @RequestBody DistrictSource source) {
        return ResponseEntity.ok(districtService.getListSource(source));
    }

    @PostMapping("/district")
    public ResponseEntity<ResultData<DistrictEntity>> createData(@RequestBody DistrictEntity addressEntity) {
        return ResponseEntity.ok(districtService.createSource(addressEntity));
    }
}
