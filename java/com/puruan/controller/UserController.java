package com.puruan.controller;

import com.github.pagehelper.PageHelper;
import com.puruan.mapper.UserMapper;
import com.puruan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/getByID", method = RequestMethod.GET)
    public User getByID(@RequestParam(value = "id") String id) {
        return userMapper.findUserById(Integer.valueOf(id));
    }


    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public List<User> getByName(@RequestParam(value = "name") String name) {
        return userMapper.findUserByName(name);
    }

    @RequestMapping(value = "/findUserByCondition", method = RequestMethod.GET)
    public List<User> findUserByCondition(@RequestParam(value = "name") String name, @RequestParam(value = "sex", required = false) String sex) {
        return userMapper.findUserByCondition(name, sex);
    }

    @RequestMapping(value = "/findUserByPage", method = RequestMethod.GET)
    public List<User> findUserByPage(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userMapper.findUserByPage();
    }
}
