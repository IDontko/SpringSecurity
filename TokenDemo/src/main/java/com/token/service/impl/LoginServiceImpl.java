package com.token.service.impl;

import com.token.domain.LoginUser;
import com.token.domain.ResponseResult;
import com.token.domain.User;
import com.token.mapper.UserMapper;
import com.token.service.LoginService;
import com.token.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        //进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证没通过，给提示
        if (authenticate == null){
            throw new RuntimeException("登陆失败");
        }
        //如果通过，用userId生成一个jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

        //把完成的用户信息存入redis userId作为key
        return new ResponseResult(200, "登陆成功", map);
    }
}
