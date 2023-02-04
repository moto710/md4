package com.cg.spbajaxbankingtransactionjwt.service.user;

import com.cg.spbajaxbankingtransactionjwt.model.User;
import com.cg.spbajaxbankingtransactionjwt.model.dto.UserDTO;
import com.cg.spbajaxbankingtransactionjwt.service.IGeneral;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneral<User>, UserDetailsService {

    Boolean existByUsername (String username);
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsUsersByUsername(String username);
}
