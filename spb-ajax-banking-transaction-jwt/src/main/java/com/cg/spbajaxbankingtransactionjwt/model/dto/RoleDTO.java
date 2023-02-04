package com.cg.spbajaxbankingtransactionjwt.model.dto;

import com.cg.spbajaxbankingtransactionjwt.model.Role;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RoleDTO {

    @NotNull(message = "The role is required")
    private Long id;

    private String code;

    public Role toRole() {
        return new Role()
                .setId(id)
                .setCode(code);
    }
}
