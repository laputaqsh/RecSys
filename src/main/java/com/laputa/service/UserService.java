package com.laputa.service;

import com.laputa.constant.CookieConstant;
import com.laputa.constant.RedisConstant;
import com.laputa.dao.User;
import com.laputa.exception.AuthorizeException;
import com.laputa.utils.CookieUtil;
import com.laputa.repos.UserRepos;
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

    private UserRepos userRepos;

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setUserRepos(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User loginCheck(String id) {
        return userRepos.findById(Integer.parseInt(id));
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
