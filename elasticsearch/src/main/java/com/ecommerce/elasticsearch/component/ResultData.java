package com.ecommerce.elasticsearch.component;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final T data;

    public ResultData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}