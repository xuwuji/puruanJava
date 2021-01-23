package com.puruan.bloomFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloomFilterService {

    @Autowired
    private BloomUtil bloomUtil;

    public boolean containsUsername(String username) {
        return bloomUtil.contains(BloomFilterInit.username_bitset, username);
    }
}
