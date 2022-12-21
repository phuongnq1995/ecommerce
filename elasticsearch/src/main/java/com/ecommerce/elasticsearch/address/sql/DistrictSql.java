package com.ecommerce.elasticsearch.address.sql;

import java.util.StringJoiner;

import com.ecommerce.elasticsearch.address.source.DistrictSource;

public class DistrictSql {

    public static String sqlSearch(DistrictSource source) {
        StringJoiner sql = new StringJoiner(",");
        if (!source.getCode().isEmpty()) {
            sql.add("{\"match\": { \"code\":\""+source.getCode()+"\"}}");
        }
        if (!source.getCodeProvince().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getCodeProvince()+"\"}}");
        }
        if (!source.getName().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getName()+"\"}}");
        }
        return sql.toString();
    }
}
