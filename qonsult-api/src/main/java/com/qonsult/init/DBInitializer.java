package com.qonsult.init;

import com.qonsult.exception.RoleAlreadyExistsException;

public interface DBInitializer {
    public void init() throws RoleAlreadyExistsException;
    public boolean isAlreadyInitialized();
}
