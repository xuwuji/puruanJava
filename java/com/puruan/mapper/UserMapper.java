package com.puruan.mapper;

import com.github.pagehelper.Page;
import com.puruan.model.User;

import java.util.List;

public interface UserMapper {

    public User findUserById(int id);

    public List<User> findUserByName(String name);

    public List<User> findUserByCondition(String name, String sex, List<String> addressList);

    public Page<User> findUserByPage();

    public List<User> getAll();
}
