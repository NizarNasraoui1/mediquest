package com.qonsult.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Properties;

public class JPAUtility {
    public static EntityManagerFactory createEntityManagerFactory(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put("javax.persistence.nonJtaDataSource", dataSource);

        // Add any additional properties if needed
        // properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        // properties.put("hibernate.show_sql", "true");

        return Persistence.createEntityManagerFactory("your-persistence-unit-name", properties);
    }
}
