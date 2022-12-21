package com.ecommerce.elasticsearch.api.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.elasticsearch.address.entity.CommuneEntity;
import com.ecommerce.elasticsearch.address.source.CommuneSource;
import com.ecommerce.elasticsearch.component.ElasticeService;
import com.ecommerce.elasticsearch.component.ResultData;
import com.ecommerce.elasticsearch.component.ResultSetData;

@RestController
public class CommueController {

    @Autowired
    @Qualifier("commune-service")
    ElasticeService<CommuneEntity, CommuneSource> communeService;

    @PostMapping("/communes")
    public ResponseEntity<ResultSetData<CommuneEntity>> getData(
           @RequestBody CommuneSource source) {
        return ResponseEntity.ok(communeService.getListSource(source));
    }

    @PostMapping("/commune")
    public ResponseEntity<ResultData<CommuneEntity>> createData(@RequestBody CommuneEntity addressEntity) {
        return ResponseEntity.ok(communeService.createSource(addressEntity));
    }
}
