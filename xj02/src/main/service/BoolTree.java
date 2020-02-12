package main.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import main.dao.DaoTree;
import main.util.DataBoolUtils;
import main.util.mysqlUtil;


public class BoolTree {
    //bool型专用方法  返回测量量为"1"的比例  table为表名，id为测点ID，time为后期时间
//    public double BooleanTree (String table,String id, long time) throws ClassNotFoundException, SQLException {
        public double BooleanTree (String id,long time) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree as = new DaoTree();
        DataBoolUtils data = as.queBool(id,time);//获取boolean型数据
           /* DataUtils data = null;//获取boolean型数据
            try {
                data = (DataUtils) DaoTree.queBool(time,id);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

        //   ArrayList<Double> value = data.getValue();
            assert data != null;
            ArrayList<Integer> value = data.getValue();
//        System.out.println(value.size());
        int x = 0;
        int y = 0;
        double p = 0;

        for (Integer h : value) {
            if (h == 1) {
                x++;
            }
            if (h == 0) {
                y++;
            }
        }
        if (x == 0 && y == 0){
            p=0;
        }else{
            p = ((double)x/(x+y));
        }
        return p;
    }

}
