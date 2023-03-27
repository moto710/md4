package com.spbproductmanagementjwt.user;

import com.spbproductmanagementjwt.role.IRoleRepository;
import com.spbproductmanagementjwt.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.spbproductmanagementjwt.role.ERole.ROLE_USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserMapperTest {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Test
    void testUserDTOMapUser() {
        //given
        Role role = new Role(1L, "USER", ROLE_USER);
        User actual = new User()
                .setId(100L)
                .setUsername("userTest")
                .setPassword("passTest")
                .setRole(role);

        roleRepository.save(role);
        userRepository.save(actual);

        //when
        UserDTO userDTO = UserMapper.userMapUserDTO(actual);

        User expected = UserMapper.userDTOMapUser(userDTO);

        //then
        assertThat(expected.getId()).isEqualTo(actual.getId());
        assertThat(expected.getUsername()).isEqualTo(actual.getUsername());
        assertThat(expected.getPassword()).isEqualTo(actual.getPassword());
        assertThat(expected.getRole().getId()).isEqualTo(actual.getRole().getId());
        assertThat(expected.getRole().getCode()).isEqualTo(actual.getRole().getCode());
    }

    @Test
    void testUserMapUserDTO() {
        //given
        Role role = new Role(1L, "USER", ROLE_USER);
        User actual = new User()
                .setId(100L)
                .setUsername("userTest")
                .setPassword("passTest")
                .setRole(role);

        roleRepository.save(role);
        userRepository.save(actual);

        //when
        UserDTO expected = UserMapper.userMapUserDTO(actual);

        //then
        assertThat(expected.getId()).isEqualTo(actual.getId());
        assertThat(expected.getUsername()).isEqualTo(actual.getUsername());
        assertThat(expected.getPassword()).isEqualTo(actual.getPassword());
        assertThat(expected.getRole().getId()).isEqualTo(actual.getRole().getId());
        assertThat(expected.getRole().getCode()).isEqualTo(actual.getRole().getCode());
    }
}