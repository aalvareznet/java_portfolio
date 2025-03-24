package com.uia.ing.soft.olda.dunamys.ing_software_dunamys;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.AuthController;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.AuthResponse;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.AuthService;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.auth.LoginRequest;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.config.ApplicationConfig;
import com.uia.ing.soft.olda.dunamys.ing_software_dunamys.Security.config.SecurityConfig;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, ApplicationConfig.class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private AuthService authService;

    @Test
    void testLogin_Exitoso() throws Exception {
        LoginRequest request = new LoginRequest("user", "pass");
        AuthResponse response = new AuthResponse("token_jwt");
        
        when(authService.login(request)).thenReturn(response);
        
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
