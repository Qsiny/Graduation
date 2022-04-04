package com.qsiny.graduation.config;

import com.qsiny.graduation.filter.JwtAuthenticationTokenFilter;
import com.qsiny.graduation.handler.AccessDeniedHandlerImpl;
import com.qsiny.graduation.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/10 17:57
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsServiceImpl;

    @Resource
    private DataSource dataSource;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

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
        web.ignoring().antMatchers("/css/**","/images/**","/jquery/**","/#vCode","/bootstrap/**","/toRegis","/regis/**","/","/index","/temp","/regis","/user/login","/user/logout","/toLogin");
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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //                权限相关
                .authorizeRequests()
                //  没有登录过才可以访问
                    .antMatchers("/user/login").anonymous()
                //其他的都需要认证过
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
                    .defaultSuccessUrl("/loginSuccess")
                .permitAll()
                .and()
                    .csrf().disable();

        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).
                accessDeniedHandler(accessDeniedHandler);

    }

    /**
     * 这个地方是配置权限的，默认有了student的人也拥有了user权限
     * @return RoleHierarchy
     */
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
