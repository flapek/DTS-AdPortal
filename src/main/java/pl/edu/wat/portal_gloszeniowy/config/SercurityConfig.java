package pl.edu.wat.portal_gloszeniowy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    //Do not use this in production enviorment
    //Or set up proper security measures
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/treningi").permitAll();


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }



}
