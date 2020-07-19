package com.likexian.phone.searchinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="com.likexian.phone.searchinfo.mapper")
public class SearchinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchinfoApplication.class, args);
    }

}
