package com.cg.spbajaxbankingtransactionjwt.repository;

import com.cg.spbajaxbankingtransactionjwt.model.User;
import com.cg.spbajaxbankingtransactionjwt.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);


    @Query("SELECT NEW com.cg.spbajaxbankingtransactionjwt.model.dto.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = :username"
    )
    Optional<UserDTO> findUserDTOByUsername(@Param("username")String username);


    Boolean existsUsersByUsername(String username);

    Boolean existsByUsername (String username);
}
