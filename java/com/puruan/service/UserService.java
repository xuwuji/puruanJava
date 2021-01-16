package com.puruan.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puruan.mapper.UserMapper;
import com.puruan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper mapper;

    public static final String USER_KEY_PREFIX = "user_";
    public static final String USER_VOTE_KEY_PREFIX = "user_vote_";
    public static final String VOTE_COUNT_SET = "vote_count_set";

    public User getByID(String id) throws IOException {
        User user = new User();
        String key = USER_KEY_PREFIX + id;
        // 判断缓存中是否已经存在，存在则直接返回
        if (redisTemplate.hasKey(key)) {
            System.out.println("--- hit redis cache ----");
            String json = redisTemplate.opsForValue().get(USER_KEY_PREFIX + id).toString();
            System.out.println(json);
            user = mapper.readValue(json, User.class);
        }
        // 不存在则去DB中查询，然后回写到redis缓存中
        else {
            System.out.println("--- no data in redis cache ----");
            user = userMapper.findUserById(Integer.valueOf(id));
            String jsonStr = mapper.writeValueAsString(user);
            redisTemplate.opsForValue().set(key, jsonStr);
        }
        return user;
    }

    public void vote(String id) {
        // 将对应id的分数加1
        redisTemplate.opsForZSet().incrementScore(VOTE_COUNT_SET, id, 1);
    }

    public HashMap<String, Double> getRank() {
        HashMap<String, Double> ranks = new HashMap<>();
        // set中的元素个数
        Long count = redisTemplate.opsForZSet().zCard(VOTE_COUNT_SET);
        // 倒序获得排名，分数大的在前面
        Set<String> ids = redisTemplate.opsForZSet().reverseRange(VOTE_COUNT_SET, 0, count);
        // 获得每个元素对应的分数
        for (String id : ids) {
            ranks.put(id, redisTemplate.opsForZSet().score(VOTE_COUNT_SET, id));
        }
        return ranks;
    }

    public void updateUser(User user) {
        String id = String.valueOf(user.getId());
        // 缓存一致性
        // 1. 让缓存内的数据失效/清空对应的数据
        
        // 2. 更新数据库

        // 3. 回写到redis内

    }
}
