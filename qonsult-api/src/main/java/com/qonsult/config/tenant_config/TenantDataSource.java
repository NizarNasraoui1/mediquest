package com.qonsult.config.tenant_config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.qonsult.entity.Schema;
import com.qonsult.repository.SchemaRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;


@Component
public class TenantDataSource implements Serializable {

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	private static final long serialVersionUID = -2418234625461365801L;
	private Map<String, DataSource> dataSources = new HashMap<>();

	private List<Schema> dbDetails;

	private Map<String,String> usernameSchemaMap;

	@Autowired
	private SchemaRepository schemaRepository;

	@PostConstruct
	public Map<String, DataSource> getAllTenantDS() {
		List<Schema> schemas =schemaRepository.findAll();
		usernameSchemaMap = schemas.stream().collect(Collectors.toMap(Schema::getUserName,Schema::getName));
		Map<String, DataSource> result = schemas.stream()
				.collect(Collectors.toMap(Schema::getName, db -> getDataSource(db.getName())));

		return result;
	}

	public List<Schema> getDbDetails() {
		return dbDetails;
	}

	public Map<String, String> getUsernameSchemaMap() {
		return usernameSchemaMap;
	}

	public DataSource getDataSource(String schema) {
		if (dataSources.get(schema) != null) {
			return dataSources.get(schema);
		}
		DataSource dataSource = createDataSource(schema);
		if (dataSource != null) {
			dataSources.put(schema, dataSource);
		}
		return dataSource;
	}

	private DataSource createDataSource(String tenant) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.postgresql.Driver");
		hikariConfig.setJdbcUrl(datasourceUrl);
		hikariConfig.setSchema(tenant);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(5);
		return new HikariDataSource(hikariConfig);
	}
}
