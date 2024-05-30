package com.qonsult.init;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.config.tenant_config.TenantDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements ApplicationListener<ContextRefreshedEvent> {

    private static boolean alreadySetup = false;
    private final MigrateDB migrateDB;
    private final DBInitializer initRoles;
    private final DBInitializer initPublicSchema;
    private final InitQuestionnaires initQuestionnaires;
    private final TenantDataSource tenantDataSource;


    public InitDB(MigrateDB migrateDB,
                  @Qualifier("initPublicSchema")
                  DBInitializer initPublicSchema,
                  @Qualifier("initRoles") DBInitializer initRoles,
                  InitQuestionnaires initQuestionnaires,
                  TenantDataSource tenantDataSource) {
        this.migrateDB = migrateDB;
        this.initRoles = initRoles;
        this.initQuestionnaires = initQuestionnaires;
        this.tenantDataSource = tenantDataSource;
        this.initPublicSchema = initPublicSchema;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup){
            return ;
        }
        migrateDB.migrateDB();
        initRoles.init();
        initPublicSchema.init();
        try{
            initRoles.init();
            for (String tenant : tenantDataSource.getAllTenantDS().keySet()){
                TenantContext.clear();
                TenantContext.setCurrentTenant(tenant);
                initQuestionnaires.init();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        alreadySetup = true;
    }


}
