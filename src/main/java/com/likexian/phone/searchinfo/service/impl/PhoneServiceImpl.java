package com.likexian.phone.searchinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.likexian.phone.searchinfo.entity.SelectUser;
import com.likexian.phone.searchinfo.entity.UserPhone;
import com.likexian.phone.searchinfo.listener.ExcelListener;
import com.likexian.phone.searchinfo.listener.ExcelSelectListener;
import com.likexian.phone.searchinfo.mapper.SelectUserMapper;
import com.likexian.phone.searchinfo.mapper.UserPhoneMapper;
import com.likexian.phone.searchinfo.model.UserExcelDto;
import com.likexian.phone.searchinfo.model.UserMatchDto;
import com.likexian.phone.searchinfo.model.UserSelectExcelDto;
import com.likexian.phone.searchinfo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: searchinfo
 * @description:
 * @create: 2020-06-20 15:01
 **/
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    UserPhoneMapper userPhoneMapper;
    @Autowired
    SelectUserMapper selectUserMapper;

    @Override
    public void selectDateExcel(HttpServletResponse response) throws IOException {
        ExcelWriter writer = null;
        // 文件输出位置
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            String fileName = "匹配信息";
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet = new Sheet(1, 0, UserSelectExcelDto.class);
            // 第一个 sheet 名称
            sheet.setSheetName("匹配信息");
            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            List<SelectUser> selectUsers = selectUserMapper.selectAll();
            List<UserSelectExcelDto> userSelectExcelDtos = new ArrayList<>();
            for(SelectUser selectUser : selectUsers){
                UserSelectExcelDto userSelectExcelDto = new UserSelectExcelDto();
                BeanUtil.copyProperties(selectUser,userSelectExcelDto);
                userSelectExcelDtos.add(userSelectExcelDto);
            };
            writer.write(userSelectExcelDtos, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                // 将上下文中的最终 outputStream 写入到指定文件中
                writer.finish();
            }
            if (out != null) {
                try {
                    // 关闭流
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void importSelectExcel(MultipartFile file) throws IOException {
//        if(!file.getOriginalFilename().equals("用户名单.xls") && !file.getOriginalFilename().equals("用户名单.xlsx") ){
//            return;
//        }
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        //实例化实现了AnalysisEventListener接口的类
        ExcelSelectListener excelSelectListener = new ExcelSelectListener(selectUserMapper);
        ExcelReader reader = new ExcelReader(inputStream,null,excelSelectListener);
        //读取信息
        reader.read(new Sheet(1,1,UserSelectExcelDto.class));
    }



    @Override
    public void selectUserExcel(HttpServletResponse response) throws IOException {
        ExcelWriter writer = null;
        // 文件输出位置
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            String fileName = "用户信息";
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet = new Sheet(1, 0, UserExcelDto.class);
            // 第一个 sheet 名称
            sheet.setSheetName("用户信息");
            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            List<UserPhone> userPhones = userPhoneMapper.selectAll();
            List<UserExcelDto> userExcelDtos = new ArrayList<>();
            for(UserPhone userPhone : userPhones){
                UserExcelDto userExcelDto = new UserExcelDto();
                BeanUtil.copyProperties(userPhone,userExcelDto);
                userExcelDtos.add(userExcelDto);
            };
            writer.write(userExcelDtos, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                // 将上下文中的最终 outputStream 写入到指定文件中
                writer.finish();
            }
            if (out != null) {
                try {
                    // 关闭流
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void importUserExcel(MultipartFile file) throws IOException {
//        if(!file.getOriginalFilename().equals("用户名单.xls") && !file.getOriginalFilename().equals("用户名单.xlsx") ){
//            return;
//        }
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener excelListener = new ExcelListener(userPhoneMapper);
        ExcelReader reader = new ExcelReader(inputStream,null,excelListener);
        //读取信息
        reader.read(new Sheet(1,1,UserExcelDto.class));
    }

    @Override
    public void delSelectUserDate() {
        selectUserMapper.truncateTable();
    }

    @Override
    public void delUserDate() {
        userPhoneMapper.truncateTable();
    }


    @Override
    public void matchUserExcel(HttpServletResponse response) throws IOException {
        ExcelWriter writer = null;
        // 文件输出位置
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            String fileName = "匹配信息"+ DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT);
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet = new Sheet(1, 0, UserMatchDto.class);
            // 第一个 sheet 名称
            sheet.setSheetName("sheet");
            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            List<UserMatchDto> userMatchDtos = queryMatchUser();
            writer.write(userMatchDtos, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                // 将上下文中的最终 outputStream 写入到指定文件中
                writer.finish();
            }
            if (out != null) {
                try {
                    // 关闭流
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    List<UserMatchDto> queryMatchUser(){
        List<UserMatchDto> userMatchDtos = new ArrayList<>();
        List<UserPhone> userPhones = userPhoneMapper.selectAll();
        List<SelectUser> selectUsers = selectUserMapper.selectAll();
        for(SelectUser selectUser:selectUsers){
            String phone[] = selectUser.getSelectPhone().split("-");
            for(UserPhone userPhone:userPhones){
                if(userPhone.getUserName().contains(selectUser.getSelectName().substring(0,1))
                    && phone.length ==2
                    && userPhone.getUserPhone().contains(phone[0])
                    && userPhone.getUserPhone().contains(phone[1])
                ){
                    UserMatchDto userMatchDto = new UserMatchDto();
                    BeanUtil.copyProperties(selectUser,userMatchDto);
                    userMatchDto.setUserName(userPhone.getUserName());
                    userMatchDto.setUserPhone(userPhone.getUserPhone());
                    userMatchDtos.add(userMatchDto);
//                    break;
                }
            }

        }
        return userMatchDtos;
    }

}

