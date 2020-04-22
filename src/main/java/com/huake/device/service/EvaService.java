package com.huake.device.service;

import execution.eva;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class EvaService {
    // 每日后台运行模型
    public void cal(String jstr) throws Exception {
        eva.run_model(jstr);
    }

    // 点击“开始评价”按钮即时运行模型
    public void click_cal(String jstr) throws Exception {
        eva.click_run_model(jstr);
    }

    // 载入设备评价页面时显示的最新结果
    public String load_res(String jstr) throws ParseException {
        return eva.get_res(jstr);
    }

    // 搜索时段内评价结果
    public String timeRange_res(String jstr) throws ParseException {
        return eva.search_res(jstr);
    }

    public String load_model(String jstr){//进行模型编辑时载入模型
        return eva.get_model(jstr);
    }

    public void save_model(String jstr){//保存模型
        eva.save_model(jstr);
    }

    public String load_offres(String jstr){//点击“离线评价”后载入最新离线评价结果
        return eva.get_off_res(jstr);
    }

    public String init_offres(String jstr) throws ParseException {//新建模型后初始化离线评价结果
        return eva.get_init_off_res(jstr);
    }

    public void save_offres(String jstr){//保存修改后的离线评价结果
        eva.save_off_res(jstr);
    }

    public String draw_info(String jstr) throws ParseException {//根据一条计算规则及评价时间得到应查询的测点集及时段集
        return eva.get_draw_info(jstr);
    }

    public String rela_time(String jstr) throws ParseException {//返回一条计算规则的查询时段
        return eva.get_rela_time(jstr);
    }

    public String rela_points(String jstr){//返回一条计算规则的相关测点及类型
        return eva.get_rela_points(jstr);
    }

    public String point_tag(String jstr){//返回评价在线叶子节点编辑界面中左下角不同计算规则显示的文字标签
        return eva.get_tag(jstr);
    }
}
