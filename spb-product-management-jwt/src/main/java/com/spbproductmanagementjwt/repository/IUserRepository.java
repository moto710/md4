package com.spbproductmanagementjwt.repository;

import com.spbproductmanagementjwt.model.User;
import com.spbproductmanagementjwt.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT NEW com.spbproductmanagementjwt.model.dto.UserDTO (" +
                "u.id, " +
                "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);



    @Modifying
    @Query("UPDATE User AS u " +
            "SET u.deleted = true " +
            "WHERE u.id = :userId"
    )
    void deactivate(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User AS u " +
            "SET u.deleted = false " +
            "WHERE u.id = :userId"
    )
    void reactivate(@Param("userId") Long userId);
}
