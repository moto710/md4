package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.exception.DataInputException;
import com.spbproductmanagementjwt.exception.EmailExistsException;
import com.spbproductmanagementjwt.model.JwtResponse;
import com.spbproductmanagementjwt.model.Role;
import com.spbproductmanagementjwt.model.User;
import com.spbproductmanagementjwt.model.dto.UserDTO;
import com.spbproductmanagementjwt.model.dto.UserLoginDTO;
import com.spbproductmanagementjwt.service.jwt.JwtService;
import com.spbproductmanagementjwt.service.role.IRoleService;
import com.spbproductmanagementjwt.service.user.IUserService;
import com.spbproductmanagementjwt.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult br) {

        if (br.hasErrors()) {
            System.out.println("register error");
            return appUtils.mapErrorToResponse(br);
        }

        Long roleId = userDTO.getRole().getId();

        Optional<Role> optRole = roleService.findById(roleId);

        if (!optRole.isPresent()) {
            throw new DataInputException("Invalid role!");
        }

        Boolean existsByUsername = userService.existsByUsername(userDTO.getUsername());

        if (existsByUsername) {
            throw new EmailExistsException("Account already exists");
        }

        try {
            userService.save(userDTO.toUser());

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        System.out.println(userLoginDTO);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.getByUsername(userLoginDTO.getUsername());

            JwtResponse jwtResponse = new JwtResponse(
                    jwt,
                    currentUser.getId(),
                    userDetails.getUsername(),
                    currentUser.getUsername(),
                    userDetails.getAuthorities()
            );

            ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(60 * 1000)
                    .domain("localhost")
                    .build();

            System.out.println(jwtResponse);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                    .body(jwtResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
