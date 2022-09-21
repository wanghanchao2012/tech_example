package com.example.multids.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class DatasourceConfiguration {

    final DynamicDatasourceProperties dynamicDatasourceProperties;

    @Bean
    public Map<Object, Object> dataSourceMap() {
        Map<Object, Object> datasourceMap = new HashMap<>();
        /**
         * other database
         */
        Map<String, Map<String, String>> sourceMap = dynamicDatasourceProperties.getDruid();
        DruidDataSource dataSourceDefault = dynamicDatasourceProperties.getPoolConfig();
        sourceMap.forEach((datasourceName, datasourceMaps) -> {
            DruidDataSource dataSource = new DruidDataSource();
            BeanUtils.copyProperties(dataSourceDefault,dataSource);
            datasourceMaps.forEach((K, V) -> {
                String setField = "set" + K.substring(0, 1).toUpperCase() + K.substring(1);
                //转换yml文件中带有-符号的属性
                String[] strings = setField.split("");
                StringBuilder newStr = new StringBuilder();
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].equals("-")) {
                        strings[i + 1] = strings[i + 1].toUpperCase();
                    }
                    if (!strings[i].equals("-")) {
                        newStr.append(strings[i]);
                    }
                }
                try {
                    DruidDataSource.class.getMethod(newStr.toString(), String.class).invoke(dataSource, V);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            datasourceMap.put(datasourceName, dataSource);
        });
        return datasourceMap;
    }


    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DynamicDataSource(Map<Object, Object> dataSourceMap) {

        DynamicDataSource dataSource = new DynamicDataSource();
        // 设置所有的数据源
        dataSource.setTargetDataSources(dataSourceMap);
        // 设置默认使用的数据源对象
        dataSource.setDefaultTargetDataSource(dataSourceMap.get("test"));

        return dataSource;
    }


    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                // 设置数据库mapper的xml文件路径
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/*/*.xml"));
        return bean.getObject();
    }

} 