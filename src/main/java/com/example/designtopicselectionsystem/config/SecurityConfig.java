package com.example.designtopicselectionsystem.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 创建一个密码编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 使用内存身份认证
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("admin")
                .password(encoder.encode("123456"))
                .roles("admin");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 放行静态资源和api
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/icon/*").permitAll()
                .antMatchers("/font-awesome/*").permitAll()
                .antMatchers("/fonts/*").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated();

        // 自定义用户登陆控制
        http.formLogin()
                .loginPage("/admin/login").permitAll()
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/admin")
                .failureUrl("/admin/login?error");

        // 自定义用户退出
        http.logout()
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login");

        http.csrf().disable(); // 关闭csrf攻击
    }
}
