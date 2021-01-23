package com.puruan.bloomFilter;

import com.puruan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Component
public class BloomFilterInit implements ApplicationRunner {

    @Autowired
    private BloomUtil bloomUtil;

    @Autowired
    private UserMapper userMapper;

    // 存放username的布隆过滤器
    public static BitSet username_bitset = new BitSet(BloomUtil.DEFAULT_SIZE);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // username布隆过滤器冷启动
        this.addUsername();
    }

    // 将user表里的name字段进行冷启动
    public void addUsername() {
        // 1. 遍历数据库 -> 得到所有的name
        List<String> usernames = new ArrayList<>();
        // System.out.println("users:" + userMapper.getAll());
        userMapper.getAll().forEach(user -> {
            usernames.add(user.getUsername());
        });
        // 2. 把name放到布隆过滤器里面
        usernames.forEach(name -> {
            // System.out.println(name);
            bloomUtil.add(username_bitset, name);
        });
    }


}
