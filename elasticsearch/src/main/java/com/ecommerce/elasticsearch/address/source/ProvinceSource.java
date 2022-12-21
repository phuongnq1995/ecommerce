package com.ecommerce.elasticsearch.address.source;

import com.ecommerce.elasticsearch.common.Constant;
import com.ecommerce.elasticsearch.component.ElasticPage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(onConstructor = @__(@JsonCreator) )
@JsonInclude(Include.NON_NULL)
@Builder
public class ProvinceSource extends ElasticPage{

    @JsonProperty
    private String id;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        if(code == null) {
            code = Constant.EMPTY_DEFAULT_VALUE;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        if(name == null) {
            name = Constant.EMPTY_DEFAULT_VALUE;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
