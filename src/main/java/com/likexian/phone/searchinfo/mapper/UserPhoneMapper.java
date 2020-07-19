package com.likexian.phone.searchinfo.mapper;

import com.likexian.phone.searchinfo.entity.UserPhone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.MyMapper;

@Component
public interface UserPhoneMapper extends MyMapper<UserPhone> {

    Integer truncateTable();

}