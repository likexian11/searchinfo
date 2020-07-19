package com.likexian.phone.searchinfo.mapper;

import com.likexian.phone.searchinfo.entity.SelectUser;
import org.springframework.stereotype.Component;
import tk.mybatis.MyMapper;
@Component
public interface SelectUserMapper extends MyMapper<SelectUser> {
    Integer truncateTable();
}