package com.qonsult.init;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantSchemaResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class InitSchema {

    private final InitRoles initRoles;
    private final InitCenterAdminRole initCenterAdminRole;
    private final InitCenterAdminUser initCenterAdminUser;
    private final DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;
    private final MigrateDB migrateDB;
    private final TenantSchemaResolver tenantSchemaResolver;
    public void initSchema(String schema){
        try{
//            initCenterAdminUser.createAdminUser(schema+"_admin");
            TenantContext.clear();
            TenantContext.setCurrentTenant(schema);
            tenantSchemaResolver.resolveCurrentTenantIdentifier();
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
