package com.cg.spbajaxbankingtransactionjwt.controller.api;

import com.cg.spbajaxbankingtransactionjwt.model.dto.UserLoginDTO;
import com.cg.spbajaxbankingtransactionjwt.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    @Autowired
    private IUserService userService;

    @PostMapping("")
    private ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        System.out.println("htrinrtnrtnr");
        System.out.println(userService.existsUsersByUsername(userLoginDTO.getUsername()));
        if (userService.existsUsersByUsername(userLoginDTO.getUsername())) {
            return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
        }
        return null;
    }
}
