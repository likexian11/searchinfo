package com.likexian.phone.searchinfo.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.likexian.phone.searchinfo.entity.UserPhone;
import com.likexian.phone.searchinfo.mapper.UserPhoneMapper;
import com.likexian.phone.searchinfo.model.UserExcelDto;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<UserExcelDto> {
    private List<UserExcelDto> datas = new ArrayList<>();
    private static final int BATCH_COUNT = 3000;
    private UserPhoneMapper userPhoneMapper;

    public ExcelListener(UserPhoneMapper userPhoneMapper){
        this.userPhoneMapper = userPhoneMapper;
    }

    @Override
    public void invoke(UserExcelDto user, AnalysisContext analysisContext) {
        //数据存储到datas，供批量处理，或后续自己业务逻辑处理。
        datas.add(user);
        //达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(datas.size() >= BATCH_COUNT){
            saveData();
            // 存储完成清理datas
            datas.clear();
        }
    }

    private void saveData() {
//        userPhoneMapper.truncateTable();
        for(UserExcelDto user : datas){
            if(StringUtil.isNotEmpty(user.getUserPhone()) &&  StringUtil.isNotEmpty(user.getUserPhone())){
                UserPhone userPhone = new UserPhone();
                BeanUtil.copyProperties(user,userPhone);
                userPhoneMapper.insert(userPhone);
            }
        }
    }

    public List<UserExcelDto> getDatas() {
        return datas;
    }

    public void setDatas(List<UserExcelDto> datas) {
        this.datas = datas;
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();//确保所有数据都能入库
    }
}
