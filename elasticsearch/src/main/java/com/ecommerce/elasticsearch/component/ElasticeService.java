package com.ecommerce.elasticsearch.component;

public interface ElasticeService<E, R> {

    ResultSetData<E> getListSource(R source);

    ResultData<E> createSource(E source);
}
