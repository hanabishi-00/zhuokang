package main.util;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import main.model.Node;
import main.model.MakeFaultTree;

public class mysqlUtil {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
// //   static final String DB_URL_FLOAT = "jdbc:mysql://218.197.228.243:3306/XJdatabase_core";
////    static final String DB_URL_FLOAT = "jdbc:mysql://192.168.1.194:3306/XJdatabase_core";
//////    static final String DB_URL_BOOL = "jdbc:mysql://218.197.228.243:3306/XJdatabase_core";
////    static final String DB_URL_BOOL = "jdbc:mysql://192.168.1.194:3306/XJdatabase_boolean";
    final static String DB_URL_BOOL = "jdbc:mysql://localhost:3306/xjdatabase_boolean?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";//本地
    final static String DB_URL_FLOAT = "jdbc:mysql://localhost:3306/xjdatabase_core?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    final static String DB_URL_result = "jdbc:mysql://localhost:3306/xjresult?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

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
        long[] time_long={1530012850,1530014650};
        long l1 = 1497628800;
        long l2 = l1-1800;
        Date d1= TimeUtils.longToDate(l2*1000,"yyyy-MM-dd HH:mm:ss");
        Date d2= TimeUtils.longToDate(l1*1000,"yyyy-MM-dd HH:mm:ss");
        Date[] time_d={d1,d2};
        System.out.println(d1);
        Date[][] steady_turbine=steadyTime(time_d,826, 3600);
        List<resFloat> rf=searchFloat(time_d,404);
        for (int f_i=0;f_i<rf.size();f_i++) {
            System.out.println("record "+f_i+": " + rf.get(f_i).getTime() + " " + rf.get(f_i).getValue() + " " + rf.get(f_i).getFlag());
        }
        List<resBool> rb=searchBool(time_d,1560);
        for (int f_i=0;f_i<rb.size();f_i++) {
            System.out.println("record "+f_i+": " + rb.get(f_i).getTime() + " " + rb.get(f_i).getValue() + " " + rb.get(f_i).getFlag());
        }
//        ArrayList<Node> insertN;
//        MakeFaultTree.InitialNodes(insertN,);

    }

    public static List searchFloat(Date[] time_gap, int id){//查询数据库中单精量
        List<resFloat> resList=new ArrayList<resFloat>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL=DB_URL_FLOAT;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            String[] t1= TimeUtils.getYMDHMS(time_gap[0]);
            String[] t2= TimeUtils.getYMDHMS(time_gap[1]);

            String[] year=new String[2];
            year[0]=t1[0];
            year[1]=t2[0];
            String[] month=new String[2];
            month[0]=t1[1];
            month[1]=t2[1];

            long[] sql_time=new long[2];//用于sql查询的数据库存储时间
            sql_time[0]=TimeUtils.dateToLong(time_gap[0])/1000;
            sql_time[1]= TimeUtils.dateToLong(time_gap[1])/1000;

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
                    resFloat rf= new resFloat(tm, value, flag);
                    resList.add(rf);

                    // 输出数据
                 /*   System.out.print("时间: " + tm);
                    System.out.print(", 测点值: " + value);
                    System.out.print(", 标志: " + flag);
                    System.out.print("\n");*/
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
                sub_sql_time[0]= TimeUtils.dateToLong(sub_time[0])/1000;
                sub_sql_time[1]= TimeUtils.dateToLong(sub_time[1])/1000;

                while((sub_time[0].before(time_gap[1]))||(sub_time[0].equals(time_gap[1]))){//只要子片段开始时间不在总时间片段末尾后

                    if (sub_time[1].after(time_gap[1])){//若子片段结束时间大于总片段结束时间，则重写为总片段结束时间
                        sub_time[1]=time_gap[1];
                    }

                    String[] st1= TimeUtils.getYMDHMS(sub_time[0]);
                    String[] st2= TimeUtils.getYMDHMS(sub_time[1]);

                    year[0]=st1[0];
                    year[1]=st2[0];
                    month[0]=st1[1];
                    month[1]=st2[1];

                    sub_sql_time[0]= TimeUtils.dateToLong(sub_time[0])/1000;
                    sub_sql_time[1]= TimeUtils.dateToLong(sub_time[1])/1000;

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
                            resFloat rf= new resFloat(tm, value, flag);
                            resList.add(rf);

                            // 输出数据
                         /*   System.out.print("时间: " + tm);
                            System.out.print(", 测点值: " + value);
                            System.out.print(", 标志: " + flag);
                            System.out.print("\n");*/
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
//        System.out.println("Goodbye!");
        return resList;
    }

    public static List searchBool(Date[] time_gap, int id){//查询数据库中状态量
        List<resBool> resList=new ArrayList<resBool>();
        Connection conn = null;
        //Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL=DB_URL_BOOL;
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            /*// 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();*/

            Date[][] month_piece=month_cut(time_gap);
            List<resBool> tempList=new ArrayList<resBool>();

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
//        System.out.println("Goodbye!");
        return resList;
    }

    public static Date[][] month_cut(Date[] gap) throws ParseException {//将时间段gap按月切分

        String[] t1= TimeUtils.getYMDHMS(gap[0]);
        String[] t2= TimeUtils.getYMDHMS(gap[1]);

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
                present_time= TimeUtils.getYMDHMS(month_piece[i][0]);
                last_day = c.getActualMaximum(Calendar.DAY_OF_MONTH)+"";
                String tttt=present_time[0]+"-"+present_time[1]+"-"+last_day+" 23:59:59";
                Date month_last= TimeUtils.stringToDate(tttt,"yyyy-MM-dd HH:mm:ss");
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
        List<resBool> resList = new ArrayList<resBool>();
        Statement stmt=cnnec.createStatement();

        String[] t1= TimeUtils.getYMDHMS(time_gap[0]);
        String[] t2= TimeUtils.getYMDHMS(time_gap[1]);

        String[] year=new String[2];
        year[0]=t1[0];
        year[1]=t2[0];
        String[] month=new String[2];
        month[0]=t1[1];
        month[1]=t2[1];

        long[] sql_time=new long[2];//用于sql查询的数据库存储时间
        sql_time[0]= TimeUtils.dateToLong(time_gap[0])/1000;
        sql_time[1]= TimeUtils.dateToLong(time_gap[1])/1000;



        List<resBool> temp_resList=new ArrayList<resBool>();

        if (year[0].equals(year[1])&&month[0].equals(month[1])){
            String tb_name="bool_"+id+"_"+year[0]+"_"+month[0];
            //String conditions="tm>="+sql_time[0]+" AND "+"tm<="+sql_time[1];
            //String sql = "SELECT * FROM "+tb_name+" WHERE "+conditions;
            //String sql = "select * from "+tb_name;
            ResultSet rs = stmt.executeQuery("select * from "+tb_name);

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

                /*System.out.print("时间: " + temp_resList.get(temp_resList.size()-1).getTime());
                System.out.print(", 测点值: " + temp_resList.get(temp_resList.size()-1).getValue());
                System.out.print(", 标志: " + temp_resList.get(temp_resList.size()-1).getFlag());
                System.out.print("\n");*/
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
                String[] time_before_string= TimeUtils.getYMDHMS(time_before);
                //String id1 = Integer.toString(id);
                //String tb_name_before="bool_"+id1+"_"+time_before_string[0]+"_"+time_before_string[1];
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


    public static Date[][] steadyTime(Date[] time_gap, int id, int steadyThreshold) throws ParseException {//查询发电态、抽水态的稳态时段（设置达到稳态的时间为开机后steadyThreshold秒）

        Date[][] passage = null;//每行存储一对符合要求的稳态工况起始时段
        long gap=(TimeUtils.dateToLong(time_gap[1])- TimeUtils.dateToLong(time_gap[0]))/1000;
        if (gap>steadyThreshold) {//如果查询时间>threshold
            List<resBool> origin_data = searchBool(time_gap, id);
            int total_len=origin_data.size();

            for (int i=1;i<total_len;i++) {
                long[] per_passage_long=new long[2];
                long[] neighbor_time_gap=new long[2];//相邻两时刻
                neighbor_time_gap[0]=origin_data.get(i-1).getTime();//前一时刻
                neighbor_time_gap[1]=origin_data.get(i).getTime();//后一时刻
                if (origin_data.get(i-1).getValue()==1&&
                        ((neighbor_time_gap[1]-neighbor_time_gap[0])>steadyThreshold)) {
                    //如果前一时间的值为1且与后一个值之间的时间差大于达稳态时间阈值
                    per_passage_long[0]=neighbor_time_gap[0]+steadyThreshold;
                    per_passage_long[1]=neighbor_time_gap[1];

                    Date[] per_passage=new Date[2];
                    per_passage[0]= TimeUtils.longToDate(per_passage_long[0]*1000,"yyyy-MM-dd HH:mm:ss");
                    per_passage[1]= TimeUtils.longToDate(per_passage_long[1]*1000,"yyyy-MM-dd HH:mm:ss");

                    if (passage==null) {
                        passage = new Date[1][2];
                        passage[0] = per_passage;
                    }
                    else {
                        Date[][] temp_passage = new Date[passage.length + 1][2];
                        System.arraycopy(passage, 0, temp_passage, 0, passage.length);
                        temp_passage[passage.length] = per_passage;
                        passage = temp_passage;
                    }
                }
            }

            return passage;
        }
        else{
            return null;
        }
    }

    //  寻找名称对应节点的Id
    public static int name2id(String name, ArrayList<Node> Nlist) {
        String ID = null;
        for (Node d : Nlist) {
            if (d.getName().equals(name)) {
                ID = d.getId();
            }
        }
        if (ID == null) {
            return -1;
        } else {
            return Integer.parseInt(ID);
        }
    }



//    存放故障树节点
    public static int saveFaultTree(ArrayList<Node> insertnode) throws Exception{
        String tablename = "diag_model_gov";
        String creatsql = "CREATE TABLE "+tablename+"(node_id int not null, father_id int, " +
                "gate varchar(1) not null, primary key (node_id))";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if(tablename != null){
                ResultSet tables = conn.getMetaData().getTables(conn.getCatalog(),null,tablename, new String[]{"TABLE"});
                if (tables.next()){
//                    throw new Exception("该表名已存在，请重新输入！");
                    System.out.println("该表名已存在");
                    return 0;
                }
                tables.close();
                Statement creatstate = conn.createStatement();
                creatstate.execute(creatsql);
                sql = conn.prepareStatement("insert into "+tablename+" values(?,?,?)");
                for (int i=0;i<insertnode.size();i++){
                    sql.setInt(1,Integer.parseInt(insertnode.get(i).getId()));
                    sql.setInt(2,name2id(insertnode.get(i).getFather(),insertnode));
                    sql.setString(3,insertnode.get(i).getGate());
                    sql.executeUpdate();
                }
                conn.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return 1;
    }

//    存放单次诊断结果
    public static int savediagres(long time, ArrayList<Node> resultnode){
        String tablename = "Diag_Gov_";
        tablename += time;
        String creatsql = "CREATE TABLE "+tablename+"(node_id int not null, fre float, " +
                "sugg_id int, primary key (node_id))";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if(tablename != null){
                ResultSet tables = conn.getMetaData().getTables(conn.getCatalog(),null,tablename, new String[]{"TABLE"});
                if (tables.next()){
//                    throw new Exception("该表名已存在，请重新输入！");
                    System.out.println("该表名已存在");
                    return 0;
                }
                tables.close();
                Statement creatstate = conn.createStatement();
                creatstate.execute(creatsql);
                sql = conn.prepareStatement("insert into "+tablename+" values(?,?,?)");
                for (int i=0;i<resultnode.size();i++){
                    sql.setInt(1,Integer.parseInt(resultnode.get(i).getId()));
                    sql.setFloat(2, resultnode.get(i).getX());
                    sql.setInt(3,-1);
                    sql.executeUpdate();
                }
                conn.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return 1;
    }

}

