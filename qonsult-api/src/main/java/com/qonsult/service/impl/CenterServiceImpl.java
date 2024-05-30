package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.DataSourceBasedMultiTenantConnectionProviderImpl;
import com.qonsult.config.tenant_config.TenantDataSource;
import com.qonsult.dto.CenterDTO;
import com.qonsult.entity.Center;
import com.qonsult.entity.Schema;
import com.qonsult.generic.GenericServiceImpl;
import com.qonsult.init.InitSchema;
import com.qonsult.init.MigrateDB;
import com.qonsult.mapper.CenterMapper;
import com.qonsult.repository.CenterRepository;
import com.qonsult.repository.SchemaRepository;
import com.qonsult.service.CenterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.scheduler.Schedulers;

@Service
public class CenterServiceImpl extends GenericServiceImpl<Center, CenterDTO,Long, CenterRepository, CenterMapper> implements CenterService {

    private final JdbcTemplate jdbcTemplate;

    private final DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;
    private final SchemaRepository schemaRepository;
    private final TenantDataSource tenantDataSource;
    private final PasswordEncoder passwordEncoder;

    private final MigrateDB migrateDB;

    private final InitSchema initSchema;

    @Value("${init-database-file-path}")
    private String initDatabaseFilePath;



    public CenterServiceImpl(final CenterRepository repository, final CenterMapper mapper, JdbcTemplate jdbcTemplate, DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider, SchemaRepository schemaRepository, TenantDataSource tenantDataSource, PasswordEncoder passwordEncoder, MigrateDB migrateDB, InitSchema initSchema) {
        super(repository, mapper);
        this.jdbcTemplate = jdbcTemplate;
        this.dataSourceBasedMultiTenantConnectionProvider = dataSourceBasedMultiTenantConnectionProvider;
        this.schemaRepository = schemaRepository;
        this.tenantDataSource = tenantDataSource;
        this.passwordEncoder = passwordEncoder;
        this.migrateDB = migrateDB;
        this.initSchema = initSchema;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Mono<Center> addCenter(CenterDTO centerDTO,String schemaName) {
        if(this.repository.findByName(centerDTO.getName()).isPresent()){
            return Mono.error(new Exception("Center already exists"));
        }
        Center center = this.mapper.toBo(centerDTO);
        return Mono.justOrEmpty(this.repository.save(center)).subscribeOn(Schedulers.boundedElastic());
    }
}
