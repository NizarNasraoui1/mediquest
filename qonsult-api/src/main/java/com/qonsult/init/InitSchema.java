package com.qonsult.init;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantSchemaResolver;
import com.qonsult.entity.User;
import com.qonsult.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class InitSchema {

    private final DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;
    private final MigrateDB migrateDB;
    public void initSchema(String schema){
        try{
            migrateSchema(schema);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void migrateSchema(String schemaName) {
        DataSource dataSource = dataSourceBasedMultiTenantConnectionProvider.selectDataSource(schemaName);
        migrateDB.migrateSchema(dataSource);
    }
}
