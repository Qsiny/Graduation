package com.qsiny.graduation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/10 17:57
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsServiceImpl;

    /**
     * 校验登录时的用户密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("qin").password("123456").roles("student");
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/css/**","/images/**","jquery/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/index.html")
                .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/user/**").hasAuthority("user")
                    .antMatchers("/student/**").hasAuthority("student")
                    .antMatchers("/teacher/**").hasRole("teacher")
                    .anyRequest().authenticated()

                .and()
                    .csrf().disable();

    }

    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_student > ROLE_user");
        roleHierarchy.setHierarchy("ROLE_teacher > ROLE_user");
        return roleHierarchy;
    }


}
