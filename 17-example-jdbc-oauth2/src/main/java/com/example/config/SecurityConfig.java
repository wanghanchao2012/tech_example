package com.example.config;

import com.example.dto.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Bean(name = "userDetailsService")
    @Override
    protected UserDetailsService userDetailsService() {
        return createUserDetailsService();
    }

    private UserDetailsService createUserDetailsService() {
        List<Users> query = namedParameterJdbcTemplate.query("select * from users ", new BeanPropertyRowMapper
                <>(Users.class));

        List<UserDetails> users = new ArrayList<>();
        query.forEach(record -> {
            /**
             * 利用passwordEncoder().encode(record.getPassword())来生成密码密闻存于数据库
             */
            String password = record.getPassword();
            UserDetails userDetails = User.withUsername(record.getUsername()).password(password).authorities("ADMIN", "USER").build();
            users.add(userDetails);
        });
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();//生产token的url允许任何登录的用户访问
    }

    /**
     * 重新实例化bean
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
