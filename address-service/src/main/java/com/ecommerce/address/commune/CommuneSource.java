package com.ecommerce.address.commune;

import com.ecommerce.address.component.EcSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonInclude(Include.NON_NULL)
@Getter
public class CommuneSource extends EcSource {
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;

    @JsonProperty
    private String code;

    @JsonProperty
    private String codeProvince;

    @JsonProperty
    private String codeDistrict;

    @JsonProperty
    private String name;

    @JsonProperty
    private boolean isDelete;

    public CommuneSource(CommuneEntity request) {
        this.id = request.getId();
        this.name = request.getName();
        this.isDelete = request.isDelete();
        this.code = request.getCode();
        this.codeProvince = request.getCodeProvince();
        this.codeDistrict = request.getCodeDistrict();
    }
}