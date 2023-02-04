package com.cg.spbajaxbankingtransactionjwt.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLoginDTO {

    private String username;

    private String password;
}
