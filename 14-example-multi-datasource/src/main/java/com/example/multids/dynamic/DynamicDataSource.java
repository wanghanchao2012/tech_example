package com.example.multids.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wanghanchao
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataBaseType = DataSourceType.getDataBaseType();
        return dataBaseType;
    }
}
