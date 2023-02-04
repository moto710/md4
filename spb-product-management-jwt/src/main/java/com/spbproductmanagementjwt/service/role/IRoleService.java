package com.spbproductmanagementjwt.service.role;

import com.spbproductmanagementjwt.model.Role;
import com.spbproductmanagementjwt.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {

    Role findByName(String name);
}
