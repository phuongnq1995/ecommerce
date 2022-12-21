package com.ecommerce.elasticsearch.component;

import com.ecommerce.elasticsearch.common.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ElasticPage {

    @JsonProperty(value = "limit")
    private Integer limit;

    @JsonProperty(value = "offset")
    private Integer offset;

    public Integer getLimit() {
        if(limit == null) {
            limit = Constant.LIMIT_DEFAULT_VALUE;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        if(offset == null) {
            offset = Constant.OFFSET_DEFAULT_VALUE;
        }
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
