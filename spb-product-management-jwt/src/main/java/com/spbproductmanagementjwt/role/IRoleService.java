package com.spbproductmanagementjwt.role;

import com.spbproductmanagementjwt.role.Role;
import com.spbproductmanagementjwt.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {

    Role findByName(String name);
}
