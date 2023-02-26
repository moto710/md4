package com.spbproductmanagementjwt.controller.api;

import com.spbproductmanagementjwt.exception.DataInputException;
import com.spbproductmanagementjwt.exception.EmailExistsException;
import com.spbproductmanagementjwt.jwt.JwtResponse;
import com.spbproductmanagementjwt.role.Role;
import com.spbproductmanagementjwt.user.User;
import com.spbproductmanagementjwt.user.UserDTO;
import com.spbproductmanagementjwt.user.UserLoginDTO;
import com.spbproductmanagementjwt.jwt.JwtService;
import com.spbproductmanagementjwt.role.IRoleService;
import com.spbproductmanagementjwt.user.IUserService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final IUserService userService;

    private final IRoleService roleService;

    private final AppUtils appUtils;

    @Autowired
    public AuthAPI(AuthenticationManager authenticationManager, JwtService jwtService, IUserService userService, IRoleService roleService, AppUtils appUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.roleService = roleService;
        this.appUtils = appUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult br) {

        if (br.hasErrors()) {
            return appUtils.mapErrorToResponse(br);
        }

        Optional<Role> optRole = roleService.findById(2L);

        if (!optRole.isPresent()) {
            throw new DataInputException("Invalid role!");
        }

        Boolean existsByUsername = userService.existsByUsername(userDTO.getUsername());

        if (existsByUsername) {
            throw new EmailExistsException("Account already exists");
        }

        try {
            User user = userDTO.toUser();
            user.setRole(optRole.get());
            userService.save(user);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
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
