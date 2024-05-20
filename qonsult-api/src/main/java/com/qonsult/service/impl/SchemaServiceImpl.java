package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.entity.Schema;
import com.qonsult.init.InitQuestionnaires;
import com.qonsult.init.InitSchema;
import com.qonsult.repository.SchemaRepository;
import com.qonsult.service.SchemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SchemaServiceImpl implements SchemaService {

    private final SchemaRepository schemaRepository;
    private final TenantDataSource tenantDataSource;
    private final DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;
    private final InitSchema initSchema;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Mono<Schema> addSchema(String schemaName, String username) {
        Schema schema = new Schema();
        schema.setUserName(username);
        schema.setName(schemaName);
        createSchema(schemaName);
        tenantDataSource.getUsernameSchemaMap().put(username,schemaName);
        dataSourceBasedMultiTenantConnectionProvider.addDataSource(schemaName);

        try{
            initSchema.initSchema(schemaName);
        }
        catch (Exception e){
            return Mono.error(e);
        }
        return Mono.justOrEmpty(schemaRepository.save(schema));
    }

    public void createSchema(String schemaName) {
        String sql = "CREATE SCHEMA " + schemaName;
        jdbcTemplate.execute(sql);
    }
}

