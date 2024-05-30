package com.qonsult.init;

import com.qonsult.exception.RoleAlreadyExistsException;

public interface DBInitializer {
    public void init();
    public boolean isAlreadyInitialized();
}
