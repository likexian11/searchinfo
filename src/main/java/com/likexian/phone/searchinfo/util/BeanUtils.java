package com.likexian.phone.searchinfo.util;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: searchinfo
 * @description:
 * @create: 2020-06-20 15:31
 **/
public class BeanUtils {

    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

}
