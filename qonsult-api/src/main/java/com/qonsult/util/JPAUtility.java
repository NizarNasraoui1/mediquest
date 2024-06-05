package com.qonsult.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Properties;

public class JPAUtility {
    public static EntityManagerFactory createEntityManagerFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put("javax.persistence.nonJtaDataSource", dataSource);
        return Persistence.createEntityManagerFactory("persistence-unit", properties);
    }
}
