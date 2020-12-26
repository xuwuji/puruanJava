package com.puruan.dao;

import com.puruan.mapper.CustomerMapper;
import com.puruan.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private CustomerMapper customerMapper;

    public Customer getCustomerById(String id) {
        return customerMapper.getCustomerById(Integer.valueOf(id));
    }

}
