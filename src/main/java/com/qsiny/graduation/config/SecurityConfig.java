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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

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

    @Resource
    private DataSource dataSource;

    /**
     * 校验登录时的用户密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsServiceImpl;
    }

    @Override
    public void configure(WebSecurity web)  {
        web.ignoring().antMatchers("/css/**","/images/**","jquery/**","/toRegis","/regis","/","/index");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        配置错误页面


        http
//                注销相关
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login.html")
                    .deleteCookies("remember-me")
                    .invalidateHttpSession(true)
                .and()
                //                权限相关
                .authorizeRequests()
                    .antMatchers("/user/**").hasAuthority("user")
                    .antMatchers("/student/**").hasAuthority("student")
                    .antMatchers("/teacher/**").hasRole("teacher")
                    .anyRequest().authenticated()
                //配置自动登录的东西
                .and()
                    .rememberMe().tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(60*60*24*7).userDetailsService(userDetailsServiceImpl).key("Qsiny")
                .and()
//                登录相关
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/index.html")
                .permitAll()

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

//TERTSDdhNWxmc1NBYlpOdlFTUDFzZyUzRCUzRDpBZGZCJTJGS0ElMkZnV01WY1JQa0Rxamd2USUzRCUzRA
//TERTSDdhNWxmc1NBYlpOdlFTUDFzZyUzRCUzRDo1T1RRVnRZc2drdWhkNVElMkZSY2FXeVElM0QlM0Q

    /**
     * 免密登录
     * @return PersistentTokenRepository，保存cookie到客户端
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


}
