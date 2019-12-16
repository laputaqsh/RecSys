package com.sky.service;

import com.sky.constant.CookieConstant;
import com.sky.constant.RedisConstant;
import com.sky.dao.UserInfo;
import com.sky.exception.AuthorizeException;
import com.sky.repos.UserRepos;
import com.sky.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {

    private UserRepos repos;

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRepos(UserRepos repos) {
        this.repos = repos;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean loginCheck(int userId, String userPw) {
        UserInfo userInfo = repos.findById(userId);
        return userInfo.getUserPw().equals(userPw);
    }

    public void setUserId(HttpServletResponse response, int userId) {
        String token = UUID.randomUUID().toString();
        //设置到Redis
        redisTemplate.opsForValue().set(String.format(RedisConstant.PREFIX, token), String.valueOf(userId), RedisConstant.EXPIRE, TimeUnit.SECONDS);
        //设置到Cookie
        CookieUtil.set(response, token, CookieConstant.EXPIRE);
    }

    public int getUserId(HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request);
        if (cookie == null) {
            throw new AuthorizeException();
        }
        String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.PREFIX, cookie.getValue()));
        if (userId == null) {
            throw new AuthorizeException();
        }
        return Integer.parseInt(userId);
    }

    public void delUserId(HttpServletRequest request,
                          HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request);
        if (cookie != null) {
            //清除Redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.PREFIX, cookie.getValue()));
            //清除Cookie
            CookieUtil.set(response, null, 0);
        }
    }

}
