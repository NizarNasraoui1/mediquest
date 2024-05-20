package com.qonsult.init;

import com.qonsult.config.tenant_config.TenantDataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class MigrateDB {

    private final TenantDataSource tenantDataSource;

    @Value("${flyway-script-location}")
    String flywayScripts;
    public void migrateDB(){
        for (DataSource dataSource : tenantDataSource.getAllTenantDS().values()){
            migrateSchema(dataSource);
        }
    }

    public void migrateSchema(DataSource dataSource){
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(flywayScripts)
                .outOfOrder(true)
                .load();
        if (!checkTableExistence(dataSource)) {
            flyway.baseline();
        }
        flyway.migrate();
    }
    private boolean checkTableExistence(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet rs = statement.executeQuery(
                        "SELECT to_regclass('flyway_schema_history');")) {
                    if (rs.next()) {
                        return rs.getString(1) != null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
