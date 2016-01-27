package com.lins4tech.locaimob.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.lins4tech.locaimob.account.UserService;
import com.lins4tech.locaimob.services.UsuarioService;

@Configuration
@EnableWebMvcSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UsuarioService userService() {
        return new UsuarioService();
    }

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", userService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true).userDetailsService(userService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/", "/favicon.ico", "/resources/**", "/signup").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/signin")
//                .permitAll()
//                .failureUrl("/signin?error=1")
//                .loginProcessingUrl("/authenticate")
//                .and()
//            .logout()
//                .logoutUrl("/logout")
//                .permitAll()
//                .logoutSuccessUrl("/signin?logout")
//                .and()
//            .rememberMe()
//                .rememberMeServices(rememberMeServices())
//                .key("remember-me-key");
        http.csrf().disable(); // Adicionado para usar o $http.post no angularjs
    }
}