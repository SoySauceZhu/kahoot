package mingjie.kahoot.userservice.config;

import mingjie.kahoot.userservice.dao.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ApplicationConfig {
    private UserMapper userMapper;

    @Bean
    public UserDetailsService userDetailsService() {
        // It provides a clear and concise way to implement single-method interfaces
        // (functional interfaces) using an expression.
        return username -> userMapper.findByEmail(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());

    }


}
