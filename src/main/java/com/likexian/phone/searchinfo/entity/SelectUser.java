package com.likexian.phone.searchinfo.entity;

import javax.persistence.*;

@Table(name = "select_user")
public class SelectUser {
    @Id
    private Integer id;

    /**
     * 查找名称
     */
    @Column(name = "select_name")
    private String selectName;

    /**
     * 查找手机号
     */
    @Column(name = "select_phone")
    private String selectPhone;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取查找名称
     *
     * @return select_name - 查找名称
     */
    public String getSelectName() {
        return selectName;
    }

    /**
     * 设置查找名称
     *
     * @param selectName 查找名称
     */
    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    /**
     * 获取查找手机号
     *
     * @return select_phone - 查找手机号
     */
    public String getSelectPhone() {
        return selectPhone;
    }

    /**
     * 设置查找手机号
     *
     * @param selectPhone 查找手机号
     */
    public void setSelectPhone(String selectPhone) {
        this.selectPhone = selectPhone;
    }
}