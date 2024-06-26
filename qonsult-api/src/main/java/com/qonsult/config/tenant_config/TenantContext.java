package com.qonsult.config.tenant_config;

public class TenantContext {
	private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

	public static String getCurrentTenant() {
		return currentTenant.get();
	}

	public static void setCurrentTenant(String tenant) {
		currentTenant.set(tenant);
	}

	public static void clear() {
		currentTenant.remove();
	}
}
