package com.puruan.controller;

import com.puruan.dao.CustomerDao;
import com.puruan.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping(value = "/getByID", method = RequestMethod.GET)
    public Customer getByID(@RequestParam(value = "id") String id) {
        return customerDao.getCustomerById(id);
    }

}
