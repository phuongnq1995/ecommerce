package com.ecommerce.elasticsearch.component;

import static java.util.Collections.emptyList;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResultSetData<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static ResultSetData EMPTY = new ResultSetData<>(emptyList(), 0L);
    private final List<T> data;
    private final long count;

    public ResultSetData(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public long getCount() {
        return count;
    }

    @JsonIgnore
    public Stream<T> stream() {
        return getData().stream();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultSetData<T> emptyResultSet() {
        return EMPTY;
    }
}