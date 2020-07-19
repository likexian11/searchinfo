package com.likexian.phone.searchinfo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @program: searchinfo
 * @description:
 * @create: 2020-06-20 15:15
 **/
@Data
public class UserMatchDto extends BaseRowModel {

    @ExcelProperty(value = "查询姓名",index = 0)
    private String selectName;

    @ExcelProperty(value = "查询号码",index = 1)
    private String selectPhone;

    @ExcelProperty(value = "匹配姓名",index = 2)
    private String userName;

    @ExcelProperty(value = "匹配号码",index = 3)
    private String userPhone;
}
