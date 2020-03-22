package util;

import element.tree;
import sun.applet.AppletThreadGroup;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

public class mysqlUtil {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL_FLOAT = "jdbc:mysql://192.168.1.194:3306/XJdatabase_core";
    static final String DB_URL_BOOL = "jdbc:mysql://192.168.1.194:3306/XJdatabase_boolean";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "remote";
    static final String PASS = "123456";

    // 机组状态测点号
    static final int[] pump_state_point_id=new int[]{826, 2740, 4654, 6568};//各机组抽水态id
    static final int[] tur_state_point_id=new int[]{822, 2736, 4650, 6564};//各机组发电态id
    static final int[] terminal_point_id=new int[]{819, 2733, 4647, 6561};//各机组停机态id

    //定义结果中每一行的对象，单精量resFloat，状态量resBool
    public static class resFloat{
        private long time;
        private float value;
        private int flag;

        public resFloat(long tim, float val, int fla) {
            time=tim;
            value=val;
            flag=fla;
        }

        public long getTime(){
            return time;
        }

        public float getValue(){
            return value;
        }

        public int getFlag(){
            return flag;
        }
    }

    public static class resBool{
        private long time;
        private int value;
        private int flag;

        public resBool(long tim, int val, int fla){
            this.time=tim;
            this.value=val;
            this.flag = fla;
        }

        /*public void setTime(long time){
            this.time=time;
        }
        public void setValue(int value){
            this.value=value;
        }
        public  void setFlag(int flag) {
            this.flag = flag;
        }*/

        public long getTime(){
            return time;
        }

        public int getValue(){
            return value;
        }

        public int getFlag(){
            return flag;
        }
    }


    public static void main(String[] args) throws ParseException {
        /*long[] time_long={1537397423,1538335000};
        Date d1=timeUtil.longToDate(time_long[0]*1000,"yyyy-MM-dd HH:mm:ss");
        Date d2=timeUtil.longToDate(time_long[1]*1000,"yyyy-MM-dd HH:mm:ss");*/

        Date d1=timeUtil.stringToDate("2017-12-29 00:00:00","yyyy-MM-dd HH:mm:ss");
        Date d2=timeUtil.stringToDate("2017-12-29 12:00:00","yyyy-MM-dd HH:mm:ss");

        Date[] time_d={d1,d2};
        Date[][] steady_turbine=steadyTime(time_d,1, 1);
        //List<resBool> rb=searchBool(time_d,822);
        List<resFloat> rf=searchFloat(time_d,50);
        for (int f_i=0;f_i<rf.size();f_i++) {
            System.out.println("record "+f_i+": " + rf.get(f_i).getTime() + " " + rf.get(f_i).getValue() + " " + rf.get(f_i).getFlag());
        }

    }

    public static List searchFloat(Date[] time_gap, int id){//查询数据库中单精量
        List<resFloat> resList=new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            String DB_URL=DB_URL_FLOAT;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            String[] t1=timeUtil.getYMDHMS(time_gap[0]);
            String[] t2=timeUtil.getYMDHMS(time_gap[1]);

            String[] year=new String[2];
            year[0]=t1[0];
            year[1]=t2[0];
            String[] month=new String[2];
            month[0]=t1[1];
            month[1]=t2[1];

            long[] sql_time=new long[2];//用于sql查询的数据库存储时间
            sql_time[0]=timeUtil.dateToLong(time_gap[0])/1000;
            sql_time[1]=timeUtil.dateToLong(time_gap[1])/1000;

            if (year[0].equals(year[1])&&month[0].equals(month[1])){
                String tb_name="float_"+id+"_"+year[0]+"_"+month[0];
                String conditions="t>="+sql_time[0]+" AND "+"t<="+sql_time[1];
                String sql = "SELECT * FROM "+tb_name+" WHERE "+conditions;
                ResultSet rs = stmt.executeQuery(sql);

                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                    long tm  = rs.getLong("t");
                    float value = rs.getFloat("v");
                    int flag = rs.getInt("f");
                    resFloat rf=new resFloat(tm,value,flag);
                    resList.add(rf);

                    // 输出数据
                    System.out.print("单精量测点: " + id);
                    System.out.print(", 时间: " + tm);
                    System.out.print(", 测点值: " + value);
                    System.out.print(", 标志: " + flag);
                    System.out.print("\n");
                }
                // 完成后关闭
                rs.close();
            }
            else {

                Calendar c= Calendar.getInstance();
                c.setTime(time_gap[0]);
                c.add(Calendar.MONTH, 1);
                Date[] sub_time=new Date[2];
                sub_time[0]=time_gap[0];
                sub_time[1]=c.getTime();

                long[] sub_sql_time=new long[2];
                sub_sql_time[0]=timeUtil.dateToLong(sub_time[0])/1000;
                sub_sql_time[1]=timeUtil.dateToLong(sub_time[1])/1000;

                while((sub_time[0].before(time_gap[1]))||(sub_time[0].equals(time_gap[1]))){//只要子片段开始时间不在总时间片段末尾后

                    if (sub_time[1].after(time_gap[1])){//若子片段结束时间大于总片段结束时间，则重写为总片段结束时间
                        sub_time[1]=time_gap[1];
                    }

                    String[] st1=timeUtil.getYMDHMS(sub_time[0]);
                    String[] st2=timeUtil.getYMDHMS(sub_time[1]);

                    year[0]=st1[0];
                    year[1]=st2[0];
                    month[0]=st1[1];
                    month[1]=st2[1];

                    sub_sql_time[0]=timeUtil.dateToLong(sub_time[0])/1000;
                    sub_sql_time[1]=timeUtil.dateToLong(sub_time[1])/1000;

                    for (int i = 0; i < 2; i++) {
                        String tb_name = "float_" + id + "_" + year[i] + "_" + month[i];
                        String conditions;
                        if (i == 1) {
                            conditions = "t>=" + sql_time[i];
                        } else {
                            conditions = "t<=" + sql_time[i];
                        }
                        String sql = "SELECT * FROM " + tb_name + " WHERE " + conditions;
                        ResultSet rs = stmt.executeQuery(sql);

                        while (rs.next()) {
                            // 通过字段检索
                            long tm  = rs.getLong("t");
                            float value = rs.getFloat("v");
                            int flag = rs.getInt("f");
                            resFloat rf=new resFloat(tm,value,flag);
                            resList.add(rf);

                            // 输出数据
                            System.out.print("单精量测点: " + id);
                            System.out.print(", 时间: " + tm);
                            System.out.print(", 测点值: " + value);
                            System.out.print(", 标志: " + flag);
                            System.out.print("\n");
                        }
                    }

                    //更新时间
                    c.setTime(sub_time[1]);
                    c.add(Calendar.SECOND, 1);
                    sub_time[0]=c.getTime();//子片段开始时间变为前一子片段结束时间+1s
                    c.add(Calendar.MONTH,1);
                    sub_time[1]=c.getTime();//子片段结束时间变为新子片段开始时间+1月
                }
            }

            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return resList;
    }

    public static List searchBool(Date[] time_gap, int id){//查询数据库中状态量
        List<resBool> resList=new ArrayList<>();
        Connection conn=null;
        //Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            String DB_URL=DB_URL_BOOL;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            /*// 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();*/

            Date[][] month_piece=month_cut(time_gap);
            List<resBool> tempList=new ArrayList<>();

            for (int i=0;i<month_piece.length;i++){//按月片段分别检索所有表格
                tempList=searchBool_monthly(month_piece[i],id,conn);
                resList.addAll(tempList);//每查到一个月的数据就往resList中添加
            }

            //stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            /*try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){

            }// 什么都不做*/
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return resList;
    }

    public static Date[][] month_cut(Date[] gap) throws ParseException {//将时间段gap按月切分
        String[] t1=timeUtil.getYMDHMS(gap[0]);
        String[] t2=timeUtil.getYMDHMS(gap[1]);

        int total_month=(Integer.parseInt(t2[0])-Integer.parseInt(t1[0]))*12+
                (Integer.parseInt(t2[1])-Integer.parseInt(t1[1]))+1;//涉及的总月份数

        Date[][] month_piece=new Date[total_month][2];
        month_piece[0][0]=gap[0];
        month_piece[total_month-1][1]=gap[1];
        Calendar c=Calendar.getInstance();

        String last_day;
        String[] present_time;

        if (total_month>1){
            for (int i=0;i<total_month-1;i++){
                c.setTime(month_piece[i][0]);
                present_time=timeUtil.getYMDHMS(month_piece[i][0]);
                last_day = c.getActualMaximum(Calendar.DAY_OF_MONTH)+"";
                String tttt=present_time[0]+"-"+present_time[1]+"-"+last_day+" 23:59:59";
                Date month_last=timeUtil.stringToDate(tttt,"yyyy-MM-dd HH:mm:ss");
                month_piece[i][1]=month_last;
                c.setTime(month_last);
                c.add(Calendar.SECOND,1);
                Date month_start=c.getTime();
                month_piece[i+1][0]=month_start;
            }
        }

        return month_piece;
    }

    public static List searchBool_monthly(Date[] time_gap, int id, Connection cnnec ) throws SQLException {//按单月查找bool值，专用作searchBool子函数
        List<resBool> resList = new ArrayList<>();
        Statement stmt=cnnec.createStatement();

        String[] t1=timeUtil.getYMDHMS(time_gap[0]);
        String[] t2=timeUtil.getYMDHMS(time_gap[1]);

        String[] year=new String[2];
        year[0]=t1[0];
        year[1]=t2[0];
        String[] month=new String[2];
        month[0]=t1[1];
        month[1]=t2[1];

        long[] sql_time=new long[2];//用于sql查询的数据库存储时间
        sql_time[0]=timeUtil.dateToLong(time_gap[0])/1000;
        sql_time[1]=timeUtil.dateToLong(time_gap[1])/1000;



        List<resBool> temp_resList=new ArrayList<>();

        if (year[0].equals(year[1])&&month[0].equals(month[1])){
            String tb_name="bool_"+id+"_"+year[0]+"_"+month[0];
            //String conditions="tm>="+sql_time[0]+" AND "+"tm<="+sql_time[1];
            //String sql = "SELECT * FROM "+tb_name+" WHERE "+conditions;
            String sql = "SELECT * FROM "+tb_name;
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                long tm  = rs.getLong("t");
                int value = rs.getInt("v");
                int flag = rs.getInt("f");
                resBool rb = new resBool(tm,value,flag);
                temp_resList.add(rb);//将查询的当月数据暂存在temp_resList中
                // 输出数据
//                System.out.print("时间: " + tm);
//                System.out.print(", 测点值: " + value);
//                System.out.print(", 标志: " + flag);
//                System.out.print("\n");

                System.out.print("状态量测点: " + id);
                System.out.print(", 时间: " + temp_resList.get(temp_resList.size()-1).getTime());
                System.out.print(", 测点值: " + temp_resList.get(temp_resList.size()-1).getValue());
                System.out.print(", 标志: " + temp_resList.get(temp_resList.size()-1).getFlag());
                System.out.print("\n");
            }

            /*rs.last();
            int total_res_row=rs.getRow();
            rs.beforeFirst();
            for (int i=0;i<total_res_row;i++){
                rs.next();
                long tm  = rs.getLong("tm");
                int value = rs.getInt("id"+id+"_v");
                int flag = rs.getInt("id"+id+"_f");
                resBool rb = new resBool(tm,value,flag);
                temp_resList.add(rb);//将查询的当月数据暂存在temp_resList中

                System.out.print("时间: " + temp_resList.get(temp_resList.size()-1).getTime());
                System.out.print(", 测点值: " + temp_resList.get(temp_resList.size()-1).getValue());
                System.out.print(", 标志: " + temp_resList.get(temp_resList.size()-1).getFlag());
                System.out.print("\n");
            }*/


            long[] temp_resList_timegap=new long[2];//存储当月的最早和最晚记录的时间

            /*for (int tii=0;tii<temp_resList.size();tii++){
                System.out.println("index "+tii+": time —— "+temp_resList.get(tii).getTime()+" value —— "+ temp_resList.get(tii).getValue());
            }*/

            temp_resList_timegap[0]=temp_resList.get(0).getTime();
            temp_resList_timegap[1]=temp_resList.get(temp_resList.size()-1).getTime();

            int[] save_pos=new int[2];//用于保存temp_resList中可连续保存的结果的索引位置
            //即：resList首结果时间后temp_resList中最早出现的记录时间，和resList末结果时间前temp_resList中最后出现的记录时间


            //该if结构体用于获取resList首位的值并确定temp_resList中连续复制位的位置
            if (temp_resList_timegap[0]>sql_time[0]){//如果表中有记录的数据最早时间大于查询起始时间，查询上月数据并赋值上月最后数据至结果List中
                Calendar c= Calendar.getInstance();
                c.setTime(time_gap[0]);
                c.add(Calendar.MONTH, -1);
                Date time_before=c.getTime();
                String[] time_before_string=timeUtil.getYMDHMS(time_before);
                String tb_name_before="bool_"+id+"_"+time_before_string[0]+"_"+time_before_string[1];
                String sql_before="SELECT * FROM "+tb_name_before;

                ResultSet rs_before=stmt.executeQuery(sql_before);
                rs_before.last();

                long tm_before  = sql_time[0];//时间用开始查询时间
                int value_before = rs_before.getInt("v");
                int flag_before = 3;//代表是插值
                resBool rb_before=new resBool(tm_before,value_before,flag_before);

                resList.add(rb_before);
                save_pos[0]=0;//更新temp_resList中可连续存储的起始位置为0
            }
            else{//首条记录时间在开始时间前
                if (temp_resList.size()==1){//如果只有一条数据
                    long tm_first  = sql_time[0];//时间用开始查询时间
                    int value_first = (temp_resList.get(0).getValue());
                    int flag_first = 3;//代表是插值
                    resBool rb_first=new resBool(tm_first,value_first,flag_first);

                    resList.add(rb_first);
                    save_pos[0]=1;
                }
                else {//存在多条数据
                    for (int ii = 1; ii < temp_resList.size(); ii++) {
                        resBool rb_middle ;
                        if ((temp_resList.get(ii-1).getTime()<sql_time[0])&&
                                (temp_resList.get(ii).getTime()>sql_time[0])){//查询时间在两条记录之间
                            rb_middle=new resBool(sql_time[0],temp_resList.get(ii-1).getValue(),3);
                            resList.add(rb_middle);//赋值ii-1位的数据
                            save_pos[0]=ii;//连续复制位由ii起
                            break;//赋值后结束当前循环
                        }
                        else{
                            if (temp_resList.get(ii-1).getTime()==sql_time[0]){//如果和第ii-1个值相等
                                rb_middle=new resBool(sql_time[0],temp_resList.get(ii-1).getValue(),temp_resList.get(ii-1).getFlag());
                                resList.add(rb_middle);//赋值ii-1位的数据
                                save_pos[0]=ii;//连续复制位由ii起
                                break;//赋值后结束当前循环
                            }
                            if (temp_resList.get(ii).getTime()==sql_time[0]){//如果和第ii个值相等
                                rb_middle=new resBool(sql_time[0],temp_resList.get(ii).getValue(),temp_resList.get(ii).getFlag());
                                resList.add(rb_middle);//赋值ii位的数据
                                save_pos[0]=ii+1;//连续复制位由ii+1起
                                break;//赋值后结束当前循环
                            }
                        }
                    }
                }
            }

            //该if结构体用于获取temp_resList中终止连续复制位置
            resBool save_last = null;//存储resList中最后一项的对象
            if (temp_resList_timegap[1]<sql_time[1]){//如果该月记录中的最后时间小于sql查询结束时间，记录该月最后记录的值
                int v_last=temp_resList.get(temp_resList.size()-1).getValue();
                save_last=new resBool(sql_time[1],v_last,3);
                save_pos[1]=temp_resList.size()-1;//存储连续复制位最后一位的索引
            }
            else{
                if (temp_resList.size()==1){//等于
                    if (temp_resList_timegap[1]==sql_time[1]){
                        save_last=temp_resList.get(0);
                    }
                    else{
                        int v_last=resList.get(0).getValue();
                        save_last=new resBool(sql_time[1],v_last,3);
                    }
                    save_pos[1]=-1;//存储连续复制位最后一位的索引
                }
                else{//大于
                    for (int jj=temp_resList.size()-1;jj>0;jj--){
                        if ((temp_resList.get(jj-1).getTime()<sql_time[1])&&
                                (temp_resList.get(jj).getTime()>sql_time[1])){//查询结束时间在两条记录之间
                            int v_last=temp_resList.get(jj-1).getValue();
                            save_last=new resBool(sql_time[1],v_last,3);
                            save_pos[1]=jj-1;
                            break;
                        }
                        else{
                            if (temp_resList.get(jj-1).getTime()==sql_time[1]){
                                save_last=temp_resList.get(jj-1);
                                save_pos[1]=jj-2;
                                break;
                            }
                            if (temp_resList.get(jj).getTime()==sql_time[1]){
                                save_last=temp_resList.get(jj);
                                save_pos[1]=jj-1;
                                break;
                            }
                        }
                    }
                }
            }

            //开始存储连续复制数据段
            if (save_pos[0]<=save_pos[1]){
                for (int kk=0;kk<=(save_pos[1]-save_pos[0]);kk++){
                    resList.add(temp_resList.get(save_pos[0]+kk));
                }
            }

            if (save_last!=null) {//存储最后一位
                resList.add(save_last);
            }
            // 完成后关闭
            rs.close();
        }
        return resList;
    }

    public static Date[][] steadyTimeSimple(Date[] time_gap, int id, int steadyThreshold) throws ParseException {//查询发电态、抽水态的稳态时段（设置达到稳态的时间为开机后steadyThreshold秒）
        //作为steadyTime的子函数使用
        int close_threshold=120;//关机前120s为稳态结束时刻
        Date[][] passage = null;//每行存储一对符合要求的稳态工况起始时段
        long gap=(timeUtil.dateToLong(time_gap[1])-timeUtil.dateToLong(time_gap[0]))/1000;
        if (gap>steadyThreshold) {//如果查询时间>threshold
            List<resBool> origin_data = searchBool(time_gap, id);//存储time_gap内的id测点值
            int total_len=origin_data.size();


            for (int i=0;i<total_len;i++) {
                long[] per_passage_long=new long[2];
                long[] neighbor_time_gap=new long[2];//相邻两跳变时刻（为1的时段）

                for (int j1=i;j1<total_len-1;j1++){
                    if ((j1==0)&&(origin_data.get(j1).getValue()==1)){//如果首位为1
                        neighbor_time_gap[0]=origin_data.get(j1).getTime();//直接存储
                        i=j1;
                        break;
                    }
                    else if ((origin_data.get(j1).getValue()==0)&&
                            (origin_data.get(j1+1).getValue()==1)){//上升沿
                        neighbor_time_gap[0]=origin_data.get(j1+1).getTime();//前一时刻
                        i=j1+1;
                        break;
                    }
                }

                for (int j2=i;j2<total_len;j2++){
                    if (j2<total_len-1) {//未到最后一位
                        if ((origin_data.get(j2).getValue() == 1) &&
                                (origin_data.get(j2 + 1).getValue() == 0)) {//下降沿
                            neighbor_time_gap[1] = origin_data.get(j2+1).getTime();//后一时刻
                            i = j2 ;
                            break;
                        }
                    }
                    else if(origin_data.get(j2).getValue() == 1){//到最后一位且为1，直接存储
                        neighbor_time_gap[1] = origin_data.get(j2).getTime();//后一时刻
                    }
                }

                if (neighbor_time_gap!=null) {
                    if ((neighbor_time_gap[1] - neighbor_time_gap[0]) > steadyThreshold+close_threshold) {
                        //如果前一上升沿时间与后一个下降沿时间差大于达稳态时间阈值
                        per_passage_long[0] = neighbor_time_gap[0] + steadyThreshold;
                        per_passage_long[1] = neighbor_time_gap[1]-close_threshold;

                        Date[] per_passage = new Date[2];
                        per_passage[0] = timeUtil.longToDate(per_passage_long[0] * 1000, "yyyy-MM-dd HH:mm:ss");
                        per_passage[1] = timeUtil.longToDate(per_passage_long[1] * 1000, "yyyy-MM-dd HH:mm:ss");

                        if (passage == null) {
                            passage = new Date[1][2];
                            passage[0] = per_passage;
                        } else {
                            Date[][] temp_passage = new Date[passage.length + 1][2];
                            System.arraycopy(passage, 0, temp_passage, 0, passage.length);
                            temp_passage[passage.length] = per_passage;
                            passage = temp_passage;
                        }
                    }
                }
            }

            return passage;
        }
        else{
            return null;
        }
    }

    //计算在某时间间隔内某号机组的稳态工作时段
    public static Date[][] steadyTime(Date[] time_gap, int unit_id, int type, int steadyThreshold) throws ParseException {
        Date[][] res_time_psg = new Date[0][];
        int[] state_point_id;
        //首先定义各机组的状态测点
        switch (type){//判断查询状态
            case tree.work_condition_pump://抽水工况
                state_point_id=pump_state_point_id;//各机组抽水态id
                int unit_choushui_id=state_point_id[unit_id-1];
                res_time_psg=steadyTimeSimple(time_gap, unit_choushui_id,steadyThreshold);
                break;
            default://非-1表示正功率，发电，受其他机组启停的影响
                state_point_id=tur_state_point_id;//各机组发电态id
                int unit_fadian_id=state_point_id[unit_id-1];
                Date[][] current_unit_time_psg=steadyTimeSimple(time_gap, unit_fadian_id,steadyThreshold);//查询当前机组的启停状态
                if (current_unit_time_psg==null){
                    return null;
                }

                //查询所有其它机组在该时段内的开关机状态
                List<List> all_other_unit_state=new ArrayList<>();
                for (int i=0;i<state_point_id.length;i++){
                    if (i!=unit_id-1){
                        List<resBool> single_unit_state = searchBool(time_gap, state_point_id[i]);
                        all_other_unit_state.add(cut_redundency(single_unit_state));
                    }
                }

                List<long[]> CUTP_long_list=new ArrayList<>();//存入所选机组稳态时段秒值的List
                for (int k=0;k<current_unit_time_psg.length;k++){
                    long[] current_unit_time_psg_row=new long[2];
                    current_unit_time_psg_row[0]=timeUtil.dateToLong(current_unit_time_psg[k][0])/1000;
                    current_unit_time_psg_row[1]=timeUtil.dateToLong(current_unit_time_psg[k][1])/1000;
                    CUTP_long_list.add(current_unit_time_psg_row);
                }

                for (int j=0;j<all_other_unit_state.size();j++){//对其他机组操作时刻和选定机组稳态时段进行遍历比较
                    List<resBool> other_unit_state_per=all_other_unit_state.get(j);

                    if (other_unit_state_per==null){
                        continue;
                    }

                    for (int jj=0;jj<other_unit_state_per.size();jj++){
                        resBool other_unit_state_jj=other_unit_state_per.get(jj);
                        for (int jjj=0;jjj<CUTP_long_list.size();jjj++){//对时间点在选定机组稳态时段中进行遍历判断修改
                            long st_time=CUTP_long_list.get(jjj)[0];
                            long end_time=CUTP_long_list.get(jjj)[1];

                            //第一种情况，其他机组开关机时间落在稳态时段外，但距离稳态开始时间小于稳态时间阈值
                            if ((other_unit_state_jj.getTime()<st_time)&&
                                    (other_unit_state_jj.getTime()+steadyThreshold>st_time)&&
                                    (other_unit_state_jj.getFlag()==2)){
                                if(end_time-other_unit_state_jj.getTime()-steadyThreshold<=0){//如果其他机组达到稳态后的时间在当前机组稳态结束时间后
                                    CUTP_long_list.remove(jjj);//删除jjj条的记录
                                }
                                else {//其他机组达到稳态后的时间在当前机组稳态结束时间前，则缩短稳态时间，后延稳态开始时间
                                    CUTP_long_list.set(jjj, new long[]{other_unit_state_jj.getTime() + steadyThreshold, end_time});//延后稳态开始时间修改记录
                                }
                                break;
                            }
                            //第二种情况，其他机组开关机时间点落在稳态时段内
                            if ((other_unit_state_jj.getTime()>st_time)&&
                                    (other_unit_state_jj.getTime()<end_time)&&
                                    (other_unit_state_jj.getFlag()==2)){//其他机组有为原值且在选定机组单机稳定时段内的开关机记录
                                CUTP_long_list.set(jjj,new long[]{st_time,other_unit_state_jj.getTime()});//修改记录
                                if (end_time-other_unit_state_jj.getTime()>steadyThreshold){//如果发生时间在结束时间阈值秒数之前，新增记录
                                    CUTP_long_list.add(jjj+1,new long[] {other_unit_state_jj.getTime()+steadyThreshold,end_time});
                                }
                                break;
                            }


                        }
                    }
                }

                int time_psg_num=CUTP_long_list.size();//总的稳态时间片段数
                res_time_psg=new Date[time_psg_num][2];
                for (int rr=0;rr<time_psg_num;rr++){
                    Date d1=timeUtil.longToDate(CUTP_long_list.get(rr)[0]*1000, "yyyy-MM-dd hh:mm:ss");
                    Date d2=timeUtil.longToDate(CUTP_long_list.get(rr)[1]*1000, "yyyy-MM-dd hh:mm:ss");
                    res_time_psg[rr]= new Date[]{d1, d2};
                }
        }
        return res_time_psg;
    }

    public static Date[][] steadyTime(Date[] time_gap, int unit_id, int type) throws ParseException {//设置默认阈值为20min（1200s）

        int steadyThreshold=1200;
        Date[][] res=steadyTime( time_gap,  unit_id,  type, steadyThreshold);
        return res;
    }

    public static Date[][] workTime(Date[] time_gap, int unit_id, int work_type ) throws ParseException {
        Date[][] res;
        List<Date[]> time_psg=new ArrayList<>();
        int[] mark = {1, 0};//默认为统计1到0的时段
        int[] state_point_id=tur_state_point_id;//根据工况类型设置状态测点id
        if (work_type==tree.work_condition_pump){
            state_point_id=pump_state_point_id;
        }

        //统计对应工况时段
        List<resBool> ori_data_pump=searchBool(time_gap, state_point_id[unit_id-1]);
        ori_data_pump=cut_redundency(ori_data_pump);
        for (int ii = 1; ii < ori_data_pump.size()-1; ii++) {
            if (ori_data_pump.get(ii).getValue()==mark[0]&&ori_data_pump.get(ii+1).getValue()==mark[1]){
                Date t_ii_mk0=timeUtil.longToDate(ori_data_pump.get(ii).getTime()*1000,
                        "yyyy-MM-dd HH:mm:ss");
                Date t_ii_mk1=timeUtil.longToDate(ori_data_pump.get(ii+1).getTime()*1000,
                        "yyyy-MM-dd HH:mm:ss");
                time_psg.add(new Date[]{t_ii_mk0,t_ii_mk1});
            }
        }
        if (ori_data_pump.get(ori_data_pump.size()-2).getValue()==mark[0]&&
                ori_data_pump.get(ori_data_pump.size()-1).getValue()==mark[0]){
            Date t_ii_mk0=timeUtil.longToDate(ori_data_pump.get(ori_data_pump.size()-2).getTime()*1000,
                    "yyyy-MM-dd HH:mm:ss");
            Date t_ii_mk1=timeUtil.longToDate(ori_data_pump.get(ori_data_pump.size()-1).getTime()*1000,
                    "yyyy-MM-dd HH:mm:ss");
            time_psg.add(new Date[]{t_ii_mk0,t_ii_mk1});
        }

        res=new Date[time_psg.size()][];
        for (int i=0;i<res.length;i++){//组织输出
            res[i]=time_psg.get(i);
        }

        return res;
    }

    public static Date[][] terminalTime(Date[] time_gap, int unit_id, int type) throws ParseException {
        Date[][] res;
        List<long[]> time_psg=new ArrayList<>();
        int[] mark = {1, 0};//默认为统计1到0的时段
        int[] state_point_id=terminal_point_id;//设置状态测点id

        //统计对应工况时段
        List<resBool> ori_data_pump=searchBool(time_gap, state_point_id[unit_id-1]);
        ori_data_pump=cut_redundency(ori_data_pump);
        for (int ii = 1; ii < ori_data_pump.size()-1; ii++) {
            if (ori_data_pump.get(ii).getValue()==mark[0]&&ori_data_pump.get(ii+1).getValue()==mark[1]){
                time_psg.add(new long[]{ori_data_pump.get(ii).getTime(),ori_data_pump.get(ii+1).getTime()});
            }
        }
        if (ori_data_pump.get(ori_data_pump.size()-2).getValue()==mark[0]&&
                ori_data_pump.get(ori_data_pump.size()-1).getValue()==mark[0]){
            time_psg.add(new long[]{ori_data_pump.get(ori_data_pump.size()-2).getTime(),ori_data_pump.get(ori_data_pump.size()-1).getTime()});
        }

        //若为停机稳态，则需进行时段筛选
        if (type>0) {
            List<long[]> temp_time_psg = new ArrayList<>();
            for (int i = 0; i < time_psg.size(); i++) {
                long t1 = time_psg.get(i)[0] + 3600;
                long t2 = time_psg.get(i)[1] - 120;
                if (t1 < t2) {
                    temp_time_psg.add(new long[]{t1, t2});
                }
            }
            time_psg=temp_time_psg;
        }

        //整理结果
        res=new Date[time_psg.size()][];
        for (int i=0;i<res.length;i++){//组织输出
            Date t1=timeUtil.longToDate(time_psg.get(i)[0]*1000,
                    "yyyy-MM-dd HH:mm:ss");
            Date t2=timeUtil.longToDate(time_psg.get(i)[1]*1000,
                    "yyyy-MM-dd HH:mm:ss");
            res[i]=new Date[]{t1,t2};
        }

        return res;
    }

    public static List<resBool> cut_redundency(List<resBool> src_list){//删除src_list中的连续重复元素（保留两端值）
        List<resBool> res=src_list;
        int ii=0;//删除的元素数量
        if (src_list.size()>2) {
            for (int i = 0; i < src_list.size() - 2; i++) {
                if (src_list.get(i).getValue() == src_list.get(i + 1).getValue()) {//如果出现了重复元素
                    res.remove(i + 1 - ii);
                    ii++;
                }
            }
        }
        return res;
    }

    public static float mean(int[] number){//计算均值
        float res;
        int count=0;
        int len=number.length;
        for (int i=0;i<len;i++){
            count=count+number[i];
        }
        res=count/len;
        return res;
    }

    public static float mean(float[] number) {
        float res = 0;
        float count = 0;
        if (number != null) {
            int len = number.length;
            for (int i = 0; i < len; i++) {
                count = count + number[i];
            }
            res = count / len;
        }
        return res;
    }


    public static float maxx(float[] number){
        float res=number[0];
        int len=number.length;
        if (len>1) {
            for (int i = 0; i < len - 1; i++) {
                res=Math.max(res,number[i+1]);
            }
        }
        return res;
    }

    public static float minn(float[] number){
        float res=number[0];
        int len=number.length;
        if (len>1) {
            for (int i = 0; i < len - 1; i++) {
                res=Math.min(res,number[i+1]);
            }
        }
        return res;
    }

}

