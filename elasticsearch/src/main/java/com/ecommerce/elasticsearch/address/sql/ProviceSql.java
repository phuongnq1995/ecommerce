package com.ecommerce.elasticsearch.address.sql;

import java.util.StringJoiner;

import com.ecommerce.elasticsearch.address.source.ProvinceSource;

public class ProviceSql {

    public static String sqlSearch(ProvinceSource source) {
        StringJoiner sql = new StringJoiner(",");
        if (!source.getCode().isEmpty()) {
            sql.add("{\"match\": { \"code\":\""+source.getCode()+"\"}}");
        }
        if (!source.getName().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getName()+"\"}}");
        }
        return sql.toString();
    }
}
