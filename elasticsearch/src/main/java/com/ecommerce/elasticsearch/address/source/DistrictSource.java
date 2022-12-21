package com.ecommerce.elasticsearch.address.source;

import com.ecommerce.elasticsearch.common.Constant;
import com.ecommerce.elasticsearch.component.ElasticPage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;

@AllArgsConstructor(onConstructor = @__(@JsonCreator) )
@JsonInclude(Include.NON_NULL)
public class DistrictSource extends ElasticPage {

    @JsonProperty
    private String id;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "code_province")
    private String codeProvince;

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

    public String getCodeProvince() {
        if(codeProvince == null) {
            codeProvince = Constant.EMPTY_DEFAULT_VALUE;
        }
        return codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
    }
}
