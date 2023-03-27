package com.spbproductmanagementjwt.user;

import com.spbproductmanagementjwt.role.IRoleRepository;
import com.spbproductmanagementjwt.role.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    public IUserRepository underTest;

    @Autowired
    IRoleRepository roleRepository;

//    @BeforeEach
//    void setUp() {
//        Role role = new Role();
//        User user = new User(100L, "userTest", "passTest", role);
//
//        roleRepository.save(role);
//        underTest.save(user);
//    }

    @Test
    void testFindUserDTOByUsername() {
        //given
        String username = "userTest";

        Role role = new Role();
        User user = new User(1L, "userTest", "passTest", role);

        roleRepository.save(role);
        underTest.save(user);

        UserDTO expected = UserMapper.userMapUserDTO(user);

//        UserDTO expected = underTest.findById(100L).orElseThrow(NullPointerException::new).toUserDTO();
        //when

        UserDTO actual = underTest.findUserDTOByUsername(username).orElseThrow(NullPointerException::new);

        // .orElseThrow(() -> new RuntimeException("User not found!"));
        //then
        assertThat(expected.getId()).isEqualTo(actual.getId());
        assertThat(expected.getUsername()).isEqualTo(actual.getUsername());
    }

    @Test
    void falseIfUsernameNotExist() {
        //given
        String username = "userTest";

        //when
        Boolean expected = underTest.existsByUsername(username);

        //then
        assertThat(expected).isTrue();
    }
}