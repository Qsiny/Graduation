package com.qsiny.graduation.service.Impl;

import com.qsiny.graduation.enums.UserTypeEnum;
import com.qsiny.graduation.mapper.MenuMapper;
import com.qsiny.graduation.mapper.UserMapper;
import com.qsiny.graduation.pojo.LoginUser;
import com.qsiny.graduation.pojo.ResponseResult;
import com.qsiny.graduation.pojo.User;
import com.qsiny.graduation.service.RoleService;
import com.qsiny.graduation.service.UserService;
import com.qsiny.graduation.utils.JwtUtil;
import com.qsiny.graduation.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Qin
 * @description: TODO
 * @date 2021/12/5 12:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public ResponseResult addUser(User user) {

        //密码加密
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        user.setUserType(UserTypeEnum.NORMAL);
        user.setCreateTime(new Date(System.currentTimeMillis()));
        //先存入数据库
        userMapper.insert(user);
        //修改权限 在数据库添加用户角色 与用户的访问权限
        Long roleId = roleService.selectRoleIdByRoleKey("user");
        Long userId = userMapper.findUserByUsername(user.getUserName()).getId();
        roleService.connectUserAndRole(userId, roleId);

        //查询角色的权限信息
        List<String> permissions = menuMapper.selectPermsByUserId(userId);
        LoginUser loginUser = new LoginUser(user,permissions);

        //存入redis
        redisCache.setCacheObject("userid:"+userId,loginUser);
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();

        //产生token返回
        String jwt = JwtUtil.createJWT(String.valueOf(userId));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,authorities);
        //将权限信息放入securityManager
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new ResponseResult(200,"注册成功",jwt);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User checkUsernameExist(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User checkTelExist(String tel) {
        return userMapper.findUserByTel(tel);
    }

    @Override
    public ResponseResult login(String message,String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(message, password);

        //前台传给我们一个user，那我们首先要判断这个user是否真实存在
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        if(loginUser == null){
            throw new RuntimeException("用户名或密码错误");
        }

        String id = String.valueOf(loginUser.getUser().getId());
        //如果真实存在这个用户，则要颁发token
        String jwt = JwtUtil.createJWT(id);

        //把用户的信息存入redis
        redisCache.setCacheObject("userid:"+id,loginUser);
        Map<String, String> hashMap = new HashMap<>(1);
        hashMap.put("loginJwt",jwt);


        return new ResponseResult(200,"登录成功",hashMap);
    }

    @Override
    public ResponseResult logout() {
        //通过在securityContext中获取用户的信息
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        //删除redis中的信息
        String redisKey = "userid:"+user.getId();
        redisCache.deleteObject(redisKey);

        return new ResponseResult(200,"删除成功");
    }
}
