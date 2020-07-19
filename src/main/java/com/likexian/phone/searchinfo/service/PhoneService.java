package com.likexian.phone.searchinfo.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: searchinfo
 * @description:
 * @author: herongjian
 * @create: 2020-06-20 15:00
 **/
public interface PhoneService {

    void selectUserExcel(HttpServletResponse response) throws IOException;

    void importUserExcel(MultipartFile file) throws IOException;

    void selectDateExcel(HttpServletResponse response) throws IOException;

    void importSelectExcel(MultipartFile file) throws IOException;

    void delSelectUserDate();

    void delUserDate();

    public void matchUserExcel(HttpServletResponse response) throws IOException;
}
