package model;

import element.tree;
import util.timeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static element.tree.*;
import static util.excelUtil.readExcel;
import static util.hdbUtil.steadyTime;
//import static util.mysqlUtil.*;


public class turbine {
    public static tree eva_tree;
    private static String type="tur";

    public static void main(String[] args) throws Exception {
        int unit=1;//机组号
        //设置时段
        /*Date d1= timeUtil.stringToDate("2017-12-29 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date d2=timeUtil.stringToDate("2017-12-29 23:59:59","yyyy-MM-dd HH:mm:ss");
        Date[] time_d={d1,d2};*/
        Date d2=timeUtil.stringToDate("2017-12-30 23:59:59","yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        cal.setTime(d2);
        cal.add(Calendar.DAY_OF_YEAR,-1);
        cal.add(Calendar.SECOND,1);
        Date d1=cal.getTime();
        Date[] time_d={d1, d2};


        //新存入模型后务必将1、2、3均设为true
        boolean tree_build_mark=false;
        boolean node_threshold_build_mark=false;
        boolean offline_res_build_mark=false;
        boolean threshold_build_mark=false;//架构更新后已弃用
        boolean search_conduct_mark=false;//率定阈值时用

        if (tree_build_mark) {
            //tree temp_eva_tree = new tree("./src/model/水泵水轮机评价模型.xlsx");//建树
            tree temp_eva_tree = new tree("./src/model/模型-水泵水轮机.xlsx");//建树
            eva_tree = temp_eva_tree;
            tree.tree_saver(eva_tree, "tur");
        }

        Map<String, Map<Integer, String[][]>> th_all;
        if (threshold_build_mark) {
            //录入阈值
            th_all = tree.threshold_builder("./src/model/在线点阈值.xlsx");
        }

        if (node_threshold_build_mark) {
            //录入阈值
            tree.node_threshold_builder("./src/model/阈值-水泵水轮机.xlsx", type);
        }

        if (search_conduct_mark){
            eva_tree=tree.tree_reader(type);
            Map<Integer,String[]> search_res=tree.node_search_val(eva_tree, type, time_d);
            tree.node_search_val_output(eva_tree, search_res,
                    "./src/model/节点查询-水泵水轮机.xlsx");
        }

        if (offline_res_build_mark) {
            tree.offline_res_builder("./src/model/水泵水轮机离线评价结果.xlsx", "tur");
        }

        /*//稳态时段
        Date[][] steady_turbine=steadyTime(time_d,1, tree.work_condition_turbine);//求稳态时段
        Date[][] steady_pump=steadyTime(time_d,1, tree.work_condition_pump);*/
        //Date[][] stime_t = steadyTime(time_d, unit, work_condition_turbine);

        //可对某一测点的计算值进行演算测试
        /*Map<Integer,Map<String,Map<String,String[][]>>> LN_th=nodes_threshold_reader(type);//查询节点阈值
        float v = cal_func_B(time_d, 2234, "float", "S",
                new int[]{unit}, LN_th.get(60007));*/



        eva_tree=tree.cal_tree(type, time_d, unit);
        System.out.println("Calculation finished！");

        tree.res_saver(eva_tree, type, unit, time_d);
        System.out.println("Result Saving finished！");

        //List<String[]> min_avg_max=new ArrayList<>();
        //min_avg_max = id_min_avg_max( steady_turbine, eva_tree);//稳态时段内测点值的最小值、平均值与最大值

        //writeExcel( min_avg_max, 4, "./src/model/在线点稳态值表格.xlsx");
    }

}
