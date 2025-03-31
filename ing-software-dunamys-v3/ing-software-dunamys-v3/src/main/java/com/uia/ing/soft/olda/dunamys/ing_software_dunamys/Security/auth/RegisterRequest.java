package com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth;


import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    Role role;
}
