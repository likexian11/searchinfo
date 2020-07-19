package tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * mybatis通用接口
 * 自己得mapper该接口不能被spring扫描到，否则报错
 * */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {}
