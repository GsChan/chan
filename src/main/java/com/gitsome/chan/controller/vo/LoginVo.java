package com.gitsome.chan.controller.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


@Data
public class LoginVo {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
