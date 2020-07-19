package com.likexian.phone.searchinfo.controller;

import com.likexian.phone.searchinfo.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: searchinfo
 * @description:
 * @create: 2020-06-20 14:58
 **/
@Controller
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 下载匹配excel数据
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/selectDateExcel")
    public void selectDateExcel(HttpServletResponse response) throws IOException {
        phoneService.selectDateExcel(response);
    }

    /**
     * 导入匹配excel数据
     * @param file
     * @return
     * @throws IOException
     */
    //导入excle
    @PostMapping("/user/importSelectExcel")
    @ResponseBody
    public Object importSelectExcel(@RequestParam("fileUpload") MultipartFile file) throws IOException {
        phoneService.importSelectExcel(file);
        Map map = new HashMap<Integer,String>();
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    /**
     * 清空查询用户数据
     */
    @GetMapping("/user/delSelectUserDate")
    @ResponseBody
    public Object delSelectUserDate(){
        phoneService.delSelectUserDate();
        return new HashMap();
    }

    /**
     * 下载用户数据
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/selectUserExcel")
    public void selectUserExcel(HttpServletResponse response) throws IOException {
        phoneService.selectUserExcel(response);
    }


    //导入excle
    /**
     * 导入用户数据
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/user/importUserExcel")
    @ResponseBody
    public Object importUserExcel(@RequestParam("fileUpload") MultipartFile file) throws IOException {
        phoneService.importUserExcel(file);
        Map map = new HashMap<Integer,String>();
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    /**
     * 清空查询用户数据
     */
    @GetMapping("/user/delUserDate")
    @ResponseBody
    public Object delUserDate(){
        phoneService.delUserDate();
        return new HashMap();
    }

    /**
     * 匹配用户数据
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/matchUserExcel")
    public void matchUserExcel(HttpServletResponse response) throws IOException {
        phoneService.matchUserExcel(response);
    }


}
