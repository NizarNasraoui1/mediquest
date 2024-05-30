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

    private final DBInitializer initSuperAdminRole;

    private final DBInitializer initPermission;

    private final DBInitializer initSuperAdminUser;

    private final DBInitializer initDenstistQuestionnaire;
    private final InitQuestionnaires initQuestionnaires;
    private final TenantDataSource tenantDataSource;


    public InitDB(MigrateDB migrateDB, @Qualifier("initSuperAdminRole") DBInitializer initSuperAdminRole, @Qualifier("initRoles") DBInitializer initPermission, @Qualifier("initSuperAdminUser") InitSuperAdminUser initSuperAdminUser, @Qualifier("initDenstistQuestionnaire") InitDenstistQuestionnaire initDenstistQuestionnaire, InitQuestionnaires initQuestionnaires, TenantDataSource tenantDataSource) {
        this.migrateDB = migrateDB;
        this.initSuperAdminRole = initSuperAdminRole;
        this.initPermission = initPermission;
        this.initSuperAdminUser = initSuperAdminUser;
        this.initDenstistQuestionnaire = initDenstistQuestionnaire;
        this.initQuestionnaires = initQuestionnaires;
        this.tenantDataSource = tenantDataSource;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup){
            return ;
        }
        migrateDB.migrateDB();
        try{
            initSuperAdminRole.init();
            initPermission.init();
            initSuperAdminUser.init();
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
