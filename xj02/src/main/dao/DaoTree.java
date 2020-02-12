package main.dao;

//import com.mysql.jdbc.TimeUtil;
import main.util.DataBoolUtils;
import main.util.DataFloatUtils;
import main.util.TimeUtils;
import main.util.mysqlUtil;


import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoTree {
    private Connection conn = null;     //声明数据库的连接对象
    private Statement stmt = null;       //声明statement对象
    private ResultSet rs = null;       //声明result对象

    // 仙居数据数据库，此处为自己建立的测试库，库为空
    //以下为mysql 8.0及以上的连接字段
    private String driver = "com.mysql.jdbc.Driver";
//     private String url="jdbc:mysql://218.197.228.243:3306/XJdatabase_core ";//学校远程数据库
//     private static String url_FLOAT = "jdbc:mysql://192.168.1.194:3306/XJdatabase_core";//华东院
//    public static String url_BOOL = "jdbc:mysql://192.168.1.194:3306/XJdatabase_boolean";//华东院
    final static String url_BOOL = "jdbc:mysql://localhost:3306/xjdatabase_boolean?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";//本地
    final static String url_FLOAT = "jdbc:mysql://localhost:3306/xjdatabase_core?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    //?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
//     private String user="remote";//远程
    final static String user = "root";//本地
    private String password = "123456";


    static Statement sql;
    static Connection con;
    static int columnCount;
    private List<String> num = new ArrayList<>();
    ResultSetMetaData data = null;
    String sql1 = null;

  /*  public Connection getConnection(String url_databse) {  //BOOL数据连接
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url_databse, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
        return con;
    }*/
  public Connection getConnection() {  //BOOL数据连接
      try {
          Class.forName(driver);
          con = DriverManager.getConnection(url_BOOL,user,password);
          System.out.println("数据库连接成功");
      } catch (SQLException | ClassNotFoundException e) {
          e.printStackTrace();
          System.out.println("数据库连接失败！");
      }
      return con;
  }
    //关闭数据库
    private void closeALL() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    // 获取了SQL语句，返回sqls一个数组
    public ArrayList<String> getSQL(String table, String id, long starttime, long endtime){
        if (starttime > endtime){
            long tmp = starttime;
            starttime = endtime;
            endtime = tmp;
        }
        con=getConnection();
        try{
            sql = con.createStatement();
            rs = sql.executeQuery("select * from"+table);
            data = rs.getMetaData();
            for(int i = 1; i < data.getColumnCount();i++){
                //获得所有列的数目及实际列数
                columnCount = data.getColumnCount();
                //获得指定列名
                num.add(data.getColumnName(i));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        ArrayList<String> sqls = new ArrayList<String>();
        for(String n:num){
            if(n.equals(id)){
                String sql1="SELECT Top 10000 Time," + id + "from" + table + " WHERE Time>"+starttime*1000+" AND Time<"+endtime*1000+" ORDER BY time;";
                System.out.println(sql1);
                sqls.add(sql1);
            }
        }
        return sqls;
    }


    /**
     * 这里假设测点数据库的表名为table，其中列名为id：测点id，Name：测点名称，
     * value:测点测到的数值，Time:测点记录的时间
     * 若实际数据库表格发生变化，则在此处做出相应的调整。
     */
    // 查询boolean型数据
    public DataBoolUtils queBool(String id, long endtime)throws ClassNotFoundException, SQLException, ParseException {
        long starttime = endtime - 1800;    //取半小时数据
//        System.out.println(starttime);     //输出时间
        Date d1 = TimeUtils.longToDate(endtime*1000,"yyyy-MM-dd HH:mm:ss");
        Date d2 = TimeUtils.longToDate(starttime*1000,"yyyy-MM-dd HH:mm:ss");
        Date[] time_d={d2,d1};
//        System.out.println(d2);
//        System.out.println(d1);
        List<mysqlUtil.resBool> rb=mysqlUtil.searchBool(time_d,Integer.parseInt(id));
        DataBoolUtils data = new DataBoolUtils(id);
        for (int b_i=0;b_i<rb.size();b_i++) {
            data.addTime(rb.get(b_i).getTime());
            data.addValue(rb.get(b_i).getValue());

//            System.out.println(rb.get(b_i).getTime());
//            System.out.println(rb.get(b_i).getValue());

        }
        return data;


//        conn=getConnection();
//        long starttime = endtime - 604800;    //取一周数据
////        ArrayList<String> sqls = getSQL(table, id, starttime, endtime);
//        sql1 = "SELECT * FROM "+table+" WHERE Time>"+starttime+" AND Time<"+endtime+" ORDER BY time;";
////        sql1 = "select * from "+table;
//        DataUtils data = new DataUtils(table, id);
//        try {
//            stmt = conn.createStatement();
//            // 执行数据库查询语句
//            rs = stmt.executeQuery(sql1);
//            while (rs.next()) {
////                System.out.println(1);
//                data.addTime(rs.getLong(1));
//                data.addValue(rs.getDouble(2));
//            }
//        } catch (SQLException e) {
//        }
//        return data;
    }

//    public DataBoolUtils queBoolDay(String table, String id, long endtime)throws ClassNotFoundException, SQLException{
//        conn=getConnection();
//        long starttime = endtime - 86400;   //取一天数据
////        ArrayList<String> sqls = getSQL(table, id, starttime, endtime);
//        sql1 = "SELECT * FROM "+table+" WHERE Time>"+starttime+" AND Time<"+endtime+" ORDER BY time;";
//        DataBoolUtils data = new DataBoolUtils(id);
//        try {
//            stmt = conn.createStatement();
//            // 执行数据库查询语句
//                rs = stmt.executeQuery(sql1);
//                while (rs.next()) {
//                    data.addTime(rs.getLong("Time"));
//                    data.addValue(rs.getDouble("Value"));
//                }
//        } catch (SQLException e) {
//        }
//        return data;
//    }

    // 查询float型数据
    public DataFloatUtils queFloat(String id, long endtime)throws ParseException,ClassNotFoundException, SQLException{
        long starttime = endtime - 1800;    //取半小时数据
        Date d1 = TimeUtils.longToDate(endtime*1000, "yyyy-MM-dd HH:mm:ss");
        Date d2 = TimeUtils.longToDate(starttime*1000,"yyyy-MM-dd HH:mm:ss");
        Date[] time_d={d1,d2};
        List<mysqlUtil.resFloat> rf=mysqlUtil.searchFloat(time_d,Integer.parseInt(id));
        DataFloatUtils data = new DataFloatUtils(id);
        for (int f_i=0;f_i<rf.size();f_i++) {
            data.addTime(rf.get(f_i).getTime());
            data.addValue(rf.get(f_i).getValue());
        }
        return data;
    }




    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    //主函数测试数据库连接是否成功
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree c = new DaoTree();
//        c.getConnection();
        Date date1 = new Date();
        String date = TimeUtils.dateToString(date1,"yyyy-MM-dd HH:mm:ss");
        Long time = TimeUtils.stringToLong(date,"yyyy-MM-dd HH:mm:ss");
//        DataUtils d = c.queBool("table_336_2019_12_26", "336", time);
//        System.out.println(d.getValue());
//        System.out.println(date1.getMonth());
        String da = "09";
        System.out.println(da+(Integer.parseInt(da)));

    }

     }