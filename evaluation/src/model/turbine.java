package model;

import element.tree;
import util.timeUtil;

import java.util.Date;
import java.util.Map;

import static element.tree.id_min_avg_max;
import static util.excelUtil.readExcel;
//import static util.mysqlUtil.*;


public class turbine {
    public static tree eva_tree;
    private static String type="tur";

    public static void main(String[] args) throws Exception {
        int unit=1;//机组号
        boolean tree_build_mark=false;
        if (tree_build_mark) {
            tree temp_eva_tree = new tree("./src/model/水泵水轮机模型.xlsx");//建树
            eva_tree = temp_eva_tree;
            tree.tree_saver(eva_tree, "tur");
        }
        else {
            eva_tree = tree.tree_reader("tur");
        }

        boolean threshold_build_mark=false;
        Map<String, Map<Integer, float[][]>> th_all;
        if (threshold_build_mark) {
            //录入阈值
            th_all = tree.threshold_builder("./src/model/在线点阈值.xlsx");
            tree.threshold_saver(th_all);
        }

        //设置时段
        Date d1= timeUtil.stringToDate("2017-12-29 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date d2=timeUtil.stringToDate("2017-12-29 12:00:00","yyyy-MM-dd HH:mm:ss");
        Date[] time_d={d1,d2};

        /*//稳态时段
        Date[][] steady_turbine=steadyTime(time_d,1, tree.work_condition_turbine);//求稳态时段
        Date[][] steady_pump=steadyTime(time_d,1, tree.work_condition_pump);*/

        boolean offline_res_build_mark=false;
        if (offline_res_build_mark) {
            tree.offline_res_builder("./src/model/水泵水轮机离线评价结果.xlsx", "tur");
        }

        eva_tree=tree.cal(eva_tree, time_d, unit, type);
        System.out.println("Calculation finished！");

        tree.res_saver(eva_tree, type, unit, time_d);
        System.out.println("Result Saving finished！");

        //List<String[]> min_avg_max=new ArrayList<>();
        //min_avg_max = id_min_avg_max( steady_turbine, eva_tree);//稳态时段内测点值的最小值、平均值与最大值

        //writeExcel( min_avg_max, 4, "./src/model/在线点稳态值表格.xlsx");
    }

}
