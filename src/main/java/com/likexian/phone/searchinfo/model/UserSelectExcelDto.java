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
public class UserSelectExcelDto extends BaseRowModel {

    @ExcelProperty(value = "姓名",index = 0)
    private String selectName;

    @ExcelProperty(value = "手机号码",index = 1)
    private String selectPhone;
}
