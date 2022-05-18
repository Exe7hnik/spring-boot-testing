package ru.alishev.springcourse.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

import java.util.Scanner;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // конфигурируем сам Spring Security
        // конфигурируем авторизацию
        http.authorizeRequests()
                .antMatchers(
                        "/static/images/**",
                        "/static/css/**",
                        "/static/images/**",
                        "/static/fonts/**"
                        ).permitAll()
                /*.antMatchers(HttpMethod.PATCH,"/admin","/admin/**").hasRole("ADMIN")*/
                .antMatchers("/", "/error", "/cars", "/cars/**").not().fullyAuthenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/profile").hasRole("USER")
                .antMatchers("/auth/login", "/auth/registration", "/error", "/", "/cars", "/cars/**").permitAll()
                    .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin().loginPage("/auth/login")
                    .loginProcessingUrl("/process_login")
                    .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
                //.logoutSuccessUrl("/auth/login");
        http.csrf().disable();
    }

    // Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
