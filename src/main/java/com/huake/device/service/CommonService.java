package com.huake.device.service;

import com.huake.device.dao.generator.RenHeadenergyMapper;
import com.huake.device.domain.generator.RenHeadenergy;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonService {
    @Resource
    RenHeadenergyMapper renHeadenergyMapper;

    public List<RenHeadenergy> getRenHeadenergyList(){
        List<RenHeadenergy> list = renHeadenergyMapper.select(SelectDSLCompleter.allRows());
        return list;
    }
}
