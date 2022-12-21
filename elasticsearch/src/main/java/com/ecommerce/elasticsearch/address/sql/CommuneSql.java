package com.ecommerce.elasticsearch.address.sql;

import java.util.StringJoiner;

import com.ecommerce.elasticsearch.address.source.CommuneSource;

public class CommuneSql {

    public static String sqlSearch(CommuneSource source) {
        StringJoiner sql = new StringJoiner(",");
        if (!source.getCode().isEmpty()) {
            sql.add("{\"match\": { \"code\":\""+source.getCode()+"\"}}");
        }
        if (!source.getCodeProvince().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getCodeProvince()+"\"}}");
        }
        if (!source.getCodeDistrict().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getCodeDistrict()+"\"}}");
        }
        if (!source.getName().isEmpty()) {
            sql.add ("{\"match\": { \"name\":\""+source.getName()+"\"}}");
        }
        return sql.toString();
    }
}
