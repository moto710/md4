package com.cg.spbajaxbankingtransactionjwt.service.role;

import com.cg.spbajaxbankingtransactionjwt.model.Role;
import com.cg.spbajaxbankingtransactionjwt.service.IGeneral;

public interface IRoleService extends IGeneral<Role> {

    Role findByName(String name);
}
