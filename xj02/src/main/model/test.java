/*
 * Copyright 2019 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package main.model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import main.dao.DaoTree;
import main.dao.FaultTreeResultSave;
import sun.swing.StringUIClientPropertyKey;
import main.util.TimeUtils;


import javax.naming.Name;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author LENOVO
 * @date 2019/12/31 9:49
 */
 public class test {

    public static void main(String[] args) throws ParseException {
//       FaultTreeResultSave.creatsql_2(1576774800, 2, "Gov");
        FaultTreeResultSave.creatsql_3();
  /*public static void main(String[] args) throws ParseException {
      List<mysqlUtil.resFloat> rf=DaoTree.queFloat(1538335000,1560);
  }*/
       /* long date1 =1514736000;
        String date = TimeUtils.longToString(date1*1000,"yyyy-MM-dd HH:mm:ss");
        System.out.print(date);*/

    }
}