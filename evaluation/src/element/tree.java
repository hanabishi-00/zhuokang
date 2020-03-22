package element;

import util.excelUtil;
import util.timeUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

/*import static util.mysqlUtil.*;
import static util.mysqlUtil.minn;*/

import static util.excelUtil.readExcel;
import static util.excelUtil.writeExcel;
import static util.hdbUtil.*;
import static util.hdbUtil.minn;

public class tree {
    private node[] mid_nodes;
    private leafnode[] leaf_nodes;

    public static final int work_condition_turbine = 1;
    public static final int work_condition_pump = -1;
    public static final int mysql_online_node_mark = 1;//在mysql数据库中存储叶子节点类型时的标志，1为在线，0为离线
    public static final int mysql_offline_node_mark = 0;
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    /*private static final String DB_URL_res="jdbc:mysql://218.197.228.243:3306/result";
    private static final String USR="remote";
    private static final String PSW="123456";*/

    private static final String DB_URL_res = "jdbc:mysql://localhost:3306/result?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
    private static final String USR = "root";
    private static final String PSW = "root1234";

    public static class timeDescriber{
        private String type;
        private int[] unit_set=new int[]{1};

        public timeDescriber(String input_type){
            type=input_type;
        }

        public timeDescriber(String input_type, int[] input_unit_set){
            type=input_type;
            unit_set=input_unit_set;
        }

        public String getType(){
            return type;
        }

        public int[] getUnit_set(){
            return unit_set;
        }
    }

    public tree(String file_addr) {
        tree_builder(file_addr);
    }

    public tree() {

    }

    public void tree_builder(String file_addr) {//由excel导入树结构
        List tree_content = excelUtil.readExcel(file_addr);//读取excel文件中内容
        int[] present_fa = new int[3];//各位分别表示当前根节点、当前一级中间节点和当前二级中间节点
        for (int i = 1; i < tree_content.size(); i++) {//跳过表头，从第二行开始读取
            List row_content = (List) tree_content.get(i);
            int col_num = row_content.size();
            //根据读到内容的长度进行不同的操作
            if (col_num >= 10) {//内容长度≥10则意味着要写入叶子节点
                int leaf_displacement = 0;//叶子节点在row_content中的偏置
                if (col_num >= 12) {//内容长度≥12则意味着要写入二级中间节点
                    leaf_displacement = leaf_displacement + 2;
                    int second_mid_displacement = 0;//二级中间节点在row_content中的偏置
                    if (col_num >= 14) {//内容长度≥14则意味着要写入一级中间节点
                        leaf_displacement = leaf_displacement + 2;
                        second_mid_displacement = second_mid_displacement + 2;
                        int first_mid_displacement = 0;//一级中间节点在row_content中的偏置
                        if (col_num >= 16) {//内容长度≥16则意味着要写入根节点
                            leaf_displacement = leaf_displacement + 2;
                            second_mid_displacement = second_mid_displacement + 2;
                            first_mid_displacement = first_mid_displacement + 2;
                            present_fa[0] = (int) Float.parseFloat(row_content.get(0).toString());
                            System.out.print("完成父节点初始化");
                            root_node(row_content.get(1).toString());//添加根节点
                        }
                        present_fa[1] = (int) Float.parseFloat(row_content.get(first_mid_displacement).toString());
                        add_node((int) Float.parseFloat(row_content.get(first_mid_displacement).toString()),
                                row_content.get(first_mid_displacement + 1).toString(), present_fa[0]);//添加一级部件中间节点
                    }
                    present_fa[2] = (int) Float.parseFloat(row_content.get(second_mid_displacement).toString());
                    add_node((int) Float.parseFloat(row_content.get(second_mid_displacement).toString()),
                            row_content.get(second_mid_displacement + 1).toString(), present_fa[1]);//添加二级部件中间节点
                }

                String[] leaf_description = new String[3];
                for (int j = 0; j < 3; j++) {
                    leaf_description[j] = row_content.get(j + leaf_displacement + 4).toString();
                }
                String state = row_content.get(leaf_displacement + 7).toString();
                if ("离线".equals(state)) {//如果是离线点
                    add_node((int) Float.parseFloat(row_content.get(leaf_displacement).toString()),
                            row_content.get(leaf_displacement + 1).toString(), present_fa[2],
                            (int) Float.parseFloat(row_content.get(leaf_displacement + 3).toString()), leaf_description);
                } else {
                    //处理测点格式
                    Map<Integer, String[][]> node_points = new HashMap<>();
                    String float_point = row_content.get(leaf_displacement + 8).toString();//单精量测点
                    String bool_point = row_content.get(leaf_displacement + 9).toString();//状态量测点
                    String[] FP_set = float_point.split("}");
                    String[] BP_set = bool_point.split("}");
                    if (FP_set.length == BP_set.length) {
                        for (int i_fp = 0; i_fp < FP_set.length; i_fp++) {
                            String unit_fP = FP_set[i_fp].substring(FP_set[i_fp].indexOf("{") + 1);
                            String unit_bp = BP_set[i_fp].substring(BP_set[i_fp].indexOf("{") + 1);

                            List<String[]> points=new ArrayList<>();
                            for (String single_point : unit_fP.split("；")) {//存入单精量测点
                                points.add(new String[]{single_point,"float"});
                                System.out.println("叶子节点 " +
                                            (int) Float.parseFloat(row_content.get(leaf_displacement).toString()) +
                                            " 机组 " + (i_fp + 1) + "#" +
                                            " 单精量测点: " + single_point);
                            }

                            for (String single_point : unit_bp.split("；")) {//存入状态量测点
                                points.add(new String[]{single_point,"bool"});
                                    System.out.println("叶子节点 " +
                                            (int) Float.parseFloat(row_content.get(leaf_displacement).toString()) +
                                            " 状态量测点: " + single_point);
                            }

                            //录入测点Map中
                            String[][] p_str=new String[points.size()][];
                            for (int i_p=0;i_p<points.size();i_p++){
                                p_str[i_p]=points.get(i_p);
                            }

                            node_points.put(i_fp + 1, p_str);
                        }
                        //写入节点
                        add_node((int) Float.parseFloat(row_content.get(leaf_displacement).toString()),
                                row_content.get(leaf_displacement + 1).toString(), present_fa[2],
                                (int) Float.parseFloat(row_content.get(leaf_displacement + 3).toString()),
                                leaf_description, node_points);
                    }
                }
            }
        }
    }

    public static tree tree_reader(String type) {//取数据库中已有的树
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        return tree_reader(type, database_url, user, psw);
    }

    public static tree tree_reader(String type, String database_url, String user, String psw) {//取数据库中已有的树
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;

        tree temp_tree = new tree();//新建空树

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行该种模型最新版本搜索
             */

            String tb_name_guide = "eva_model_guide";
            String conditions = "type= '" + type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新建模时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            开始读取中间节点
             */
            String tb_name_mid = "eva_model_" + type + "_" + version + "_mid";
            String sql_mid = "SELECT * FROM " + tb_name_mid;//搜索中间节点表的数据
            ResultSet rs_mid = stmt.executeQuery(sql_mid);

            // 循环存入所有中间节点信息
            int index, father;
            String name;
            while (rs_mid.next()) {
                index = rs_mid.getInt("id");
                father = rs_mid.getInt("father");
                name = rs_mid.getString("name");

                temp_tree.add_node(index, name, father);
            }
            rs_mid.close();

            /*
            开始读取叶子节点
             */
            String tb_name_leaf = "eva_model_" + type + "_" + version + "_leaf";
            String sql_leaf_base = "SELECT * FROM " + tb_name_leaf;//搜索中间节点表的数据

            ResultSet rs_leaf = stmt.executeQuery(sql_leaf_base);
            // 循环存入所有叶子节点信息
            int weight;
            boolean state;
            String[] stan_description;
            Map<Integer, String[][]> points;
            while (rs_leaf.next()) {
                index = rs_leaf.getInt("id");
                father = rs_leaf.getInt("father");
                name = rs_leaf.getString("name");

                weight = rs_leaf.getInt("weight");
                state = leafnode.state_offline_mark;
                if (rs_leaf.getInt("state") == mysql_online_node_mark) {
                    state = leafnode.state_online_mark;
                }
                String ts = rs_leaf.getString("stan_description");
                String tp = rs_leaf.getString("points");
                stan_description = operation_stan(ts);

                if (state == leafnode.state_offline_mark) {//离线点
                    temp_tree.add_node(index, name, father, weight, stan_description);
                } else {//在线点
                    points = operation_points(tp);
                    temp_tree.add_node(index, name, father, weight, stan_description, points);
                }
            }
            rs_leaf.close();
            //}


            //赋值
            temp_tree.init_val();
            node[] tt_mid_nodes = temp_tree.get_mid_nodes();
            leafnode[] tt_leaf_nodes = temp_tree.get_leaf_nodes();
            System.out.println("Tree " + tt_mid_nodes[0].get_name() + " has been successfully read!");

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return temp_tree;
    }



    public static void tree_saver(tree s_tree, String type) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        tree_saver(s_tree, type, database_url, user, psw);
    }

    public static void tree_saver(tree s_tree, String type, String database_url, String user, String psw) {
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            首先写入模型版本导航表中
             */

            String tb_name_guide = "eva_model_guide";
            String tb_create = "CREATE TABLE IF NOT EXISTS " + tb_name_guide + " (" +
                    "time INT(11) NOT NULL, type VARCHAR(10) NOT NULL, " +
                    "version INT(11) NOT NULL, PRIMARY KEY(time, type))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(tb_create);//如果引导表不存在则先建表

            String conditions = "type= '" + type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//最新记录
            ResultSet rs = stmt.executeQuery(sql);

            int version = 0;
            // 保存最后一条记录
            if (rs.next()) {
                rs.last();
                String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
                version = rs.getInt("version");//最新版本号

                // 输出数据
                System.out.print("前一建模时间: " + tm);
                System.out.print(", 前一版本号: " + version);
                System.out.print("\n");
            }

            Calendar c = Calendar.getInstance();
            Date time_now = c.getTime();
            int year_old=(int)version/10000;
            int year_new = c.get(Calendar.YEAR);
            long tm_new = timeUtil.dateToLong(time_now) / 1000;
            int version_new = year_old * 10000 + version % 10000 + 1;
            if (year_new!=year_old){
                version_new = year_new *10000 +1;
            }

            System.out.print("最新建模时间: " + tm_new);
            System.out.print(", 最新版本号: " + version_new);
            System.out.print("\n");

            //写入数据库
            String value_set_guide = "(" + tm_new + ",'" + type + "', " + version_new + ")";
            sql = "INSERT INTO " + tb_name_guide + " VALUES " + value_set_guide;
            stmt.executeUpdate(sql);

            /*
            创建中间节点表
             */

            String table_name_mid = "eva_model_" + type + "_" + version_new + "_mid";
            String sql_mid = "CREATE TABLE IF NOT EXISTS " + table_name_mid + " (" +
                    "id INT(11) NOT NULL, name VARCHAR(30) NOT NULL, father INT(11) NOT NULL, " +
                    "PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(sql_mid);//在数据库中创建当前模型的中间节点表
            node[] s_mid_nodes = s_tree.get_mid_nodes();
            String value_set_mid = null;
            for (int i = 0; i < s_mid_nodes.length; i++) {//按顺序写入所有中间节点的信息
                node current_node = s_mid_nodes[i];
                value_set_mid = "(" + current_node.get_index() + ", '" + current_node.get_name() + "', " + current_node.get_father() + ")";
                sql_mid = "INSERT INTO " + table_name_mid + " VALUES " + value_set_mid;

                stmt.executeUpdate(sql_mid);
                System.out.println("中间节点 " + current_node.get_index() + " " + current_node.get_name() + " 存入数据库成功！");
            }

            /*
            创建叶子节点表
             */

            String table_name_leaf = "eva_model_" + type + "_" + version_new + "_leaf";
            String sql_leaf = "CREATE TABLE IF NOT EXISTS " + table_name_leaf + " " +
                    "(id INT(11) NOT NULL, name VARCHAR(30) NOT NULL, father INT(11) NOT NULL," +
                    "weight TINYINT(2) NOT NULL, state TINYINT(1) NOT NULL, stan_description VARCHAR(300) NOT NULL," +
                    "points VARCHAR(1000) NOT NULL, PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(sql_leaf);//在数据库中创建当前模型的叶子节点表
            leafnode[] s_leaf_nodes = s_tree.get_leaf_nodes();
            String value_set_leaf = null;
            for (int i = 0; i < s_leaf_nodes.length; i++) {//按顺序写入所有中间节点的信息
                leafnode current_node = s_leaf_nodes[i];
                int mysql_node_state = mysql_offline_node_mark;
                if (current_node.get_state() == leafnode.state_online_mark) {//如果是在线叶子节点则存入1
                    mysql_node_state = mysql_online_node_mark;
                }
                String[] CN_stan_description_set = current_node.get_standard_description();
                Map<Integer, String[][]> CN_points_set = current_node.get_point_index();
                String CN_stan_description = operation_stan(CN_stan_description_set);
                String CN_points = "-";
                if (CN_points_set != null) {//如果是在线叶子节点
                    CN_points = operation_points(CN_points_set);
                }

                value_set_leaf = "(" + current_node.get_index() + ", '" + current_node.get_name() + "', "
                        + current_node.get_father() + ", " + current_node.get_weight() + ", "
                        + mysql_node_state + ", '" + CN_stan_description + "', '" + CN_points + "')";
                sql_leaf = "INSERT INTO " + table_name_leaf + " VALUES " + value_set_leaf;

                stmt.executeUpdate(sql_leaf);
                System.out.println("叶子节点 " + current_node.get_index() + " " + current_node.get_name() + " 存入数据库成功！");
            }

            System.out.println("Tree " + s_tree.get_mid_node(0).get_name() + " has been successfully saved!");

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static Map<String, Map<Integer, String[][]>> threshold_builder(String file_addr) {//由excel导入阈值表
        Map<Integer, String[][]> float_th = threshold_builder(file_addr, 0);
        Map<Integer, String[][]> bool_th = threshold_builder(file_addr, 1);
        Map<String, Map<Integer, String[][]>> res = new HashMap<>();
        res.put("float", float_th);
        res.put("bool", bool_th);
        tree.threshold_saver(res);//存入数据库中
        return res;
    }

    public static Map<Integer, String[][]> threshold_builder(String file_addr, int sheet_id) {//由excel导入阈值表
        Map<Integer, String[][]> res = new HashMap<>();
        List th_content = excelUtil.readExcel(file_addr, sheet_id);//读取excel文件中内容
        for (int i = 1; i < th_content.size(); i++) {//跳过表头，从第二行开始读取
            List row_content = (List) th_content.get(i);

            int sz = (row_content.size() - 1) / 7;//需要创建的阈值保存数组的行数（单精量有2行，分别保存抽水和发电工况）
            String[][] point_threshold_value = new String[sz][7];
            for (int j = 0; j < sz; j++) {
                for (int jj = 0; jj < 7; jj++) {
                    point_threshold_value[j][jj] = row_content.get(j * 7 + jj + 1).toString();
                }
            }

            int point_index = (int) Float.parseFloat(row_content.get(0).toString());
            res.put(point_index, point_threshold_value);
            if (sz > 1) {
                System.out.print("完成单精量测点 " + point_index + " 阈值建立\n");
            } else {
                System.out.print("完成状态量测点 " + point_index + " 阈值建立\n");
            }
        }
        if (sheet_id == 0) {
            System.out.println("所有单精量测点阈值建立完成！");
        } else {
            System.out.println("所有状态量测点阈值建立完成！");
        }
        return res;
    }

    /*public static Map<String, Map<Integer, String[][]>> threshold_reader(tree model) {//直接用此函数即可读取数据库中存储的阈值
        Map<Integer, String[][]> float_th = threshold_reader("float", model);
        Map<Integer, String[][]> bool_th = threshold_reader("bool", model);
        Map<String, Map<Integer, String[][]>> res = new HashMap<>();
        res.put("float", float_th);
        res.put("bool", bool_th);
        return res;
    }

    public static Map<Integer, String[][]> threshold_reader(String data_type, tree model) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        Map<Integer, String[][]> res = threshold_reader(data_type, model, database_url, user, psw);
        return res;
    }

    public static Map<Integer, String[][]> threshold_reader(String data_type, tree model, String database_url, String user, String psw) {//type为float或bool
        Map<Integer, String[][]> res = new HashMap<>();//String[][]分别存储抽水态和发电态的阈值
        final int type_mark;
        if (data_type.equals("bool")) {//依据type确定其标志
            type_mark = leafnode.bool_mark;
        } else {
            data_type = "float";
            type_mark = leafnode.float_mark;
        }

        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;

        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            *//*
            进行阈值最新版本搜索
             *//*

            String tb_name_guide = "eva_threshold_guide";
            String conditions = "type= '" + data_type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新阈值存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            *//*
            按树中叶子节点涉及的该类测点进行评价阈值存储
             *//*
            leafnode[] temp_LN = model.get_leaf_nodes();
            String sql_th_query = null, condition = null;
            String tb_name_query = "eva_threshold_" + data_type + "_" + version;
            for (int j = 0; j < temp_LN.length; j++) {
                if (temp_LN[j].get_state() == leafnode.state_online_mark) {
                    Map<Integer, int[][]> node_points = temp_LN[j].get_point_index();//取节点中所有测点编号
                    for (int ii = 0; ii < node_points.size(); ii++) {//对每个机组的
                        int[][] unit_points = node_points.get(ii + 1);//机组号从1开始，ii需+1
                        for (int jj = 0; jj < unit_points.length; jj++) {//每个测点
                            if ((unit_points[jj][0] != -1) && (unit_points[jj][1] == type_mark)) {
                                condition = "id=" + unit_points[jj][0];
                                sql_th_query = "SELECT * FROM " + tb_name_query + " WHERE " + condition;
                                rs = stmt.executeQuery(sql_th_query);
                                rs.next();
                                Integer point_id = rs.getInt("id");
                                String[][] threshold_values = operation_threshold(rs.getString("threshold"));
                                res.put(point_id, threshold_values);
                                System.out.println(data_type + " point -- " + point_id + " -- threshold has been successfully read!");
                            }
                        }
                    }
                }
            }
            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return res;
    }

    public Map<String, Map<Integer, String[][]>> threshold_reader(int leaf_node_ind, String database_url, String user, String psw) {//按叶子节点查询其包含的测点的阈值
        Map<Integer, String[][]> res_float = new HashMap<>();//String[]分别存储抽水态和发电态的阈值
        Map<Integer, String[][]> res_bool = new HashMap<>();//String[]分别存储抽水态和发电态的阈值
        Map<String, Map<Integer, String[][]>> res = new HashMap<>();

        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        final String[] type = {"float", "bool"};
        final leafnode current_node = get_leaf_node_by_index(leaf_node_ind);
        Map<Integer, int[][]> node_points = current_node.get_point_index();
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            *//*
            进行阈值最新版本搜索
             *//*

            //float型测点
            String tb_name_guide = "eva_threshold_guide";
            String conditions = "type= '" + type[0] + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version_float = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新float阈值存储时间: " + tm);
            System.out.print(", 最新版本号: " + version_float);
            System.out.print("\n");

            //bool型测点
            conditions = "type= '" + type[1] + "'";
            sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version_bool = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新bool阈值存储时间: " + tm);
            System.out.print(", 最新版本号: " + version_bool);
            System.out.print("\n");

            *//*
            按树中叶子节点涉及的该类测点进行评价阈值存储
             *//*

            String sql_th_query, condition, tb_name_query;
            for (int ii = 0; ii < node_points.size(); ii++) {//对每个机组的
                int[][] unit_points = node_points.get(ii + 1);
                for (int jj = 0; jj < unit_points.length; jj++) {//每个测点
                    if ((unit_points[jj][0] != -1) && (unit_points[jj][1] == leafnode.float_mark)) {
                        tb_name_query = "eva_threshold_" + type[0] + "_" + version_float;
                        condition = "id=" + unit_points[jj][0];
                        sql_th_query = "SELECT * FROM " + tb_name_query + " WHERE " + condition;

                        rs = stmt.executeQuery(sql_th_query);
                        Integer point_id = rs.getInt("id");
                        String[][] threshold_values = operation_threshold(rs.getString("threshold"));
                        res_float.put(point_id, threshold_values);
                    } else if ((unit_points[jj][0] != -1) && (unit_points[jj][1] == leafnode.bool_mark)) {
                        tb_name_query = "eva_threshold_" + type[1] + "_" + version_bool;
                        condition = "id=" + unit_points[jj][0];
                        sql_th_query = "SELECT * FROM " + tb_name_query + " WHERE " + condition;

                        rs = stmt.executeQuery(sql_th_query);
                        Integer point_id = rs.getInt("id");
                        String[][] threshold_values = operation_threshold(rs.getString("threshold"));
                        res_bool.put(point_id, threshold_values);
                    }
                }
            }
            res.put("float", res_float);
            res.put("bool", res_bool);

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return res;
    }*/

    public static String[][] threshold_reader(int point_id, String point_type) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        return threshold_reader(point_id, point_type, database_url, user, psw);
    }

    public static String[][] threshold_reader(int point_id, String point_type, String database_url, String user, String psw){//根据测点及类型查阈值
        /*point_type为float或bool*/
        String[][] res = new String[0][];
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行阈值最新版本搜索
             */

            //float型测点
            String tb_name_guide = "eva_threshold_guide";
            String conditions = "type= '" + point_type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新"+point_type+"阈值存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            访问测点对应阈值并进行存储
             */

            String sql_th_query, condition, tb_name_query;
            tb_name_query="eva_threshold_"+point_type+"_"+version;
            condition="id="+point_id;
            sql_th_query="SELECT * FROM "+tb_name_query+" WHERE "+condition;
            rs=stmt.executeQuery(sql_th_query);
            rs.next();

            res=operation_threshold(rs.getString("threshold"));

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return res;
    }

    public static void threshold_saver(Map<String, Map<Integer, String[][]>> threshold_map) {
        threshold_saver(threshold_map.get("float"), "float");
        threshold_saver(threshold_map.get("bool"), "bool");
    }

    public static void threshold_saver(Map<Integer, String[][]> threshold_map, String type) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        threshold_saver(threshold_map, type, database_url, user, psw);
    }

    public static void threshold_saver(Map<Integer, String[][]> threshold_map, String type, String database_url, String user, String psw) {
        //threshold_map的存储格式为<测点编号，{抽水工况阈值}{发电工况阈值}>（单精量）或<测点编号，{阈值}>（状态量）
        if (!threshold_map.isEmpty()) {
            final String JDBC_DRIVER = DB_DRIVER;
            final String DB_URL = database_url;
            final String USER = user;
            final String PASS = psw;
            Connection conn = null;
            Statement stmt = null;
            try {
                // 注册 JDBC 驱动
                Class.forName(JDBC_DRIVER);

                // 打开链接
                System.out.println("连接数据库...");

                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                // 执行查询
                System.out.println(" 实例化Statement对象...");
                stmt = conn.createStatement();

                /*
                查询阈值表引导
                 */
                String tb_name_guide = "eva_threshold_guide";
                String tb_create = "CREATE TABLE IF NOT EXISTS " + tb_name_guide + " " +
                        "(time INT(11) NOT NULL, type VARCHAR(10) NOT NULL, " +
                        "version INT(11) NOT NULL, PRIMARY KEY(time, type))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                stmt.execute(tb_create);//如果引导表不存在则先建表

                String conditions = "type= '" + type + "'";
                String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//最新记录
                ResultSet rs = stmt.executeQuery(sql);

                int version = 0;
                // 保存最后一条记录
                if (rs.next()) {
                    rs.last();
                    String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
                    version = rs.getInt("version");//最新版本号

                    // 输出数据
                    System.out.print("前一阈值表保存时间: " + tm);
                    System.out.print(", 前一版本号: " + version);
                    System.out.print("\n");
                }

                Calendar c = Calendar.getInstance();
                Date time_now = c.getTime();
                int year_old=(int)version/10000;
                int year_new = c.get(Calendar.YEAR);
                long tm_new = timeUtil.dateToLong(time_now) / 1000;
                int version_new = year_old * 10000 + version % 10000 + 1;
                if (year_new!=year_old){
                    version_new = year_new *10000 +1;
                }

                System.out.print("最新阈值表保存时间: " + tm_new);
                System.out.print(", 最新版本号: " + version_new);
                System.out.print("\n");

                //写入数据库
                String value_set_guide = "(" + tm_new + ", '" + type + "', " + version_new + ")";
                sql = "INSERT INTO " + tb_name_guide + " VALUES " + value_set_guide;
                stmt.executeUpdate(sql);

                /*
                创建阈值表
                */

                String table_name_th = "eva_threshold_" + type + "_" + version_new;
                String sql_th = "CREATE TABLE IF NOT EXISTS " + table_name_th + " " +
                        "(id INT(11) NOT NULL, threshold VARCHAR(300) NOT NULL, " +
                        "PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                stmt.execute(sql_th);//在数据库中创建当前模型的测点阈值表

                Set<Integer> keySet = threshold_map.keySet();
                Iterator<Integer> keyIter = keySet.iterator();
                while (keyIter.hasNext()) {//按顺序写入所有测点阈值的信息
                    Integer current_point = keyIter.next();//测点编号
                    String th_set = operation_threshold(threshold_map.get(current_point));//测点阈值
                    String value_set_th = "(" + current_point + ", '" + th_set + "')";

                    sql_th = "INSERT INTO " + table_name_th + " VALUES " + value_set_th;//插入

                    stmt.executeUpdate(sql_th);
                    System.out.println("测点 " + current_point + " 阈值 " + th_set + " 存入数据库成功！");
                }

                rs.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2) {
                }// 什么都不做
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    public void root_node(String nam) {//初始化树，建立根节点
        add_node(0, nam, -1);
    }

    public void init_val() {//初始化树中所有节点的值，应在每次评价前执行
        for (int i = 1; i < mid_nodes.length; i++) {
            mid_nodes[i].init_value();
        }
        for (int j = 0; j < leaf_nodes.length; j++) {
            leaf_nodes[j].init_value();
        }
    }

    public leafnode[] get_leaf_nodes() {
        return leaf_nodes;
    }

    public leafnode[] get_leaf_nodes(int[] ids) {
        leafnode[] rs = new leafnode[ids.length];
        for (int i = 0; i < ids.length; i++) {
            rs[i] = get_leaf_node(i);
        }
        return rs;
    }

    public leafnode get_leaf_node(int i) {
        return leaf_nodes[i];
    }

    public leafnode get_leaf_node_by_index(int index) {//按节点编号取
        leafnode res = null;
        for (int i = 0; i < leaf_nodes.length; i++) {
            if (leaf_nodes[i].get_index() == index) {
                res = leaf_nodes[i];
                break;
            }
        }
        return res;
    }

    public static int[] get_offline_leaf_node_index(tree tr) {
        List<Integer> res_list = new ArrayList<>();
        leafnode[] LFN = tr.get_leaf_nodes();
        for (int i = 0; i < LFN.length; i++) {
            if (LFN[i].get_state() == leafnode.state_offline_mark) {
                res_list.add(LFN[i].get_index());
            }
        }

        int[] res = new int[res_list.size()];
        for (int i = 0; i < res_list.size(); i++) {
            res[i] = res_list.get(i);
        }
        return res;
    }

    public node[] get_mid_nodes() {
        return mid_nodes;
    }

    public node[] get_mid_nodes(int[] ids) {
        node[] rs = new node[ids.length];
        for (int i = 0; i < ids.length; i++) {
            rs[i] = get_mid_node(i);
        }
        return rs;
    }

    public node get_mid_node(int i) {
        return mid_nodes[i];
    }

    public node get_mid_node_by_index(int index) {//按节点编号取
        node res = null;
        for (int i = 0; i < mid_nodes.length; i++) {
            if (mid_nodes[i].get_index() == index) {
                res = mid_nodes[i];
                break;
            }
        }
        return res;
    }

    //新增节点
    public void add_node(int ind, String nam, int fa) {//新增中间节点
        if (this.add_check(ind, fa)) {
            System.out.print("Adding node " + ind + " " + nam);
            node temp_node = new node(ind, nam, fa);
            if (mid_nodes != null) {
                int m_node_num = mid_nodes.length;
                node[] temp_mid_nodes = new node[m_node_num + 1];
                System.arraycopy(mid_nodes, 0, temp_mid_nodes, 0, m_node_num);
                temp_mid_nodes[m_node_num] = temp_node;
                mid_nodes = temp_mid_nodes;
                System.out.println("Successfully added!");
            } else {
                node[] TMN = new node[1];
                TMN[0] = temp_node;
                mid_nodes = TMN;
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Wrong nodes' relation! Fail to add node!");
        }
    }

    public void add_node(int ind, String nam, int fa, int w, String[] st_des, Map<Integer, String[][]> p_ind) {//新增在线叶子节点
        if (add_check(ind, fa)) {
            System.out.print("Adding node " + ind + " " + nam);
            leafnode temp_node = new leafnode(ind, nam, fa, w, st_des, p_ind);
            if (leaf_nodes != null) {
                int m_node_num = leaf_nodes.length;
                leafnode[] temp_leaf_nodes = new leafnode[m_node_num + 1];
                System.arraycopy(leaf_nodes, 0, temp_leaf_nodes, 0, m_node_num);
                temp_leaf_nodes[m_node_num] = temp_node;
                leaf_nodes = temp_leaf_nodes;
                System.out.println("Successfully added!");
            } else {
                leafnode[] TLN = new leafnode[1];
                TLN[0] = temp_node;
                leaf_nodes = TLN;
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Wrong nodes' relation! Fail to add node!");
        }
    }

    public void add_node(int ind, String nam, int fa, int w, String[] st_des) {//新增离线叶子节点
        if (add_check(ind, fa)) {
            System.out.print("Adding node " + ind + " " + nam);
            leafnode temp_node = new leafnode(ind, nam, fa, w, st_des);
            if (leaf_nodes != null) {
                int m_node_num = leaf_nodes.length;
                leafnode[] temp_leaf_nodes = new leafnode[m_node_num + 1];
                System.arraycopy(leaf_nodes, 0, temp_leaf_nodes, 0, m_node_num);
                temp_leaf_nodes[m_node_num] = temp_node;
                leaf_nodes = temp_leaf_nodes;
                System.out.println("Successfully added!");
            } else {
                leafnode[] TLN = new leafnode[1];
                TLN[0] = temp_node;
                leaf_nodes = TLN;
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Wrong nodes' relation! Fail to add node!");
        }
    }

    //删除节点
    public void delete_node(int ind) {//删除节点
        int[] type_loc = node_type_loc(ind);
        switch (type_loc[0]) {
            case 1://叶子节点，直接删除
                del_single_node(type_loc);
            case 0://中间节点，删除中间节点及其下级节点
                int[] sub_tree_nodes = all_children(type_loc[1]);
                int sub_tree_num = sub_tree_nodes.length;
                int[][] all_del_node_tp_loc = new int[sub_tree_num + 1][2];
                all_del_node_tp_loc[0][0] = type_loc[0];
                all_del_node_tp_loc[0][1] = type_loc[1];
                for (int i = 0; i < sub_tree_num; i++) {
                    int[] temp = node_type_loc(sub_tree_nodes[i]);
                    all_del_node_tp_loc[i + 1][0] = temp[0];
                    all_del_node_tp_loc[i + 1][1] = temp[1];
                }
                //分别对中间节点和叶子节点数组遍历，删除all_del_node_tp_loc中的节点
                for (int i = 0; i < all_del_node_tp_loc.length; i++) {
                    del_single_node(all_del_node_tp_loc[i]);
                }
        }
    }

    public void del_single_node(int[] tp_lc) {//（作为delete_node子函数，不单独使用）删除某节点
        switch (tp_lc[0]) {
            case 0://如果要删除的单点是中间节点，则在中间节点数组中操作
                node[] temp_mid_nodes = new node[mid_nodes.length - 1];
                if (tp_lc[1] != 0) {
                    System.arraycopy(mid_nodes, 0, temp_mid_nodes, 0, tp_lc[1]);
                    System.arraycopy(mid_nodes, tp_lc[1] + 1, temp_mid_nodes, tp_lc[1] + 1, mid_nodes.length - tp_lc[1] - 1);
                } else {
                    System.arraycopy(mid_nodes, 1, temp_mid_nodes, 0, mid_nodes.length - 1);
                }
                mid_nodes = temp_mid_nodes;
            case 1://如果要删除的单点是叶子节点，则在叶子节点数组中操作
                leafnode[] temp_leaf_nodes = new leafnode[leaf_nodes.length - 1];
                if (tp_lc[1] != 0) {
                    System.arraycopy(leaf_nodes, 0, temp_leaf_nodes, 0, tp_lc[1]);
                    System.arraycopy(leaf_nodes, tp_lc[1] + 1, temp_leaf_nodes, tp_lc[1] + 1, leaf_nodes.length - tp_lc[1] - 1);
                } else {
                    System.arraycopy(leaf_nodes, 1, temp_leaf_nodes, 0, leaf_nodes.length - 1);
                }
                leaf_nodes = temp_leaf_nodes;
        }
    }

    //查找节点类型、位置
    public int[] node_type_loc(int ind) {//搜索节点ind类型、位置
        int flag = -1;//-1表示不存在，0表示为分支节点，1表示为叶子节点
        int array_loc = mid_loc(ind);//首先在中间节点中搜索
        if (array_loc > -1) {//如果在中间节点中搜索到其索引
            flag = 0;//类型标志置为0表示其为分支节点
        } else {
            array_loc = leaf_loc(ind);//否则在叶子节点中搜索
            if (array_loc > -1) {//如果在叶子节点中找到其索引
                flag = 1;//类型标志置为1表示其为叶子节点
            }
            //否则意味着其数组中位置索引值为-1，保留并输出
        }

        int[] tl = new int[2];
        tl[0] = flag;//存入ind节点性质
        tl[1] = array_loc;//存入ind节点在对应数组中的位置
        return tl;
    }

    public int mid_loc(int ind) {//搜索节点ind在中间节点数组中的索引
        int array_loc = -1;
        if (mid_nodes != null) {//搜索中间节点
            for (int i = 0; i < mid_nodes.length; i++) {
                if (ind == mid_nodes[i].get_index()) {
                    array_loc = i;
                    break;
                }
            }
        }
        return array_loc;
    }

    public int leaf_loc(int ind) {//搜索节点ind在叶子节点数组中的索引
        int array_loc = -1;
        if (leaf_nodes != null) {//搜索叶子节点
            for (int i = 0; i < leaf_nodes.length; i++) {
                if (ind == leaf_nodes[i].get_index()) {
                    array_loc = i;
                    break;
                }
            }
        }
        return array_loc;
    }

    //节点及其父节点编号是否正确验证（增加、修改节点的先决条件）
    public boolean add_check(int ind, int fa) {//增加节点先决条件
        int[] exist = node_type_loc(ind);//检查节点是否存在
        boolean flag = false;
        if ((exist[0] == -1) && struct_check_fa(fa)) {//如果点编号不存在，且存在父节点与其对应
            flag = true;//则符合创建节点的条件
        }
        return flag;
    }

    public boolean edit_check(int ind, int fa) {//编辑节点先决条件
        int[] exist = node_type_loc(ind);//检查节点是否存在
        boolean flag = false;
        if ((exist[0] != -1) && struct_check_fa(fa)) {//如果点编号存在，且存在父节点与其对应
            flag = true;//则符合创建节点的条件
        }
        return flag;
    }

    public boolean struct_check_fa(int fa) {//搜索中间节点以检查节点建立过程中父节点编号是否对应正确
        boolean flag = false;
        if (mid_nodes != null) {//若已存在树
            for (int i = 0; i < mid_nodes.length; i++) {
                if (fa == mid_nodes[i].get_index()) {
                    flag = true;
                    break;
                }
            }
        } else if (fa == -1) {//否则判断是否为根节点父节点正确建立方式（即-1）
            flag = true;
        }
        return flag;
    }

    //返回树结构（节点、父节点、权重）
    public int[][] get_structure() {//按顺序返回树结构中所有节点的编号、对应父节点编号、权重
        int[][] a_ind = new int[mid_nodes.length + leaf_nodes.length][3];
        for (int i = 0; i < mid_nodes.length; i++) {
            a_ind[i][0] = mid_nodes[i].get_index();
            a_ind[i][1] = mid_nodes[i].get_father();
            a_ind[i][2] = mid_nodes[i].get_weight();
        }
        for (int j = 0; j < leaf_nodes.length; j++) {
            a_ind[mid_nodes.length + j][0] = leaf_nodes[j].get_index();
            a_ind[mid_nodes.length + j][1] = leaf_nodes[j].get_father();
            a_ind[mid_nodes.length + j][2] = leaf_nodes[j].get_weight();
        }
        return a_ind;
    }

    //找节点父节点
    public int find_father(int ind) {//返回父节点
        int f_ind = -2;//如果没有ind节点则默认返回-2表示节点不存在（返回-1表示根节点）
        boolean exist = false;//默认不存在父节点
        if (mid_nodes.length > 0) {
            for (int i = 0; i < mid_nodes.length; i++) {
                if (ind == mid_nodes[i].get_index()) {
                    f_ind = mid_nodes[i].get_father();
                    exist = true;//存在父节点并找到的标志，则无需在叶子节点中继续寻找
                    break;
                }
            }

            if ((leaf_nodes.length > 0) && (!exist)) {//中间节点中不存在，则开始在叶子节点中寻找
                for (int i = 0; i < leaf_nodes.length; i++) {
                    if (ind == leaf_nodes[i].get_index()) {
                        f_ind = leaf_nodes[i].get_father();
                        exist = true;
                        break;
                    }
                }
            }
        }
        return f_ind;
    }

    //找节点子节点
    public int[] find_children(int ind) {//返回子节点
        int[] c_ind = {-1};//返回-1表示未在树结构中找到相应节点或节点属于叶子节点（即ind不存在子节点）
        boolean end_mid = true;//假定ind节点为叶子节点的父节点
        if (mid_nodes.length > 0) {//寻找是否为中间节点的父节点
            for (int i = 0; i < mid_nodes.length; i++) {
                if (ind == mid_nodes[i].get_father()) {
                    int new_c_ind = mid_nodes[i].get_index();
                    if (c_ind[0] == -1) {
                        c_ind[0] = new_c_ind;
                    } else {
                        int[] temp_c_ind = new int[c_ind.length + 1];
                        System.arraycopy(c_ind, 0, temp_c_ind, 0, c_ind.length);
                        temp_c_ind[c_ind.length] = new_c_ind;
                        c_ind = temp_c_ind;
                    }
                    end_mid = false;//找到后证明ind为中间节点的父节点（不与叶子节点直接接触）
                }
            }

            if ((leaf_nodes.length > 0) && (end_mid)) {//否则假设为叶子节点的父节点
                for (int i = 0; i < leaf_nodes.length; i++) {
                    if (ind == leaf_nodes[i].get_father()) {
                        int new_c_ind = leaf_nodes[i].get_index();
                        if (c_ind[0] == -1) {
                            c_ind[0] = new_c_ind;
                        } else {
                            int[] temp_c_ind = new int[c_ind.length + 1];
                            System.arraycopy(c_ind, 0, temp_c_ind, 0, c_ind.length);
                            temp_c_ind[c_ind.length] = new_c_ind;
                            c_ind = temp_c_ind;
                        }
                    }
                }
            }
        }

        return c_ind;
    }

    //节点下子树包含节点的编号
    public int[] all_children(int ind) {//找节点下所有子节点、孙节点、……、叶子节点编号
        int[] ch_ind = find_children(ind);
        if (ch_ind[0] != -1) {//如果存在子节点
            for (int i = 0; i < ch_ind.length; i++) {
                int[] sub_ch_ind = all_children(ch_ind[i]);//按顺序寻找孙节点
                if (sub_ch_ind[0] != -1) {//如果孙节点非叶子节点，则添加孙节点编号到原子节点编号中
                    int[] temp_ch_ind = new int[ch_ind.length + sub_ch_ind.length];
                    System.arraycopy(ch_ind, 0, temp_ch_ind, 0, ch_ind.length);
                    System.arraycopy(sub_ch_ind, 0, temp_ch_ind, ch_ind.length, sub_ch_ind.length);
                    ch_ind = temp_ch_ind;
                }
            }
        }
        return ch_ind;
    }

    //修改节点内容
    public void edit_node(int ind, String nam, int fa) {//修改中间节点
        if (edit_check(ind, fa)) {
            int loc = mid_loc(ind);
            node temp_node = new node(ind, nam, fa);
            mid_nodes[loc] = temp_node;
        } else {
            System.out.print("Wrong nodes' relation! Fail to edit node!");
        }
    }

    public void edit_node(int ind, String nam, int fa, int w, String[] st_des, Map<Integer, String[][]> p_ind) {//修改在线叶子节点
        if (edit_check(ind, fa)) {
            int loc = mid_loc(ind);
            leafnode temp_leafnode = new leafnode(ind, nam, fa, w, st_des, p_ind);
            leaf_nodes[loc] = temp_leafnode;
        } else {
            System.out.print("Wrong nodes' relation! Fail to edit node!");
        }
    }

    public void edit_node(int ind, String nam, int fa, int w, String[] st_des) {//修改离线叶子节点
        if (add_check(ind, fa)) {
            int loc = mid_loc(ind);
            leafnode temp_leafnode = new leafnode(ind, nam, fa, w, st_des);
            leaf_nodes[loc] = temp_leafnode;
        } else {
            System.out.print("Wrong nodes' relation! Fail to edit node!");
        }
    }

    //修改节点关系
    public void edit_relation(int old_ind, int new_ind) {

    }

    //计算节点的值
    /*public static tree cal(tree tr, Date[] time, int unit, String type) throws Exception {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        return cal(tr, time, unit, type, database_url, user, psw);
    }

    public static tree cal(tree tr, Date[] time, int unit, String type, String database_url, String user, String psw) throws Exception {//根据叶子节点的值更新中间节点
        tree temp_tr = cal_leafnodes(tr, time, unit, type, database_url, user, psw);
        for (int i = 0; i < temp_tr.get_leaf_nodes().length; i++) {//采用遍历的方法更新树中中间节点的值
            mid_nodes_updater(temp_tr, temp_tr.get_leaf_nodes()[i].get_father(), temp_tr.get_leaf_nodes()[i].get_value());
        }
        return temp_tr;
    }*/

    /*public static tree cal_leafnodes(tree tr, Date[] time, int unit, String type, String database_url, String user, String psw) throws Exception {//计算叶子节点分值
        tree temp_tree = tr;
        temp_tree.init_val();

        leafnode[] temp_LN = temp_tree.get_leaf_nodes();

        //计算时段内的稳态时间
        Date[][] turbine_steady = steadyTime(time, unit, work_condition_turbine);
        Date[][] pump_steady = steadyTime(time, unit, work_condition_pump);
        Map<String, Date[][]> time_gap = new HashMap<>();
        time_gap.put("turbine", turbine_steady);
        time_gap.put("pump", pump_steady);
        time_gap.put("total", new Date[][]{time});

        //计算阈值
        Map<String, Map<Integer, String[][]>> all_th = threshold_reader(tr);

        //查询时段内的在线测点数值
        Map<Integer, Map<String, Map<Integer, float[]>>> all_query_val = id_min_max(time_gap, tr);

        //查询时段内离线点的分值
        resOffline ROL = offline_res_reader(tr, type, unit, database_url, user, psw);

        for (int i = 0; i < temp_LN.length; i++) {
            leafnode current_LN = temp_LN[i];
            if (current_LN.get_state() == leafnode.state_online_mark) {
                Map<String, Map<Integer, float[]>> node_val_set = all_query_val.get(current_LN.get_index());
                float leafnode_value=cal_online_single(current_LN, unit, node_val_set, all_th);
                current_LN.write_value(leafnode_value);//写入计算所得值
                System.out.println("online leafnode "+current_LN.get_name()+" value = "+leafnode_value);
            } else if (current_LN.get_state() == leafnode.state_offline_mark) {
                float leafnode_value=ROL.getRes().get(current_LN.get_index());
                current_LN.write_value(leafnode_value);//写入离线查表所得值
                System.out.println("offline leafnode "+current_LN.get_name()+" value = "+leafnode_value);
            }
            temp_LN[i] = current_LN;
        }

        return temp_tree;
    }*/

    public static class resOffline {
        private long time;
        private Map<Integer, Float> res;

        public void setTime(long tm) {
            time = tm;
        }

        public void setRes(Map<Integer, Float> r) {
            res = r;
        }

        public long getTime() {
            return time;
        }

        public Map<Integer, Float> getRes() {
            return res;
        }
    }

    public static class res_all{
        private long[] time;//分别存储在线评价时间和离线评价时间
        private tree tr;

        public void setTime(long[] tm){
            if (tm.length==2) {//保证为按格式存入在线、离线评价时间的tm
                long[] ttm = new long[]{tm[0], tm[1]};
                time = ttm;
            }
        }

        public void setTree(tree t){
            tr=t;
        }

        public long[] getTime() {
            return time;
        }

        public tree getTree(){
            return tr;
        }
    }

    public static void offline_res_builder(String flie_addr, String type) {
        final String DB_URL = DB_URL_res;
        final String USER = USR;
        final String PASS = PSW;
        offline_res_builder(flie_addr, type, DB_URL, USER, PSW);
    }

    public static void offline_res_builder(String file_addr, String type, String database_url, String user, String psw){
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL=database_url;
        final String USER=user;
        final String PASS=psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行模型最新版本搜索
             */

            String tb_name_guide = "eva_model_guide";
            String conditions = "type= '" + type +"'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions +" ORDER BY time DESC LIMIT 1";//查询最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time")*1000,"yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新模型存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            进行离线评价结果读取
             */
            List offN=readExcel(file_addr);

            /*
            进行离线评价结果存储
            */
            //首先
            String tb_name_offline="eva_res_"+type+"_"+version+"_leaf_offline";


            //建表并依次存入
            for (int i=0;i<offN.size();i++){
                List row_content=(List) offN.get(i);
                if (i==0){//首行为表头行，建表
                    String sql_offline_build_table="CREATE TABLE IF NOT EXISTS "+tb_name_offline+" ( time INT(11) NOT NULL, unit TINYINT(4) NOT NULL";
                    for (int j=0;j<row_content.size()-2;j++){
                        sql_offline_build_table=sql_offline_build_table+", v"+j+" FLOAT NOT NULL";
                    }
                    sql_offline_build_table=sql_offline_build_table+", PRIMARY KEY (time, unit))";
                    stmt.execute(sql_offline_build_table);
                    System.out.println("<offline result> table "+tb_name_offline+" has been created!");
                }
                else {//非首行的为数据行，插入数据
                    String sql_offline_insert = "INSERT INTO " + tb_name_offline + " VALUES (";
                    for (int j = 0; j < row_content.size(); j++) {
                        if (j < row_content.size() - 1) {
                            sql_offline_insert = sql_offline_insert + row_content.get(j) + ",";
                        } else {
                            sql_offline_insert = sql_offline_insert + row_content.get(j) + ");";
                        }
                    }
                    stmt.execute(sql_offline_insert);
                    System.out.println("<offline result> " + i + " record(s) has/have been added!");
                }
            }

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
    }

    public static resOffline offline_res_reader(tree tr, String type, int unit, String database_url, String user, String psw){
        int[] offline_node_index=get_offline_leaf_node_index(tr);
        resOffline ROL=new resOffline();

        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL=database_url;
        final String USER=user;
        final String PASS=psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行模型最新版本搜索
             */

            String tb_name_guide = "eva_model_guide";
            String conditions = "type= '" + type +"'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions +" ORDER BY time DESC LIMIT 1";//查询最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time")*1000,"yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新模型存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            进行离线评价结果读取
             */
            String tb_name_offline="eva_res_"+type+"_"+version+"_leaf_offline";
            String sql_offline="SELECT * FROM "+tb_name_offline+" WHERE unit="+unit+" ORDER BY time DESC LIMIT 1";
            rs=stmt.executeQuery(sql_offline);

            //取最后一条记录
            rs.last();
            Map<Integer, Float> ROL_res=new HashMap<>();
            for (int i=0;i<offline_node_index.length;i++){
                ROL_res.put(offline_node_index[i],rs.getFloat("v"+i));
            }
            ROL.setTime(rs.getLong("time"));
            ROL.setRes(ROL_res);

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        return ROL;
    }

    public static void offline_res_saver(resOffline res_offline, String type, int unit){//保存离线结果
        final String DB_URL=DB_URL_res;
        final String USER=USR;
        final String PASS=PSW;
        offline_res_saver(res_offline, type, unit, DB_URL, USER, PASS);
    }

    public static void offline_res_saver(resOffline res_offline, String type, int unit,
                                         String database_url, String user, String psw){
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL=database_url;
        final String USER=user;
        final String PASS=psw;
        Connection conn = null;
        Statement stmt = null;

        tree tr=tree.tree_reader(type);//读取最新的type类评价树
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行模型最新版本搜索
             */
            String tb_name_guide = "eva_model_guide";
            String conditions = "type= '" + type +"'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions +" ORDER BY time DESC LIMIT 1";//查询最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time")*1000,"yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新模型存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            进行离线结果存储
            */
            //首先建表
            String offline_res_tb_name="eva_res_"+type+"_"+version+"_leaf_offline";
            String sql_offline_res_tb_build=" CREATE TABLE IF NOT EXIST "+offline_res_tb_name+
                    " ( time INT(11) NOT NULL, unit TINYINT(4) NOT NULL";

            Map<Integer, Float> offline_value_set=res_offline.getRes();
            int offline_number=offline_value_set.size();
            for (int j=0;j<offline_number;j++){
                sql_offline_res_tb_build=sql_offline_res_tb_build+", v"+(j+1)+" FLOAT NOT NULL";
            }
            sql_offline_res_tb_build=sql_offline_res_tb_build+", PRIMARY KEY (time, unit));";
            stmt.execute(sql_offline_res_tb_build);

            //其次插入数据
            long offline_res_time=res_offline.getTime();
            String sql_offline_insert=" INSERT INTO "+offline_res_tb_name+" VALUES ("+offline_res_time+", "+unit;
            leafnode[] LN=tr.get_leaf_nodes();
            for (int j=0;j<LN.length;j++) {
                if (LN[j].get_state()==leafnode.state_offline_mark) {
                    Float value=offline_value_set.get(LN[j].get_index());
                    if (value!=null) {
                        sql_offline_insert = sql_offline_insert + ", " +value;
                    }
                    else{//未找到点值
                        throw new Exception("离线结果点编号与评价数离线点编号不对应！数据写入失败！");
                    }
                }
            }
            sql_offline_insert=sql_offline_insert+");";
            stmt.execute(sql_offline_insert);

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
    }

    public static List<res_all> res_reader(String type, int unit) throws ParseException {//取最新评价结果
        Date last_time=timeUtil.longToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        Date[] time_gap=new Date[]{last_time,last_time};
        return res_reader(type,unit,time_gap);
    }

    public static List<res_all> res_reader(String type, int unit, Date[] time_gap){//返回time_gap中的所有评价结果（历史记录查询）
        final String DB_URL=DB_URL_res;
        final String USER=USR;
        final String PASS=PSW;
        return res_reader( type, unit, time_gap, DB_URL, USER, PASS);
    }

    public static List<res_all> res_reader(String type, int unit, Date[] time_gap,
                                           String database_url, String user, String psw){//返回time_gap中的所有评价结果（历史记录查询）
        List<res_all> res=new ArrayList<>();
        long[] time_gap_long=new long[]{timeUtil.dateToLong(time_gap[0])/1000,
                timeUtil.dateToLong(time_gap[1])/1000};

        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL=database_url;
        final String USER=user;
        final String PASS=psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行模型最新版本搜索
             */
            String tb_name_guide = "eva_model_guide";
            String conditions_1 = "type= '" + type +"' AND time<="+time_gap_long[1]+" AND time>="+time_gap_long[0];
            String sql_1="SELECT * FROM " + tb_name_guide + " WHERE " + conditions_1 +" ORDER BY time DESC";//在时段内的记录
            String conditions_2 = "type= '" + type +"' AND time<"+time_gap_long[0];
            String sql_2 = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions_2 +" ORDER BY time DESC LIMIT 1";//在时段前的最近记录
            String sql=sql_1+" UNION "+sql_2;
            ResultSet rs = stmt.executeQuery(sql);

            // 读取查询结果
            List<Integer> version_set=new ArrayList<>();
            while (rs.next()) {
                String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
                int version = rs.getInt("version");//最新版本号
                version_set.add(version);

                // 输出数据
                System.out.print("查询模型存储时间: " + tm);
                System.out.print(", 版本号: " + version);
                System.out.print("\n");
            }

            if (version_set.size()==0){
                throw new Exception("时段内无评价模型被使用！");
            }

            //依据涉及到的模型对结果表进行遍历
            for (int j=0;j<version_set.size();j++){
                int current_version=version_set.get(j);//当前查询的模型版本号
                //读取树
                String mid_model_tb_name="eva_model_"+type+"_"+current_version+"_mid";
                String leaf_model_tb_name="eva_model_"+type+"_"+current_version+"_leaf";
                String sql_mid_model="SELECT * FROM "+mid_model_tb_name;
                String sql_leaf_model="SELECT * FROM "+leaf_model_tb_name;
                ResultSet rs_mid_model=stmt.executeQuery(sql_mid_model);
                ResultSet rs_leaf_model=stmt.executeQuery(sql_leaf_model);

                //读取结果
                String mid_res_tb_name="eva_res_"+type+"_"+current_version+"_mid";
                String online_res_tb_name="eva_res_"+type+"_"+current_version+"_leaf_online";
                String offline_res_tb_name="eva_res_"+type+"_"+current_version+"_leaf_offline";

                //首先对mid中间节点结果进行查询，以便获得该次评价的在线、离线评价时间
                String sql_mid_res=" SELECT * FROM "+mid_res_tb_name+" WHERE unit="+unit+
                        " AND time_online>="+time_gap_long[0]+" AND time_online<="+time_gap_long[1];
                ResultSet rs_mid_res=stmt.executeQuery(sql_mid_res);//取时段内的评价记录

                ResultSet temp_rs=rs_mid_res;
                if (!temp_rs.next()){
                    temp_rs.close();
                    throw new Exception("时段内无评价记录！");
                }
                temp_rs.close();

                //读取结果
                while(rs_mid_res.next()){
                    long[] time_eva_i=new long[]{rs_mid_res.getLong("time_online"),
                            rs_mid_res.getLong("time_offlone")};//该次评价的在线、离线评价时间

                    tree tr_i=tree.tree_builder_for_res_reader(rs_mid_model, rs_leaf_model);//根据查询结果建树

                    String sql_online_res=" SELECT * FROM "+online_res_tb_name+" WHERE unit="+unit+
                            " AND time="+time_eva_i[0];
                    String sql_offline_res=" SELECT * FROM "+offline_res_tb_name+" WHERE unit="+unit+
                            " AND time="+time_eva_i[1];
                    ResultSet rs_online_res=stmt.executeQuery(sql_online_res);
                    ResultSet rs_offline_res=stmt.executeQuery(sql_offline_res);

                    //分别存入相关节点值至树中
                    //1. 存中间节点
                    int i_mid=0;
                    while (rs_mid_res.next()){
                        tr_i.get_mid_node(i_mid).write_value(rs_mid_res.getFloat(i_mid+3));
                    }
                    //2. 存叶子节点
                    int i_online=0;
                    int i_offline=0;
                    int leaf_number=tr_i.get_leaf_nodes().length;
                    for(int i_leaf=0;i_leaf<leaf_number;i_leaf++){
                        if (tr_i.get_leaf_node(i_leaf).get_state()==leafnode.state_online_mark){//在线叶子节点
                            rs_online_res.next();
                            tr_i.get_leaf_node(i_leaf).write_value(rs_online_res.getFloat(i_online+2));
                            i_online++;
                        }
                        else{//离线叶子节点
                            rs_offline_res.next();
                            tr_i.get_leaf_node(i_leaf).write_value(rs_offline_res.getFloat(i_offline+2));
                            i_offline++;
                        }
                    }

                    //存入函数返回结果List中
                    res_all rs_i=new res_all();
                    rs_i.setTime(time_eva_i);
                    rs_i.setTree(tr_i);
                    res.add(rs_i);

                    //关闭ResultSet
                    rs_online_res.close();
                    rs_offline_res.close();
                }
                //关闭ResultSet
                rs_mid_res.close();
                rs_mid_model.close();
                rs_leaf_model.close();
            }

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
        return res;
    }

    public static tree tree_builder_for_res_reader(ResultSet rs_mid, ResultSet rs_leaf) throws SQLException {//根据查询到的中间节点信息和叶子节点信息建树
        //为res_reader的子函数
        tree temp_tree=new tree();

        // 循环存入所有中间节点信息
        int index, father;
        String name;
        while (rs_mid.next()) {
            index = rs_mid.getInt("id");
            father = rs_mid.getInt("father");
            name = rs_mid.getString("name");

            temp_tree.add_node(index, name, father);
        }
        rs_mid.close();

        // 循环存入所有叶子节点信息
        int weight;
        boolean state;
        String[] stan_description;
        Map<Integer, String[][]> points;
        while (rs_leaf.next()) {
            index = rs_leaf.getInt("id");
            father = rs_leaf.getInt("father");
            name = rs_leaf.getString("name");

            weight = rs_leaf.getInt("weight");
            state = leafnode.state_offline_mark;
            if (rs_leaf.getInt("state") == mysql_online_node_mark) {
                state = leafnode.state_online_mark;
            }
            String ts = rs_leaf.getString("stan_description");
            String tp = rs_leaf.getString("points");
            stan_description = operation_stan(ts);

            if (state == leafnode.state_offline_mark) {//离线点
                temp_tree.add_node(index, name, father, weight, stan_description);
            } else {//在线点
                points = operation_points(tp);
                temp_tree.add_node(index, name, father, weight, stan_description, points);
            }
        }
        rs_leaf.close();

        temp_tree.init_val();
        node[] tt_mid_nodes = temp_tree.get_mid_nodes();
        System.out.println("Tree " + tt_mid_nodes[0].get_name() + " has been successfully built!");

        return temp_tree;
    }

    public static void res_saver(tree tr, String type, int unit, Date[] time_gap){
        final String DB_URL=DB_URL_res;
        final String USER=USR;
        final String PASS=PSW;
        res_saver(tr, type, unit, time_gap, DB_URL, USER, PASS);
    }

    public static void res_saver(tree tr, String type, int unit, Date[] time_gap, String database_url, String user, String psw){
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL=database_url;
        final String USER=user;
        final String PASS=psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行模型最新版本搜索
             */

            String tb_name_guide = "eva_model_guide";
            String conditions = "type= '" + type +"'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions +" ORDER BY time DESC LIMIT 1";//查询最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time")*1000,"yyyy-MM-dd HH:mm:ss");
            int version = rs.getInt("version");//最新版本号

            // 输出数据
            System.out.print("最新模型存储时间: " + tm);
            System.out.print(", 最新版本号: " + version);
            System.out.print("\n");

            /*
            进行评价结果存储
             */
            String tb_name_online="eva_res_"+type+"_"+version+"_leaf_online";
            String tb_name_mid="eva_res_"+type+"_"+version+"_mid";

            //首先建表（若不存在）
            //1. 在线叶子节点结果存储表
            String sql_online_res_table_build=" CREATE TABLE IF NOT EXISTS "+tb_name_online+" ( time INT(11) NOT NULL, unit TINYINT(4) NOT NULL";
            leafnode[] LN=tr.get_leaf_nodes();
            List<leafnode> online_LN=new ArrayList<>();
            int online_node_num=0;
            for (int j=0;j<LN.length;j++){
                if (LN[j].get_state()==leafnode.state_online_mark){
                    online_LN.add(LN[j]);
                    online_node_num++;
                    sql_online_res_table_build=sql_online_res_table_build+", v"+online_node_num+" FLOAT NOT NULL";
                }
            }
            sql_online_res_table_build=sql_online_res_table_build+", PRIMARY KEY (time, unit));";
            stmt.execute(sql_online_res_table_build);

            //2. 中间节点结果存储表
            String sql_mid_res_table_build=" CREATE TABLE IF NOT EXISTS "+tb_name_mid+
                    " ( time_online INT(11) NOT NULL, time_offline INT(11) NOT NULL, unit TINYINT(4) NOT NULL";
            node[] MN=tr.get_mid_nodes();
            for (int j=0;j<MN.length;j++){
                sql_mid_res_table_build=sql_mid_res_table_build+", v"+(j+1)+" FLOAT NOT NULL";
            }
            sql_mid_res_table_build=sql_mid_res_table_build+", PRIMARY KEY (time_online, unit));";
            stmt.execute(sql_mid_res_table_build);

            //之后插入数据
            //1. 插入在线点数据
            long online_eva_time=timeUtil.dateToLong(time_gap[1])/1000;
            String sql_online_res_insert = "INSERT INTO " + tb_name_online + " VALUES ("+online_eva_time+", "+unit;
            for (int j=0; j<online_LN.size(); j++) {
                sql_online_res_insert=sql_online_res_insert+", "+online_LN.get(j).get_value();
            }
            sql_online_res_insert=sql_online_res_insert+")";
            stmt.execute(sql_online_res_insert);
            System.out.println("Online leafnodes res have been saved!");

            //2. 插入中间点数据
            String sql_offline_res_read=" SELECT time FROM eva_res_"+type+"_"+version+"_leaf_offline WHERE unit="+unit+" ORDER BY time DESC LIMIT 1";
            rs=stmt.executeQuery(sql_offline_res_read);
            rs.next();
            long offline_eva_time=rs.getLong("time");
            String sql_mid_res_insert="INSERT INTO "+tb_name_mid+" VALUES ("+online_eva_time+", "+offline_eva_time+", "+unit;
            for (int j=0;j<MN.length;j++){
                sql_mid_res_insert=sql_mid_res_insert+", "+MN[j].get_value();
            }
            sql_mid_res_insert=sql_mid_res_insert+");";
            stmt.execute(sql_mid_res_insert);
            System.out.println("Midnodes res have been saved!");

            // 完成后关闭
            rs.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
    }

    /*public tree cal_online( tree tr, Date[] time, int unit) throws Exception {//仅计算在线点的分值
        tree temp_tree=tr;
        temp_tree.init_val();

        leafnode[] temp_LN=temp_tree.get_leaf_nodes();

        //计算时段内的稳态时间
        Date[][] turbine_steady=steadyTime(time, unit, work_condition_turbine);
        Date[][] pump_steady=steadyTime(time, unit, work_condition_pump);
        Map<String, Date[][]> time_gap=new HashMap<>();
        time_gap.put("turbine", turbine_steady);
        time_gap.put("pump", pump_steady);
        time_gap.put("total", new Date[][]{time});

        //计算阈值
        Map<String, Map<Integer, String[][]>> all_th=threshold_reader(tr);

        //查询时段内的在线测点数值
        Map<Integer, Map<String, Map<Integer, float[]>>> all_query_val=id_min_max(time_gap, tr);

        for (int i=0;i<temp_LN.length;i++){
            leafnode current_LN=temp_LN[i];
            if (current_LN.get_state()==leafnode.state_online_mark){
                Map<String, Map<Integer,float[]>> node_val_set=all_query_val.get(current_LN.get_index());
                current_LN.write_value(cal_online_single(current_LN, unit, node_val_set,all_th));//写入计算所得值
            }
            temp_LN[i]=current_LN;
        }

        return temp_tree;
    }

    public static float cal_online_single(leafnode LN, int unit, Map<String, Map<Integer,float[]>> val, Map<String, Map<Integer, String[][]>> all_th) throws Exception {
        *//*计算单点的值*//*
        float res=0;
        if (LN.get_state()==leafnode.state_online_mark){//在线节点
            Map<Integer, int[][]> LN_points_set=LN.get_point_index();//得到节点的测点集
            int[][] LN_points=LN_points_set.get(unit);
            if ((val.get("bool").size()+Math.max(val.get("float_turbine").size(),val.get("float_pump").size()))==LN_points.length) {
                for (int i = 0; i < LN_points.length; i++) {//对第i个测点
                    if (LN_points[i][0] != -1) {//有测点
                        String[][] threshold = null;
                        if (LN_points[i][1] == leafnode.float_mark) {//单精量测点
                            threshold = all_th.get("float").get(LN_points[i][0]);
                            //水泵工况
                                res = Math.max(res,LN.get_weight()*th_cal(val.get("float_pump").get(LN_points[i][0])[0], threshold[0]));//输入最小值
                                res = Math.max(res,LN.get_weight()*th_cal(val.get("float_pump").get(LN_points[i][0])[1], threshold[0]));//输入最大值
                            //水轮机工况
                                res = Math.max(res,LN.get_weight()*th_cal(val.get("float_turbine").get(LN_points[i][0])[0], threshold[1]));//输入最小值
                                res = Math.max(res,LN.get_weight()*th_cal(val.get("float_turbine").get(LN_points[i][0])[1], threshold[1]));//输入最大值
                        }
                        else if (LN_points[i][1] == leafnode.bool_mark){//状态量测点
                            threshold=all_th.get("bool").get(LN_points[i][0]);
                            res = Math.max(res,LN.get_weight()*th_cal(val.get("bool").get(LN_points[i][0])[0], threshold[0]));//输入最大值
                        }
                    }
                }
            }
            else{
                throw new Exception("值个数与测点数不对应！");
            }
        }
        return res;
    }*/

    public static float th_cal(float val, String[] th) throws Exception {//直接输入阈值数组计算基本扣分值
        float res=0;
        String th_empty="-";
        if (th.length!=7){
            throw new Exception("定义阈值数目异常！");
        }
        else{//依据其所处位置分别打分
            List<Float[]> th_val=new ArrayList<>();
            for (int i=0;i<th.length;i++){//得到阈值的位置和值
                if (!th[i].equals("-")){
                    if(th[i].contains("%")){//如果存储的是百分数，进行转化
                        th[i]="0."+th[i].substring(0,th[i].indexOf("%"));
                    }
                    Float[] exist_th={Float.valueOf(i),Float.parseFloat(th[i])};
                    th_val.add(exist_th);
                }
            }

            int[] dir_level;
            boolean in_range=false;
            for (int j=0;j<th_val.size()-1;j++){
                if (val>=th_val.get(j)[1]&&val<=th_val.get(j+1)[1]){//val在某区间内
                    in_range=true;
                    int[] pos=new int[]{(int) Math.floor(th_val.get(j)[0]), (int) Math.floor(th_val.get(j+1)[0])};
                    dir_level=dir_level_cal(pos);
                    res=range_cal(val, new float[]{th_val.get(j)[1],th_val.get(j+1)[1]}, dir_level[0], dir_level[1]);
                }
            }

            if (!in_range){//如果val落在阈值外
                if (val<th_val.get(0)[1]){//小于
                    switch ((int) Math.floor(th_val.get(0)[0])){
                        case 0:
                            dir_level=new int[]{1,3};
                            res=range_cal(val, new float[]{th_val.get(0)[1],th_val.get(0)[1]+1},dir_level[0], dir_level[1]);
                            break;
                        case 1:
                            dir_level=new int[]{1,2};
                            res=range_cal(val, new float[]{th_val.get(0)[1],th_val.get(0)[1]+1},dir_level[0], dir_level[1]);
                            break;
                        case 2:
                            dir_level=new int[]{1,1};
                            res=range_cal(val, new float[]{th_val.get(0)[1],th_val.get(0)[1]+1},dir_level[0], dir_level[1]);
                            break;
                        case 3:
                            dir_level=new int[]{-1,1};
                            res=range_cal(val, new float[]{val,th_val.get(0)[1]},dir_level[0], dir_level[1]);
                            break;
                    }
                }
                else{//大于
                    int last_th_num=th_val.size()-1;
                    switch ((int) Math.floor(th_val.get(last_th_num)[0])){
                        case 6:
                            dir_level=new int[]{-1,3};
                            res=range_cal(val, new float[]{th_val.get(last_th_num)[1]-1,th_val.get(last_th_num)[1]},dir_level[0], dir_level[1]);
                            break;
                        case 5:
                            dir_level=new int[]{-1,2};
                            res=range_cal(val, new float[]{th_val.get(last_th_num)[1]-1,th_val.get(last_th_num)[1]},dir_level[0], dir_level[1]);
                            break;
                        case 4:
                            dir_level=new int[]{-1,1};
                            res=range_cal(val, new float[]{th_val.get(last_th_num)[1]-1,th_val.get(last_th_num)[1]},dir_level[0], dir_level[1]);
                            break;
                        case 3:
                            dir_level=new int[]{1,1};
                            res=range_cal(val, new float[]{th_val.get(last_th_num)[1],val},dir_level[0], dir_level[1]);
                            break;
                    }
                }
            }
        }
        return res;
    }

    public static int[] dir_level_cal(int[] pos){
        int dir=0;
        int level=0;
        if (pos[0]==0){
            dir=1;
            level=3;
        }
        else if (pos[1]==6){
            dir=-1;
            level=3;
        }
        else if (pos[0]==1){
            dir=1;
            level=2;
        }
        else if (pos[1]==5){
            dir=-1;
            level=2;
        }
        else if (pos[0]==2){
            dir=1;
            level=1;
        }
        else if (pos[1]==4){
            dir=-1;
            level=1;
        }

        return new int[]{dir,level};
    }

    public static float range_cal(float val, float[] range, int dir, int level){
        float res=0;//结果
        int[] point_range=new int[2];//扣分范围
        switch (level){//由level控制扣分等级
            case 1:
                point_range=new int[]{1,3};
                break;
            case 2:
                point_range=new int[]{4,6};
                break;
            case 3:
                point_range=new int[]{7,10};
                break;
        }

        if ((dir==-1)&&(val>=range[0])){//小值较好
            if (val-range[0]==0){
                res=0;
            }
            else {
                res = (val - range[0]) / (range[1] - range[0]);//先算出百分比
            }
        }
        if ((dir==1)&&(val<=range[1])){//大值较好
            if (range[1]-val==0){
                res=0;
            }
            else {
                res = (range[1] - val) / (range[1] - range[0]);//先算出百分比
            }
        }
        if (dir==0){//中值较好
            float mid=(range[0]+range[1])/2;
            if (val-mid==0) {
                res = 0;
            }
            else{
                res = Math.abs((val - mid) / (mid - range[0]));//先算出百分比
            }
        }

        if (res>1){
            res=1;
        }

        res=res*(point_range[1]-point_range[0])+point_range[0];//乘上范围后加上基本扣分值

        return res;
    }

    /*public static Map<Integer, Map<String, Map<Integer, float[]>>> id_min_avg_max( Map<String, Date[][]> time_gap, tree eva_tree) throws ParseException {//求某时段内所有在线叶子节点相关单精量测点的最小值、平均值和最大值
        *//*time_gap存储总时间和稳态时间（total/turbine/pump）三种条件下的查询时段，分别用于查询状态量和单精量*//*

        Map<String,Map<Integer,float[]>> res_node;//叶子节点相关测点值查询结果<类型（float/bool），测点数值集合(float_point_val/bool_point_val)>
        Map<Integer, Map<String, Map<Integer, float[]>>> res=new HashMap<>();//

        leafnode[] LN=eva_tree.get_leaf_nodes();
        for (int i=0;i<LN.length;i++){//对每个叶子节点进行遍历
            leafnode current_LN=LN[i];
            res_node=id_min_avg_max(time_gap, current_LN);
            res.put(current_LN.get_index(), res_node);
        }
        return res;
    }

    public static Map<String, Map<Integer, float[]>> id_min_avg_max( Map<String, Date[][]> time_gap, leafnode LN) throws ParseException {//计算某一在线叶子节点相关测点的特征值
        Map<String, Map<Integer, float[]>> res_node=new HashMap<>();
        Map<Integer, float[]> float_point_val_turbine=new HashMap<>();
        Map<Integer, float[]> float_point_val_pump=new HashMap<>();
        Map<Integer, float[]> bool_point_val=new HashMap<>();//<测点，值（单精量包含min、avg、max，状态量统计次数）>
        leafnode current_LN=LN;
        if (current_LN.get_state()==leafnode.state_online_mark) {//在线量
            Map<Integer, int[][]> current_points_allunit = current_LN.get_point_index();
            for (int i=0; i<current_points_allunit.size();i++) {//每个机组
                int[][] current_points=current_points_allunit.get(i+1);
                for (int j = 0; j < current_points.length; j++) {//对当前节点第j个测点值进行查询
                    if ((current_points[j][0] != -1) && (current_points[j][1] == leafnode.float_mark)) {//存在单精量测点
                        System.out.println("====叶子节点 " + current_LN.get_name() + " 单精量测点 " + current_points[j][0] + " 发电态查询===");
                        float[] sum = new float[0];
                        for (int jj = 0; jj < time_gap.get("turbine").length; jj++) {//对第jj个稳态时段的值进行查询处理
                            List<resFloat> res_float = searchFloat(time_gap.get("turbine")[jj], current_points[j][0]);
                            if (res_float != null) {//如果查询结果不为空，则存入sum数组中
                                float[] per_sum = new float[res_float.size()];
                                for (int jjj = 0; jjj < res_float.size(); jjj++) {
                                    per_sum[jjj] = res_float.get(jjj).getValue();
                                }

                                if (sum.length == 0) {
                                    sum = per_sum;
                                } else {
                                    float[] temp_sum = new float[sum.length + per_sum.length];
                                    System.arraycopy(sum, 0, temp_sum, 0, sum.length);
                                    System.arraycopy(per_sum, 0, temp_sum, sum.length, per_sum.length);
                                    sum = temp_sum;
                                }
                            }
                        }

                        if (sum.length > 0) {//写入了数据，进行计算后存储
                            float avg = mean(sum);
                            float max = maxx(sum);
                            float min = minn(sum);
                            float[] current_point_feature = new float[]{min, avg, max};
                            int CP_id = current_points[j][0];
                            float_point_val_turbine.put(CP_id, current_point_feature);
                        }

                        System.out.println("====叶子节点 " + current_LN.get_name() + " 单精量测点 " + current_points[j][0] + " 抽水态查询===");
                        sum = new float[0];
                        for (int jj = 0; jj < time_gap.get("pump").length; jj++) {//对第jj个稳态时段的值进行查询处理
                            List<resFloat> res_float = searchFloat(time_gap.get("pump")[jj], current_points[j][0]);
                            if (res_float != null) {//如果查询结果不为空，则存入sum数组中
                                float[] per_sum = new float[res_float.size()];
                                for (int jjj = 0; jjj < res_float.size(); jjj++) {
                                    per_sum[jjj] = res_float.get(jjj).getValue();
                                }

                                if (sum.length == 0) {
                                    sum = per_sum;
                                } else {
                                    float[] temp_sum = new float[sum.length + per_sum.length];
                                    System.arraycopy(sum, 0, temp_sum, 0, sum.length);
                                    System.arraycopy(per_sum, 0, temp_sum, sum.length, per_sum.length);
                                    sum = temp_sum;
                                }
                            }
                        }

                        if (sum.length > 0) {//写入了数据，进行计算后存储
                            float avg = mean(sum);
                            float max = maxx(sum);
                            float min = minn(sum);
                            float[] current_point_feature = new float[]{min, avg, max};
                            int CP_id = current_points[j][0];
                            float_point_val_pump.put(CP_id, current_point_feature);
                        }

                    } else if ((current_points[j][0] != -1) && (current_points[j][1] == leafnode.bool_mark)) {//bool型测点
                        System.out.println("====叶子节点 " + current_LN.get_name() + " 状态量测点 " + current_points[j][0] + " 查询===");
                        float sum = 0;
                        for (int jj = 0; jj < time_gap.get("total").length; jj++) {//对选定时段的值进行查询处理
                            List<resBool> res_bool = searchBool(time_gap.get("total")[jj], current_points[j][0]);
                            if (res_bool != null) {//如果查询结果不为空，则与sum相加
                                for (int jjj = 0; jjj < res_bool.size(); jjj++) {
                                    if (res_bool.get(jjj).getFlag() != 3) {//如果为插值则不加
                                        sum = sum + res_bool.get(jjj).getValue();
                                    }
                                }
                            }
                        }

                        float[] current_point_feature = new float[]{sum};
                        int CP_id = current_points[j][0];
                        bool_point_val.put(CP_id, current_point_feature);
                    }
                }
            }

            //保存单精量和状态量查询结果
            res_node.put("float_turbine", float_point_val_turbine);
            res_node.put("float_pump", float_point_val_pump);
            res_node.put("bool", bool_point_val);
        }
        return res_node;
    }

    public static Map<Integer, Map<String, Map<Integer, float[]>>> id_min_max( Map<String, Date[][]> time_gap, tree eva_tree) throws ParseException {//求某时段内所有在线叶子节点相关单精量测点的最小值和最大值、状态量测点的为1次数
        *//*time_gap存储总时间和稳态时间（total/turbine/pump）三种条件下的查询时段，分别用于查询状态量和单精量*//*

        Map<String,Map<Integer,float[]>> res_node;//叶子节点相关测点值查询结果<类型（float/bool），测点数值集合(float_point_val/bool_point_val)>
        Map<Integer, Map<String, Map<Integer, float[]>>> res=new HashMap<>();//

        leafnode[] LN=eva_tree.get_leaf_nodes();
        for (int i=0;i<LN.length;i++){//对每个叶子节点进行遍历
            leafnode current_LN=LN[i];
            if (current_LN.get_state()==leafnode.state_online_mark) {
                res_node = id_min_max(time_gap, current_LN);
                res.put(current_LN.get_index(), res_node);
            }
        }
        return res;
    }

    public static Map<String, Map<Integer, float[]>> id_min_max( Map<String, Date[][]> time_gap, leafnode LN) throws ParseException {//计算某一在线叶子节点相关测点的特征值
        Map<String, Map<Integer, float[]>> res_node=new HashMap<>();
        Map<Integer, float[]> float_point_val_turbine=new HashMap<>();
        Map<Integer, float[]> float_point_val_pump=new HashMap<>();
        Map<Integer, float[]> bool_point_val=new HashMap<>();//<测点，值（单精量包含min、avg、max，状态量统计次数）>
        leafnode current_LN=LN;
        if (current_LN.get_state()==leafnode.state_online_mark) {//在线量
            Map<Integer, int[][]> current_points_allunit = current_LN.get_point_index();
            for (int i=0; i<current_points_allunit.size();i++) {
                int[][] current_points=current_points_allunit.get(i+1);
                for (int j = 0; j < current_points.length; j++) {//对当前节点第j个测点值进行查询
                    if ((current_points[j][0] != -1) && (current_points[j][1] == leafnode.float_mark)) {//存在单精量测点
                        System.out.println("====叶子节点 " + current_LN.get_name() + " 单精量测点 " + current_points[j][0] + " 发电态查询===");
                        float[] sum = new float[0];
                        for (int jj = 0; jj < time_gap.get("turbine").length; jj++) {//对第jj个稳态时段的值进行查询处理
                            List<resFloat> res_float = searchFloat(time_gap.get("turbine")[jj], current_points[j][0]);
                            if (res_float != null) {//如果查询结果不为空，则存入sum数组中
                                float[] per_sum = new float[res_float.size()];
                                for (int jjj = 0; jjj < res_float.size(); jjj++) {
                                    per_sum[jjj] = res_float.get(jjj).getValue();
                                }

                                if (sum.length == 0) {
                                    sum = per_sum;
                                } else {
                                    float[] temp_sum = new float[sum.length + per_sum.length];
                                    System.arraycopy(sum, 0, temp_sum, 0, sum.length);
                                    System.arraycopy(per_sum, 0, temp_sum, sum.length, per_sum.length);
                                    sum = temp_sum;
                                }
                            }
                        }

                        if (sum.length > 0) {//写入了数据，进行计算后存储
                            //float avg = mean(sum);
                            float max = maxx(sum);
                            float min = minn(sum);
                            float[] current_point_feature = new float[]{min, max};
                            int CP_id = current_points[j][0];
                            float_point_val_turbine.put(CP_id, current_point_feature);
                        }

                        System.out.println("====叶子节点 " + current_LN.get_name() + " 单精量测点 " + current_points[j][0] + " 抽水态查询===");
                        sum = new float[0];
                        for (int jj = 0; jj < time_gap.get("pump").length; jj++) {//对第jj个稳态时段的值进行查询处理
                            List<resFloat> res_float = searchFloat(time_gap.get("pump")[jj], current_points[j][0]);
                            if (res_float != null) {//如果查询结果不为空，则存入sum数组中
                                float[] per_sum = new float[res_float.size()];
                                for (int jjj = 0; jjj < res_float.size(); jjj++) {
                                    per_sum[jjj] = res_float.get(jjj).getValue();
                                }

                                if (sum.length == 0) {
                                    sum = per_sum;
                                } else {
                                    float[] temp_sum = new float[sum.length + per_sum.length];
                                    System.arraycopy(sum, 0, temp_sum, 0, sum.length);
                                    System.arraycopy(per_sum, 0, temp_sum, sum.length, per_sum.length);
                                    sum = temp_sum;
                                }
                            }
                        }

                        if (sum.length > 0) {//写入了数据，进行计算后存储
                            //float avg = mean(sum);
                            float max = maxx(sum);
                            float min = minn(sum);
                            float[] current_point_feature = new float[]{min, max};
                            int CP_id = current_points[j][0];
                            float_point_val_pump.put(CP_id, current_point_feature);
                        }

                    } else if ((current_points[j][0] != -1) && (current_points[j][1] == leafnode.bool_mark)) {//bool型测点
                        System.out.println("====叶子节点 " + current_LN.get_name() + " 状态量测点 " + current_points[j][0] + " 查询===");
                        float sum = 0;
                        for (int jj = 0; jj < time_gap.get("total").length; jj++) {//对选定时段的值进行查询处理
                            List<resBool> res_bool = searchBool(time_gap.get("total")[jj], current_points[j][0]);
                            if (res_bool != null) {//如果查询结果不为空，则与sum相加
                                for (int jjj = 0; jjj < res_bool.size(); jjj++) {
                                    if (res_bool.get(jjj).getFlag() != 3) {//如果为插值则不加
                                        sum = sum + res_bool.get(jjj).getValue();
                                    }
                                }
                            }
                        }

                        float[] current_point_feature = new float[]{sum};
                        int CP_id = current_points[j][0];
                        bool_point_val.put(CP_id, current_point_feature);
                    }
                }
            }

            //保存单精量和状态量查询结果
            res_node.put("float_turbine", float_point_val_turbine);
            res_node.put("float_pump", float_point_val_pump);
            res_node.put("bool", bool_point_val);
        }
        return res_node;
    }*/

    //同数据库间的存取格式转换
    public static String[] operation_stan(String src_str){//按数据库中内容存入树叶子结点的评判标准中
        String[] res=null;
        if (!src_str.isEmpty()){
            List<String> res_list=new ArrayList<>();
            int[] cursor=new int[]{src_str.indexOf('{'),src_str.indexOf('}')};
            String per_stan=src_str.substring(cursor[0]+1,cursor[1]);
            res_list.add(per_stan);
            while(cursor[0]<src_str.lastIndexOf('{')){//按{}对内容进行划分存储
                cursor[0]= src_str.indexOf('{', cursor[1]);
                cursor[1]= src_str.indexOf('}', cursor[0]);
                per_stan=src_str.substring(cursor[0]+1,cursor[1]);
                res_list.add(per_stan);
            }

            int num=res_list.size();
            res=new String[num];
            for (int i=0;i<num;i++){
                res[i]=res_list.get(i);
            }
        }
        return res;
    }

    public static String operation_stan(String[] node_str){
        String res="";
        for (int i=0;i<node_str.length;i++){
            res=res+"{"+node_str[i]+"}";
        }
        return res;
    }

    public static Map<Integer, String[][]> operation_points(String src_str){//按数据库中内容存入树叶子结点的关联测点中
        Map<Integer, String[][]> map_res=new HashMap<>();
        String[][] res=null;
        if (!src_str.isEmpty()){
            String[] p_set=src_str.split("}");
            for (int j=0; j<p_set.length; j++) {//对每台机组的数据进行读取
                String unit_p=p_set[j].substring(p_set[j].indexOf("{")+1);
                List<String[]> res_list = new ArrayList<>();

                String[] PPS_set = unit_p.split("；");
                for (int jj=0;jj<PPS_set.length;jj++) {
                    String[] P=PPS_set[jj].split("，");
                    res_list.add(new String[]{P[0], P[1]});
                }

                int num = res_list.size();
                res = new String[num][];
                for (int i = 0; i < num; i++) {
                    res[i] = res_list.get(i);
                }

                map_res.put(j+1,res);
            }
        }
        return map_res;
    }

    public static String operation_points(Map<Integer, String[][]> node_points){//按照{}划分机组，按照point_id,type；的形式划分测点及类型
        String res="";
        for (int i=0;i<node_points.size();i++){
            res=res+"{";
            for (int j=0; j<node_points.get(i+1).length;j++) {
                    res = res + node_points.get(i + 1)[j][0] + "，" + node_points.get(i + 1)[j][1] + "；";
            }
            res=res+"}";
        }
        return res;
    }

    public static String[][] operation_threshold(String src_str){//完成由数据库中存储的String类型数据向String[][]型的转换
        String[][] res=null;
        if (!src_str.isEmpty()){
            List<String[]> res_list=new ArrayList<>();
            int[] cursor=new int[]{src_str.indexOf('{'),src_str.indexOf('}')};
            String per_th_str=src_str.substring(cursor[0]+1,cursor[1]);
            String[] PTS_set=per_th_str.split("；");
            /*float[]  per_th=new float[PTS_set.length];
            for (int i=0;i<PTS_set.length;i++){
                per_th[i]=Float.parseFloat(PTS_set[i]);
            }*/
            res_list.add(PTS_set);
            while(cursor[1]<src_str.lastIndexOf('}')){//按{}对内容进行划分存储
                cursor[0]= src_str.indexOf('{', cursor[1]);
                cursor[1]= src_str.indexOf('}', cursor[0]);
                per_th_str=src_str.substring(cursor[0]+1,cursor[1]);
                PTS_set=per_th_str.split("；");

                /*per_th=new float[PTS_set.length];
                for (int i=0;i<PTS_set.length;i++){
                    per_th[i]=Float.parseFloat(PTS_set[i]);
                }*/

                res_list.add(PTS_set);
                cursor[1]++;
            }

            int num=res_list.size();
            res=new String[num][];
            for (int i=0;i<num;i++){
                res[i]=res_list.get(i);
            }
        }
        return res;
    }

    public static String operation_threshold(String[][] points_th){
        String res="";
        for (int i=0;i<points_th.length;i++){
            String temp_str=points_th[i][0]+"";
            for (int ii=1;ii<points_th[i].length;ii++){
                temp_str=temp_str+"；"+points_th[i][ii];
            }
            res=res+"{"+temp_str+"}";
        }
        return res;
    }

    //解析事件描述字符串的函数
    public static timeDescriber time_descrip_decoder(String TD){
        timeDescriber res;
        String time_descrip = TD;
        String time_type = time_descrip;
        res=new timeDescriber(time_type);

        int[] time_unit_set;
        if (time_descrip.contains("[")){//有机组标志
            time_type = time_descrip.substring(0, time_descrip.indexOf("["));
            String[] time_unit_set_str = time_descrip.substring(time_descrip.indexOf("[") + 1,
                    time_descrip.indexOf("]")).split(",");
            time_unit_set = new int[time_unit_set_str.length];
            for (int tus_i = 0; tus_i < time_unit_set.length; tus_i++) {//依次存入机组号
                time_unit_set[tus_i] = Integer.parseInt(time_unit_set_str[tus_i]);
            }
            res=new timeDescriber(time_type,time_unit_set);
        }

        return res;
    }

    //计算叶子节点值时用到的系列函数
    public static float cal_func_L(Date[] time_gap, int point, String per_or_all,
                                   Map< String, Map<String, String[][]>> node_th,
                                   int[] unit, String time_type) throws Exception {

        List<long[]> lasting_time=search_func_L(time_gap, point, per_or_all, unit, time_type);//首先读取延续时段长

        //之后读取节点阈值并计算
        String unit_str="[";
        for (int i=0;i<unit.length;i++){
            unit_str=unit_str+unit[i];
            if (i<unit.length-1){
                unit_str=unit_str+",";
            }
        }
        unit_str=unit_str+"]";
        String point_desc=time_type;
        if (!time_type.equals("F")) {//如果不是全时段，需要添加机组号描述
            point_desc=point_desc+unit_str;
        }
        point_desc=point_desc+ "|L";

        if (per_or_all.equals("per")){//分次累计
            point_desc=point_desc+"B|";
        }
        else{//全部累计
            point_desc=point_desc+"A|";
        }

        point_desc=point_desc+point;//组成阈值主键标签
        String[][] th=node_th.get("bool").get(point_desc);
        float v=0;
        for (int j=0;j<lasting_time.size();j++){//取每种时段的所有值
            long[] LT_type_j=lasting_time.get(j);
            for (int jj=0;jj<LT_type_j.length;jj++){//遍历该时段内值并计算
                v=Math.max(v,th_cal(Float.parseFloat(String.valueOf(LT_type_j[jj])),th[j]));
            }
        }

        return v;
    }

    public static List<long[]> search_func_L(Date[] time_gap, int point, String per_or_all,
                                            int[] unit, String time_type) throws ParseException {
        //per_or_all——表示计量时段为单段（per）时长或总时长（all）
        //pos_or_neg——为true表示计量状态量为1时段长，否则计量状态量为0时段长
        int[] split_index=new int[]{0};
        List<Date[]> time_psg=time_split(time_gap,time_type, unit, split_index);//时段计算

        int point_id_rectifier=1;
        int[] mark={1,0};//默认为统计1到0的时长
        if (point<0){//若统计相反时段
            mark=new int[]{0,1};//统计0到1时长
            point_id_rectifier=-1;
        }
        List<long[]> lasting_time_all = new ArrayList<>();//存储延续时长

        if (split_index[0]>0) {//如果存在两类时段（例如稳态发电、稳态抽水）
            for (int t_i = 0; t_i < split_index[0]; t_i++) {
                List<Long> lasting_time = new ArrayList<>();
                List<resBool> ori_data = searchBool(time_psg.get(t_i), point*point_id_rectifier);

                //按不同类别统计时长
                if (per_or_all.equals("per")) {//分别计量每次的时间
                    for (int i = 1; i < ori_data.size() - 1; i++) {
                        if (ori_data.get(i).getValue() == mark[0] && ori_data.get(i + 1).getValue() == mark[1]) {
                            lasting_time.add(ori_data.get(i + 1).getTime() - ori_data.get(i).getTime());
                        }
                    }
                    if (lasting_time.size() == 0) {
                        long sum_time = 0;
                        lasting_time.add(sum_time);
                    }
                } else if (per_or_all.equals("all")) {//所有时间加和
                    long sum_time = 0;
                    for (int i = 1; i < ori_data.size() - 1; i++) {
                        if (ori_data.get(i).getValue() == mark[0] && ori_data.get(i + 1).getValue() == mark[1]) {
                            sum_time = sum_time + (ori_data.get(i + 1).getTime() - ori_data.get(i).getTime());
                        }
                    }
                    lasting_time.add(sum_time);
                }

                long[] LT_type_0 = new long[lasting_time.size()];
                for (int j = 0; j < lasting_time.size(); j++) {
                    LT_type_0[j] = lasting_time.get(j);
                }
                lasting_time_all.add(LT_type_0);
            }
        }

        for (int t_i = split_index[0]; t_i < time_psg.size(); t_i++) {
            List<Long> lasting_time = new ArrayList<>();
            List<resBool> ori_data = searchBool(time_psg.get(t_i), point*point_id_rectifier);
            //按不同类别统计时长

            if (per_or_all.equals("per")) {//分别计量每次的时间
                for (int i = 1; i < ori_data.size() - 1; i++) {
                    if (ori_data.get(i).getValue() == mark[0] && ori_data.get(i + 1).getValue() == mark[1]) {
                        lasting_time.add(ori_data.get(i + 1).getTime() - ori_data.get(i).getTime());
                    }
                }
                if (lasting_time.size() == 0) {
                    long sum_time = 0;
                    lasting_time.add(sum_time);
                }
            } else if (per_or_all.equals("all")) {//所有时间加和
                long sum_time = 0;
                for (int i = 1; i < ori_data.size() - 1; i++) {
                    if (ori_data.get(i).getValue() == mark[0] && ori_data.get(i + 1).getValue() == mark[1]) {
                        sum_time = sum_time + (ori_data.get(i + 1).getTime() - ori_data.get(i).getTime());
                    }
                }
                lasting_time.add(sum_time);
            }

            long[] LT_type_1 = new long[lasting_time.size()];
            for (int j = 0; j < lasting_time.size(); j++) {
                LT_type_1[j] = lasting_time.get(j);
            }
            lasting_time_all.add(LT_type_1);
        }

        return lasting_time_all;
    }

    public static float cal_func_G(Date[] time_gap, int id_0, int id_1,
                                   String per_or_all,
                                   Map<String,Map<String,String[][]>> node_th) throws Exception {
        //首先查找差距时长
        List<Long> lasting_time_gaps=search_func_G(time_gap, id_0, id_1, per_or_all);

        //查找对应阈值并计算
        String point_descrip="F|G";
        if (per_or_all.equals("per")){
            point_descrip=point_descrip+"B[";
        }
        else{
            point_descrip=point_descrip+"A[";
        }
        point_descrip=point_descrip+id_1+"]|"+id_0;
        String[][] th=node_th.get("bool").get(point_descrip);

        float v=0;
        for (int j=0;j<lasting_time_gaps.size();j++){//取所有间隔时段值
            long LT_j=lasting_time_gaps.get(j);
            v=Math.max(v,th_cal(Float.parseFloat(String.valueOf(LT_j)),th[j]));
        }

        return v;
    }

    public static List<Long> search_func_G(Date[] time_gap, int id_0, int id_1,
                                           String per_or_all) throws ParseException {
        //per_or_all——表示计量时段为单段（per）时长或总时长（all）
        //pos_or_neg——为true表示计量为1时段长，否则计量为0时段长
        int[] mark={1,1};//默认为统计id_0为1至id_1为1的时长
        int[] point_id_rectifier=new int[]{1,1};
        if (id_0<0){//若起点为id_0=0
            mark[0]=0;//统计0到终点时长
            point_id_rectifier[0]=-1;
        }
        if (id_1<0){//若终点为id_1=0
            mark[1]=0;//统计起点到0时长
            point_id_rectifier[1]=-1;
        }

        List<resBool> ori_data_0=searchBool(time_gap, id_0*point_id_rectifier[0]);
        ori_data_0=cut_redundency(ori_data_0);
        List<resBool> ori_data_1=searchBool(time_gap, id_1*point_id_rectifier[1]);
        ori_data_1=cut_redundency(ori_data_1);

        //按类别进行时长统计
        List<Long> lasting_time=new ArrayList<>();//存储延续时长
        int i_1_cursor=0;
        if (per_or_all.equals("per")) {//分别计量每次的时间
            for (int i_0 = 0; i_0 < ori_data_0.size(); i_0++) {
                resBool current_data_0=ori_data_0.get(i_0);
                if (current_data_0.getValue()==mark[0]){
                    for (int i_1=i_1_cursor;i_1<ori_data_1.size();i_1++){
                        resBool current_data_1=ori_data_1.get(i_1);
                        if (i_0<ori_data_0.size()-1) {
                            resBool current_data_0_1plus=ori_data_0.get(i_0+1);
                            boolean find_mark=false;
                            for (int i_0_1plus=i_0+1;i_0_1plus<ori_data_0.size();i_0_1plus++){
                                if (ori_data_0.get(i_0_1plus).getValue()==mark[0]){
                                    current_data_0_1plus=ori_data_0.get(i_0_1plus);
                                    find_mark=true;
                                    break;
                                }
                            }
                            if (find_mark) {
                                long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                                long gap_1 = current_data_0_1plus.getTime() - current_data_1.getTime();
                                if (current_data_1.getValue() == mark[1] && gap_0 >= 0 && gap_1 < 0) {
                                    lasting_time.add(gap_0);
                                    i_1_cursor = i_1 + 1;
                                    break;
                                }
                            }
                            else{
                                long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                                if (current_data_1.getValue() == mark[1] && gap_0 >= 0 ) {
                                    lasting_time.add(gap_0);
                                    i_1_cursor = i_1 + 1;
                                    break;
                                }
                            }
                        }
                        else{
                            long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                            if (current_data_1.getValue() == mark[1] && gap_0 >= 0) {
                                lasting_time.add(gap_0);
                                i_1_cursor = i_1 + 1;
                                break;
                            }

                        }
                    }
                }
            }
            if (lasting_time.size()==0){
                long sum_time=0;
                lasting_time.add(sum_time);
            }
        }
        else if (per_or_all.equals("all")){//所有时间加和
            long sum_time=0;
            for (int i_0 = 0; i_0 < ori_data_0.size(); i_0++) {
                resBool current_data_0=ori_data_0.get(i_0);
                if (current_data_0.getValue()==mark[0]){
                    for (int i_1=i_1_cursor;i_1<ori_data_1.size();i_1++){
                        resBool current_data_1=ori_data_1.get(i_1);
                        if (i_0<ori_data_0.size()-1) {
                            resBool current_data_0_1plus=ori_data_0.get(i_0+1);
                            boolean find_mark=false;
                            for (int i_0_1plus=i_0+1;i_0_1plus<ori_data_0.size();i_0_1plus++){
                                if (ori_data_0.get(i_0_1plus).getValue()==mark[0]){
                                    current_data_0_1plus=ori_data_0.get(i_0_1plus);
                                    find_mark=true;
                                    break;
                                }
                            }
                            if (find_mark) {
                                long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                                long gap_1 = current_data_0_1plus.getTime() - current_data_1.getTime();
                                if (current_data_1.getValue() == mark[1] && gap_0 >= 0 && gap_1 < 0) {
                                    sum_time=sum_time+gap_0;
                                    i_1_cursor = i_1 + 1;
                                    break;
                                }
                            }
                            else{
                                long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                                if (current_data_1.getValue() == mark[1] && gap_0 >= 0 ) {
                                    sum_time=sum_time+gap_0;
                                    i_1_cursor = i_1 + 1;
                                    break;
                                }
                            }
                        }
                        else{
                            long gap_0 = current_data_1.getTime() - current_data_0.getTime();
                            if (current_data_1.getValue() == mark[1] && gap_0 >= 0) {
                                sum_time=sum_time+gap_0;
                                i_1_cursor = i_1 + 1;
                                break;
                            }

                        }
                    }
                }
            }
            lasting_time.add(sum_time);
        }

        return lasting_time;
    }

    public static float cal_func_C(Date[] time_gap, int[] id_set,
                                   int[]  unit_set, String time_type,
                                   Map<String,Map<String,String[][]>> node_th) throws Exception {
        //查找不同类型时段结果
        List<Map<Integer, Integer>> ori_data=search_func_C(time_gap,id_set,unit_set,time_type,node_th);

        //查找对应阈值范围并计算
        String unit_str="[";
        for (int i=0;i<unit_set.length;i++){
            unit_str=unit_str+unit_set[i];
            if (i<unit_set.length-1){
                unit_str=unit_str+",";
            }
        }
        unit_str=unit_str+"]";
        String point_descrip=time_type;
        if (!time_type.equals("F")) {//如果不是全时段，需要添加机组号描述
            point_descrip=point_descrip+unit_str;
        }
        point_descrip=point_descrip+ "|C[";
        for (int i=0;i<id_set.length;i++){
            point_descrip=point_descrip+id_set[i];
            if (i<id_set.length-1){
                point_descrip=point_descrip+",";
            }
        }
        point_descrip=point_descrip+"]|";

        String[][] th=node_th.get("bool").get(point_descrip);
        float v=0;
        for (int j=0;j<ori_data.size();j++){
            int val=ori_data.get(j).get(2)+ori_data.get(j).get(3);
            v=Math.max(v,th_cal(Float.parseFloat(String.valueOf(val)),th[j]));
        }

        return v;
    }

    public static List<Map<Integer, Integer>> search_func_C(Date[] time_gap, int[] id_set,
                                                            int[]  unit_set, String time_type,
                                                            Map<String,Map<String, String[][]>> node_th) throws Exception {
        List<Map<Integer,Integer>> res=new ArrayList<>();//每个Map存储的是不同时间类型的越限点个数
        int[] state_split_index=new int[]{0};
        List<Date[]> time_psg=time_split(time_gap,time_type,unit_set,state_split_index);
        if (state_split_index[0]!=0){
            List<Date[]> time_psg_0=time_psg.subList(0,state_split_index[0]);
            res.add(search_func_C_simple(time_psg_0,id_set,unit_set,time_type,node_th));
        }
        List<Date[]> time_psg_1=time_psg.subList(state_split_index[0],time_psg.size()-1);
        res.add(search_func_C_simple(time_psg_1,id_set,unit_set,time_type,node_th));

        return res;
    }

    public static Map<Integer, Integer> search_func_C_simple(List<Date[]> time_psg, int[] id_set,
                                                             int[]  unit_set, String time_type,
                                                             Map<String,Map<String, String[][]>> node_th) throws Exception {
        //Map<Integer, Integer>中第一个Integer存储等级标签（1~3），后一Integer存储在相应区间的点数
        //1——轻度劣化区间，2——中度劣化区间，3——重度及以上劣化区间
        int count_1=0;
        int count_2=0;
        int count_3=0;

        for (int i=0;i<id_set.length;i++) {//遍历所有点
            int count_val=1;
            int point_id_rectifier=1;
            if (id_set[i]<0){
                count_val=0;
                point_id_rectifier=-1;
            }

            float cal_res=0;
            for (int j=0;j<time_psg.size();j++) {//对所有时段进行遍历
                //更新节点值，取最严重的状态
                cal_res=Math.max(cal_res,
                         cal_func_B(time_psg.get(j),id_set[i],"bool",time_type,unit_set,node_th));
            }

            //计算该点值对应在何劣化区间
            if (cal_res <= 3) {//在轻度劣化区间
                count_1++;
            } else if (cal_res >= 7) {//在重度及以上劣化区间
                count_3++;
            } else {//在中度劣化区间
                count_2++;
            }
        }

        //写入数据
        Map<Integer, Integer> res=new HashMap<>();
        res.put(1,count_1);
        res.put(2,count_2);
        res.put(3,count_3);

        return res;
    }

    public static float cal_func_D(Date[] time_gap, long window, int point_id, String time_type, int[] unit_set,
                                   Map<String,Map<String,String[][]>> node_th) throws Exception {
        //首先进行差值计算时段推算
        long st_time=timeUtil.dateToLong(time_gap[0])/1000-window;
        Date[] time_diff=new Date[]{timeUtil.longToDate(st_time*1000,"yyyy-MM-dd HH:mm:ss"),
                time_gap[1]};

        //查找记录
        int[] state_split_index=new int[]{0};
        List<float[]> ori_data=search_func_D(time_diff, window, point_id,time_type,unit_set,state_split_index);

        //其次取得阈值并进行计算
        String unit_str="[";
        for (int i=0;i<unit_set.length;i++){
            unit_str=unit_str+unit_set[i];
            if (i<unit_set.length-1){
                unit_str=unit_str+",";
            }
        }
        unit_str=unit_str+"]";
        String point_descrip=time_type;
        if (!time_type.equals("F")) {//如果不是全时段，需要添加机组号描述
            point_descrip=point_descrip+unit_str;
        }
        point_descrip=point_descrip+ "|D["+window+"]|"+point_id;
        String[][] th=node_th.get("float").get(point_descrip);

        String[] tst=th[0];
        boolean relative=false;
        for (int tst_i=0;tst_i<tst.length;tst_i++) {
            if (tst[tst_i].contains("%")){//如果阈值中出现%，则为相对值
                relative=true;
                break;
            }
        }

        float v=0;
        for (int i=0;i<ori_data.size();i++){
            float[] gap_t=ori_data.get(i);
            float cal_val=gap_t[1]-gap_t[0];
                if (relative) {//相对值，进行处理
                    cal_val=cal_val/gap_t[0];
                }
            v=Math.max(v,th_cal(cal_val, th[i]));
        }
        return v;
    }

    public static List<float[]> search_func_D(Date[] time_diff, long window, int point_id, String time_type,
                                              int[] unit_set, int[] state_split_index) throws ParseException {

        List<float[]> res=new ArrayList<>();//存储结果
        List<Date[]> time_psg=time_split(time_diff, time_type, unit_set, state_split_index);

        //随时段内数据进行查询并计算
        float max_value_0=0, max_value_1=0;//存储发电、抽水的最大值
        float min_value_0=0, min_value_1=0;//存储发电、抽水的最小值

        //进行相关计算
        //按工况存储数据
        List<resFloat> R_0=new ArrayList<>();
        for (int i=0;i<state_split_index[0];i++){//第一类工况
            List<resFloat> r_i=searchFloat(time_psg.get(i),point_id);
            R_0.addAll(r_i);
        }
        for (int i=0;i<R_0.size();i++){
            long t_i_0=R_0.get(i).getTime();
            int last_i=i+1;
            for (int ii=i+1;ii<R_0.size();ii++){
                if ((R_0.get(ii).getTime()-t_i_0)<=window){
                    last_i=ii;
                }
                else{
                    break;
                }
            }

            List<resFloat> window_psg_data=R_0.subList(i,last_i);//取得符合窗口的记录片段
            float min_win=window_psg_data.get(0).getValue();
            float max_win=window_psg_data.get(0).getValue();
            for (int jj=1;jj<window_psg_data.size();jj++){
                min_win=Math.min(min_win,window_psg_data.get(jj).getValue());
                max_win=Math.max(max_win,window_psg_data.get(jj).getValue());
            }

            if ((max_win-min_win)>(max_value_0-min_value_0)){
                min_value_0=min_win;
                max_value_0=max_win;
            }

            if (last_i>=R_0.size()-1){
                break;
            }
        }

        List<resFloat> R_1=new ArrayList<>();
        for (int i=state_split_index[0];i<time_psg.size();i++){//第二类工况
            List<resFloat> r_i=searchFloat(time_psg.get(i),point_id);
            R_1.addAll(r_i);
        }
        for (int i=0;i<R_1.size();i++){
            long t_i_1=R_1.get(i).getTime();
            int last_i=i+1;
            for (int ii=i+1;ii<R_1.size();ii++){
                if ((R_1.get(ii).getTime()-t_i_1)<=window){
                    last_i=ii;
                }
                else{
                    break;
                }
            }

            List<resFloat> window_psg_data=R_1.subList(i,last_i);//取得符合窗口的记录片段
            float min_win=window_psg_data.get(0).getValue();
            float max_win=window_psg_data.get(0).getValue();
            for (int jj=1;jj<window_psg_data.size();jj++){
                min_win=Math.min(min_win,window_psg_data.get(jj).getValue());
                max_win=Math.max(max_win,window_psg_data.get(jj).getValue());
            }

            if ((max_win-min_win)>(max_value_1-min_value_1)){
                min_value_1=min_win;
                max_value_1=max_win;
            }

            if (last_i>=R_1.size()-1){
                break;
            }
        }

        //依据工况数量存储结果并输出
        if (state_split_index[0]==0){//只有一种工况
            res.add(new float[]{min_value_1,max_value_1});
        }
        else{//有两种工况
            res.add(new float[] {min_value_0,max_value_0});
            res.add(new float[] {min_value_1,max_value_1});
        }
        return res;
    }

    public static float cal_func_B(Date[] time_gap, int point_id, String point_type,
                                   String time_type, int[] unit_set,
                                   Map<String,Map<String, String[][]>> node_th) throws Exception {
        //首先查找相应时段测点计算后评价值（单精量的最小最大值、状态量的加和（次数））
        List<float[]> test_data=search_func_B(time_gap, point_id, point_type, time_type, unit_set);

        //取相应阈值
        String unit_str="[";
        for (int i=0;i<unit_set.length;i++){
            unit_str=unit_str+unit_set[i];
            if (i<unit_set.length-1){
                unit_str=unit_str+",";
            }
        }
        unit_str=unit_str+"]";
        String point_th_descrip=time_type;
        if (!time_type.equals("F")) {//如果不是全时段，需要添加机组号描述
            point_th_descrip=point_th_descrip+unit_str;
        }
        point_th_descrip=point_th_descrip+ "|B|"+point_id;

        String[][] point_th=node_th.get(point_type).get(point_th_descrip);

        //对每个点值进行评价
        float v=0;
        for (int i=0;i<test_data.size();i++){
            float[] data_same_time_type=test_data.get(i);
            for (int ii=0;ii<data_same_time_type.length;ii++){
                v=Math.max(v,th_cal(data_same_time_type[ii],point_th[i]));
            }
        }

        return v;
    }

    public static List<float[]> search_func_B(Date[] time_gap, int point_id, String point_type,
                                              String time_type, int[] unit_set) throws ParseException {
        //同一float[]中存储同一时间类型的值（eg.List中第一个float[]存发电稳态的测点数值，第二个float[]存抽水稳态的测点数值）
        int[] split_index=new int[]{0};
        List<Date[]> time_psg=time_split(time_gap, time_type, unit_set, split_index);

        boolean type_float=true;
        if (point_type.equals("bool")){
            type_float=false;
        }

        List<Float> data_value_0=new ArrayList<>();
        for (int i=0;i<split_index[0];i++){
            if (type_float){
                List<resFloat> ori_data_psg_i=searchFloat(time_psg.get(i),point_id);
                for (int ii=0;ii<ori_data_psg_i.size();ii++){
                    data_value_0.add(ori_data_psg_i.get(ii).getValue());
                }
            }
            else{
                int point_id_rectifier=1;
                if (point_id<0){
                    point_id_rectifier=-1;
                }
                List<resBool> ori_data_psg_i=searchBool(time_psg.get(i),point_id*point_id_rectifier);
                ori_data_psg_i=cut_redundency(ori_data_psg_i);
                if (ori_data_psg_i.size() > 1) {
                    if (ori_data_psg_i.get(ori_data_psg_i.size() - 2).getValue() ==
                            ori_data_psg_i.get(ori_data_psg_i.size() - 1).getValue()) {
                        ori_data_psg_i.remove(ori_data_psg_i.size() - 1);//这一步用于消除末尾两个连续的重复值（包含只有两条记录的情况），
                        // 因为在使用cut_redundency函数时会保留最后一条记录，常出现与倒数第二条记录数值相等的情况
                    }
                }
                if (point_id<0){//对数据进行翻转
                    for (int rever_i=0;rever_i<ori_data_psg_i.size();rever_i++){
                        resBool current_record=ori_data_psg_i.get(rever_i);
                        if (current_record.getValue()==0){
                            resBool update_record=new resBool(
                                    current_record.getTime(),1,current_record.getFlag());
                            ori_data_psg_i.set(rever_i,update_record);
                        }
                        else{
                            resBool update_record=new resBool(
                                    current_record.getTime(),0,current_record.getFlag());
                            ori_data_psg_i.set(rever_i,update_record);
                        }
                    }
                }

                for (int ii=0;ii<ori_data_psg_i.size();ii++){
                    data_value_0.add(Float.parseFloat(String.valueOf(
                            ori_data_psg_i.get(ii).getValue())));
                }
            }
        }

        List<Float> data_value_1=new ArrayList<>();
        for (int i=split_index[0];i<time_psg.size();i++){
            if (type_float){
                List<resFloat> ori_data_psg_i=searchFloat(time_psg.get(i),point_id);
                for (int ii=0;ii<ori_data_psg_i.size();ii++){
                    data_value_1.add(ori_data_psg_i.get(ii).getValue());
                }
            }
            else{
                List<resBool> ori_data_psg_i=searchBool(time_psg.get(i),point_id);
                ori_data_psg_i=cut_redundency(ori_data_psg_i);
                if (ori_data_psg_i.size() > 1) {
                    if (ori_data_psg_i.get(ori_data_psg_i.size() - 2).getValue() ==
                            ori_data_psg_i.get(ori_data_psg_i.size() - 1).getValue()) {
                        ori_data_psg_i.remove(ori_data_psg_i.size() - 1);//这一步用于消除末尾两个连续的重复值（包含只有两条记录的情况），
                        // 因为在使用cut_redundency函数时会保留最后一条记录，常出现与倒数第二条记录数值相等的情况
                    }
                }
                if (point_id<0){//对数据进行翻转
                    for (int rever_i=0;rever_i<ori_data_psg_i.size();rever_i++){
                        resBool current_record=ori_data_psg_i.get(rever_i);
                        if (current_record.getValue()==0){
                            resBool update_record=new resBool(
                                    current_record.getTime(),1,current_record.getFlag());
                            ori_data_psg_i.set(rever_i,update_record);
                        }
                        else{
                            resBool update_record=new resBool(
                                    current_record.getTime(),0,current_record.getFlag());
                            ori_data_psg_i.set(rever_i,update_record);
                        }
                    }
                }

                for (int ii=0;ii<ori_data_psg_i.size();ii++){
                    data_value_1.add(Float.parseFloat(String.valueOf(
                            ori_data_psg_i.get(ii).getValue())));
                }
            }
        }

        List<float[]> res=new ArrayList<>();
        if (split_index[0]>0){
            if (type_float){
                float[] min_max=new float[]{data_value_0.get(0),data_value_0.get(0)};
                for (int j=1;j<data_value_0.size();j++){
                    min_max[0]=Math.min(min_max[0],data_value_0.get(j));
                    min_max[1]=Math.max(min_max[1],data_value_0.get(j));
                }
                res.add(min_max);
            }
            else{
                float[] time_sum=new float[]{0};
                for (int j=0;j<data_value_0.size();j++){
                    time_sum[0]=time_sum[0]+data_value_0.get(j);
                }
                res.add(time_sum);
            }
        }

        if (type_float){
            float[] min_max=new float[]{data_value_1.get(0),data_value_1.get(0)};
            for (int j=1;j<data_value_1.size();j++){
                min_max[0]=Math.min(min_max[0],data_value_1.get(j));
                min_max[1]=Math.max(min_max[1],data_value_1.get(j));
            }
            res.add(min_max);
        }
        else{
            float[] time_sum=new float[]{0};
            for (int j=0;j<data_value_1.size();j++){
                time_sum[0]=time_sum[0]+data_value_1.get(j);
            }
            res.add(time_sum);
        }

        return res;
    }

    public static tree cal_tree(String tree_type, Date[] time_gap, int unit) throws Exception {
        tree tr = tree_reader(tree_type);//首先读取整棵树
        tr.init_val();//初始化节点值

        //首先判断是否有符合计算要求的时段
        if (time_checker(tr, time_gap, unit)){
            //之后更新叶子节点的值
            leaf_nodes_unpdater(tr, tree_type, time_gap, unit);

            //最后更新中间节点的值
            mid_nodes_updater(tr);
        }

        //否则返回初始化后的树
        return tr;
    }

    public static boolean time_checker(tree tr, Date[] time_gap, int unit) throws ParseException {//时段验证函数
        boolean check_flag=true;
        leafnode[] LN=tr.get_leaf_nodes();
        for (int i=0;i<LN.length;i++){
            leafnode current_LN=LN[i];
            if (current_LN.get_state()==leafnode.state_online_mark){//对在线点计算时段
                String[][] points=current_LN.get_point_index().get(unit);
                for (int ii=0;ii<points.length;ii++) {
                    String[] point_descrip=points[ii][0].split("\\|");
                    String time_descrip_str=point_descrip[0];
                    timeDescriber TD = time_descrip_decoder(time_descrip_str);
                    List<Date[]> time_psg=time_split(time_gap,TD.getType(),TD.getUnit_set());
                    if (time_psg.size()==0){
                        check_flag=false;
                        break;
                    }
                }
            }
            if (!check_flag){
                break;
            }
        }
        return check_flag;
    }

    public static void leaf_nodes_unpdater(tree tr, String tr_type, Date[] time_gap,
                                           int unit) throws Exception {//叶子节点更新函数
        leafnode[] LN=tr.get_leaf_nodes();
        Map<Integer,Map<String,Map<String,String[][]>>> LN_th=nodes_threshold_reader(tr_type);//查询节点阈值
        resOffline ROL = offline_res_reader(tr, tr_type, unit, DB_URL_res, USR, PSW);//查询时段内离线点的分值
        for (int i=0;i<LN.length;i++){//遍历每个叶子节点
            leafnode current_LN=LN[i];
            int node_id=current_LN.get_index();
            if (current_LN.get_state()==leafnode.state_online_mark) {
                String[][] points = current_LN.get_point_index().get(unit);
                Map<String, Map<String, String[][]>> current_LN_th = LN_th.get(node_id);
                float v = 0;//存储计算所得值
                for (int ii = 0; ii < points.length; ii++) {//对每个点进行计算
                    String[] current_point = points[ii];
                    String point_type = current_point[1];
                    String[] point_descrip = current_point[0].split("\\|");
                    if (point_descrip.length > 1) {//排除为0的记录（即无对应测点的项目）
                        //整理时间类型
                        String time_descrip = point_descrip[0];
                        String time_type = time_descrip;
                        int[] time_unit_set = new int[]{1};
                        if (time_descrip.contains("[")){
                            time_type = time_descrip.substring(0, time_descrip.indexOf("["));
                            String[] time_unit_set_str = time_descrip.substring(time_descrip.indexOf("[") + 1,
                                    time_descrip.indexOf("]")).split(",");
                            time_unit_set = new int[time_unit_set_str.length];
                            for (int tus_i = 0; tus_i < time_unit_set.length; tus_i++) {
                                time_unit_set[tus_i] = Integer.parseInt(time_unit_set_str[tus_i]);
                            }
                        }

                        //按不同计算方式进行相应计算
                        String cal_descrip = point_descrip[1];
                        int point_id;
                        char cal_type_head = cal_descrip.charAt(0);
                        switch (cal_type_head) {
                            case 'B'://基本类型
                                point_id = Integer.parseInt(point_descrip[2]);
                                v = cal_func_B(time_gap, point_id, point_type, time_type, time_unit_set, current_LN_th);
                                break;
                            case 'L':
                                point_id = Integer.parseInt(point_descrip[2]);
                                String per_or_all = "per";
                                if (cal_descrip.charAt(1) == 'A') {
                                    per_or_all = "all";
                                }
                                v = cal_func_L(time_gap, point_id, per_or_all,
                                        current_LN_th, time_unit_set, time_type);
                                break;
                            case 'D':
                                point_id = Integer.parseInt(point_descrip[2]);
                                long window = Long.parseLong(cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ));
                                v = cal_func_D(time_gap, window, point_id, time_type, time_unit_set, current_LN_th);
                                break;
                            case 'G':
                                per_or_all = "per";
                                if (cal_descrip.charAt(1) == 'A') {
                                    per_or_all = "all";
                                }
                                int point_id_0 = Integer.parseInt(point_descrip[2]);
                                int point_id_1 = Integer.parseInt(cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ));
                                v = cal_func_G(time_gap, point_id_0, point_id_1, per_or_all, current_LN_th);
                                break;
                            case 'C':
                                String[] points_id_set_str = cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ).split(",");
                                int[] points_id_set = new int[points_id_set_str.length];
                                for (int pis_i = 0; pis_i < points_id_set_str.length; pis_i++) {
                                    points_id_set[pis_i] = Integer.parseInt(points_id_set_str[pis_i]);
                                }
                                v = cal_func_C(time_gap, points_id_set, time_unit_set, time_type, current_LN_th);
                                break;
                        }
                        current_LN.write_value(v*current_LN.get_weight());
                    }
                }
                System.out.println("online leafnode "+current_LN.get_name()+" value = "+current_LN.get_value());
            }
            else{//离线节点
                float offline_leafnode_value=ROL.getRes().get(node_id);
                current_LN.write_value(offline_leafnode_value);//写入离线查表所得值
                System.out.println("offline leafnode "+current_LN.get_name()+" value = "+offline_leafnode_value);
            }
            LN[i]=current_LN;//写入LN
        }
        tr.leaf_nodes=LN;
    }

    public static void mid_nodes_updater(tree tr) {//中间节点更新函数
        for (int i = 0; i < tr.get_leaf_nodes().length; i++) {//采用遍历的方法更新树中中间节点的值
            mid_nodes_updater_step(tr, tr.get_leaf_nodes()[i].get_father(), tr.get_leaf_nodes()[i].get_value());
        }
    }

    public static void mid_nodes_updater_step(tree tr, int fa, float val) {//中间节点更新函数
        if (fa != -1) {
            tr.get_mid_node_by_index(fa).write_value(val);
            mid_nodes_updater_step(tr, tr.get_mid_node_by_index(fa).get_father(), tr.get_mid_node_by_index(fa).get_value());
        }
    }

    public static List<Date[]> time_split(Date[] time_gap,String time_type, int[] unit_set) throws ParseException {
        int[] state_split_index=new int[]{0};
        return time_split(time_gap,time_type,unit_set,state_split_index);
    }

    public static List<Date[]> time_split(Date[] time_gap,String time_type, int[] unit_set, int[] state_split_index) throws ParseException {
        List<Date[]> time_psg=new ArrayList<>();
        state_split_index[0]=0;//不同工况时段序号分界线（发电/抽水）,从该序号后时段就为另一工况

        switch (time_type){//计算对应时段
            case "ST"://发电稳态
                for (int i=0;i<unit_set.length;i++){
                    Date[][] stime_t=steadyTime(time_gap,unit_set[i],work_condition_turbine);
                    if (stime_t!=null) {
                        for (int jj = 0; jj < stime_t.length; jj++) {
                            time_psg.add(stime_t[jj]);
                        }
                    }
                }
                break;
            case "SP"://抽水稳态
                for (int i=0;i<unit_set.length;i++){
                    Date[][] stime_p=steadyTime(time_gap,unit_set[i],work_condition_pump);
                    if (stime_p!=null) {
                        for (int jj = 0; jj < stime_p.length; jj++) {
                            time_psg.add(stime_p[jj]);
                        }
                    }
                }
                break;
            case "SA"://全稳态
                for (int i=0;i<unit_set.length;i++){
                    Date[][] stime_t=steadyTime(time_gap,unit_set[i],work_condition_turbine);
                    if (stime_t!=null) {
                        for (int jj = 0; jj < stime_t.length; jj++) {
                            time_psg.add(stime_t[jj]);
                        }
                    }
                    Date[][] stime_p=steadyTime(time_gap,unit_set[i],work_condition_pump);
                    if (stime_p!=null) {
                        for (int jj = 0; jj < stime_p.length; jj++) {
                            time_psg.add(stime_p[jj]);
                        }
                    }
                }
                break;
            case "S"://分稳态
                List<Date[]> ST_tur=new ArrayList<>();
                List<Date[]> ST_pump=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //发电稳态
                    Date[][] stime_t = steadyTime(time_gap, unit_set[i], work_condition_turbine);
                    if (stime_t!=null) {
                        for (int jj = 0; jj < stime_t.length; jj++) {
                            ST_tur.add(stime_t[jj]);
                        }
                    }

                    //抽水稳态
                    Date[][] stime_p=steadyTime(time_gap,unit_set[i],work_condition_pump);
                    if (stime_p!=null) {
                        for (int jj = 0; jj < stime_p.length; jj++) {
                            ST_pump.add(stime_p[jj]);
                        }
                    }
                }
                state_split_index[0]=ST_tur.size();
                time_psg.addAll(ST_tur);
                time_psg.addAll(ST_pump);
                break;
            case "WT"://发电态
                List<Date[]> WT_tur=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水轮机时段
                    Date[][] tur_time=workTime(time_gap,unit_set[i],work_condition_turbine);
                    if (tur_time!=null) {
                        for (int ii = 0; ii < tur_time.length; ii++) {
                            WT_tur.add(tur_time[ii]);
                        }
                    }
                }
                time_psg.addAll(WT_tur);
                break;
            case "WP"://抽水态
                List<Date[]> WT_pump=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水泵时段
                    Date[][] pump_time=workTime(time_gap,unit_set[i],work_condition_pump);
                    if (pump_time!=null) {
                        for (int ii = 0; ii < pump_time.length; ii++) {
                            WT_pump.add(pump_time[ii]);
                        }
                    }
                }
                time_psg.addAll(WT_pump);
                break;
            case "WA"://全发电抽水态
                WT_tur=new ArrayList<>();
                WT_pump=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水轮机时段
                    Date[][] tur_time=workTime(time_gap,unit_set[i],work_condition_turbine);
                    if (tur_time!=null) {
                        for (int ii = 0; ii < tur_time.length; ii++) {
                            WT_tur.add(tur_time[ii]);
                        }
                    }

                    //统计水泵时段
                    Date[][] pump_time=workTime(time_gap,unit_set[i],work_condition_pump);
                    if (pump_time!=null) {
                        for (int ii = 0; ii < pump_time.length; ii++) {
                            WT_pump.add(pump_time[ii]);
                        }
                    }
                }
                time_psg.addAll(WT_tur);
                time_psg.addAll(WT_pump);
                break;
            case "W"://分发电抽水态
                WT_tur=new ArrayList<>();
                WT_pump=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水轮机时段
                    Date[][] tur_time=workTime(time_gap,unit_set[i],work_condition_turbine);
                    if (tur_time!=null) {
                        for (int ii = 0; ii < tur_time.length; ii++) {
                            WT_tur.add(tur_time[ii]);
                        }
                    }

                    //统计水泵时段
                    Date[][] pump_time=workTime(time_gap,unit_set[i],work_condition_pump);
                    if (pump_time!=null) {
                        for (int ii = 0; ii < pump_time.length; ii++) {
                            WT_pump.add(pump_time[ii]);
                        }
                    }
                }

                state_split_index[0]=WT_tur.size();
                time_psg.addAll(WT_tur);
                time_psg.addAll(WT_pump);
                break;
            case "TS"://停机稳态
                List<Date[]> TS=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水泵时段
                    Date[][] TS_time=terminalTime(time_gap,unit_set[i],1);
                    if (TS_time!=null) {
                        for (int ii = 0; ii < TS_time.length; ii++) {
                            TS.add(TS_time[ii]);
                        }
                    }
                }
                time_psg.addAll(TS);
                break;
            case "T"://停机态
                List<Date[]> T=new ArrayList<>();
                for (int i=0;i<unit_set.length;i++) {
                    //统计水泵时段
                    Date[][] T_time=terminalTime(time_gap,unit_set[i],0);
                    if (T_time!=null) {
                        for (int ii = 0; ii < T_time.length; ii++) {
                            T.add(T_time[ii]);
                        }
                    }
                }
                time_psg.addAll(T);
                break;
            default://全时段（即“F”）
                time_psg.add(time_gap);
                break;
        }
        return time_psg;
    }

    public static Map<Integer, Map< String, Map<String, String[][]>>> nodes_threshold_reader(String type){
        return nodes_threshold_reader(type, DB_URL_res, USR, PSW);
    }

    //读取node阈值
    public static Map<Integer, Map< String, Map<String, String[][]>>> nodes_threshold_reader(String type,
                                                                                             String database_url, String user, String psw){
        //结果Map中，Integer为节点号，第一个String为测点类型（float、bool），第二个String为阈值类型说明前缀，String[][]为阈值
        Map<Integer, Map<String, Map<String, String[][]>>> nodes_th=new HashMap<>();
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行阈值最新版本搜索
             */
            String tb_name_guide = "eva_node_threshold_guide";
            String conditions = "type= '" + type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            // 保存最后一条记录
            rs.last();
            String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
            int version_model = rs.getInt("model_version");//最新模型版本号
            int version_threshold = rs.getInt("threshold_version");//最新模型版本号

            // 输出数据
            System.out.print("最新"+type+"评价模型节点阈值存储时间: " + tm);
            System.out.print(", 最新模型版本号: " + version_model);
            System.out.print(", 最新节点阈值版本号: " + version_threshold);
            System.out.print("\n");

            /*
            取对应阈值表格中数据
             */
            String tb_name_th = "eva_node_threshold_"+type+"_"+version_model+"_"+version_threshold;
            String sql_th = "SELECT * FROM " + tb_name_th;//取所有记录
            ResultSet rs_th = stmt.executeQuery(sql_th);

            //对结果进行格式整理，存入nodes_th中
            while(rs_th.next()){
                Map<String, Map<String, String[][]>> type_th=new HashMap<>();
                int th_node_index=rs_th.getInt("id");
                System.out.println("开始读取 "+th_node_index+" 节点阈值");
                //存单精量阈值
                Map<String, String[][]> f_th=new HashMap<>();
                String th_node_f=rs_th.getString("float_threshold");
                String[] th_points_f=th_node_f.split("#");
                for (int i=0;i<th_points_f.length;i++){//遍历所有点的阈值
                    String current_th_string=th_points_f[i];
                    if (current_th_string.length()>1) {
                        String th_descrip = current_th_string.substring(0, current_th_string.indexOf("{"));
                        String[][] th_value = operation_threshold(current_th_string.substring(current_th_string.indexOf("{")));
                        f_th.put(th_descrip, th_value);
                        System.out.println("单精量测点 "+th_descrip+" 阈值读取完成");
                    }
                }
                //存状态量阈值
                Map<String, String[][]> b_th=new HashMap<>();
                String th_node_b=rs_th.getString("bool_threshold");
                String[] th_points_b=th_node_b.split("#");
                for (int i=0;i<th_points_b.length;i++){//遍历所有点的阈值
                    String current_th_string=th_points_b[i];
                    if (current_th_string.length()>1) {
                        String th_descrip = current_th_string.substring(0, current_th_string.indexOf("{"));
                        String[][] th_value = operation_threshold(current_th_string.substring(current_th_string.indexOf("{")));
                        b_th.put(th_descrip, th_value);
                        System.out.println("状态量测点 "+th_descrip+" 阈值读取完成");
                    }
                }
                //存入结果
                type_th.put("float",f_th);
                type_th.put("bool",b_th);
                nodes_th.put(th_node_index,type_th);
            }

            // 完成后关闭
            System.out.println(type+" 模型叶子节点阈值读取成功！");
            rs.close();
            rs_th.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return nodes_th;
    }

    public static void node_threshold_saver(Map<Integer, Map< String, Map<String, String[][]>>> nodes_th,String type,
                                             int model_ver_new,
                                             String database_url, String user, String psw){//存入阈值至数据库中（对象至数据库）
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            /*
            进行阈值最新版本搜索
             */
            String tb_name_guide = "eva_node_threshold_guide";
            String tb_create = "CREATE TABLE IF NOT EXISTS " + tb_name_guide + " " +
                    "(time INT(11) NOT NULL, type VARCHAR(10) NOT NULL, " +
                    "model_version INT(11) NOT NULL, threshold_version INT(11) NOT NULL," +
                    "PRIMARY KEY(time, type))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(tb_create);//如果引导表不存在则先建表

            String conditions = "type= '" + type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            int version_model=0;
            int version_threshold = 0;
            int version_th_new=1;
            // 保存最后一条记录
            if (rs.next()) {
                // 保存最后一条记录
                rs.last();
                String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
                version_model = rs.getInt("model_version");//最新模型版本号
                version_threshold = rs.getInt("threshold_version");//最新模型版本号

                // 输出数据
                System.out.print("最新"+type+"评价模型节点阈值存储时间: " + tm);
                System.out.print(", 模型版本号: " + version_model);
                System.out.print(", 阈值版本号: " + version_threshold);
                System.out.print("\n");
            }
            //在引导表中添加记录
            Calendar c = Calendar.getInstance();
            Date time_now = c.getTime();
            int year_old=(int)version_threshold/10000;
            int year_new = c.get(Calendar.YEAR);
            long tm_new = timeUtil.dateToLong(time_now) / 1000;
            version_th_new = year_old * 10000 + version_threshold % 10000 + 1;
            if (year_new!=year_old||version_model!=model_ver_new){
                version_th_new = year_new *10000 +1;
            }

            System.out.print("新建 "+type+" 评价模型节点阈值存储时间: " + tm_new);
            System.out.print(", 模型版本号: " + version_model);
            System.out.print(", 阈值版本号: " + version_th_new);
            System.out.print("\n");
            //更新阈值引导表
            String value_set_guide = "(" + tm_new + ", '" + type + "', " + model_ver_new+","+version_th_new + ")";
            sql = "INSERT INTO " + tb_name_guide + " VALUES " + value_set_guide;
            stmt.executeUpdate(sql);

            /*
            存储阈值表格中数据
             */
            String tb_name_th = "eva_node_threshold_"+type+"_"+version_model+"_"+version_th_new;
            String tb_create_th = "CREATE TABLE IF NOT EXISTS " + tb_name_th + " (" +
                    "id INT(11) NOT NULL, float_threshold VARCHAR(4000) , " +
                    "bool_threshold VARCHAR(4000) , PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(tb_create_th);//如果表不存在则先建表

            //按编号存入阈值
            Set<Integer> node_keySet = nodes_th.keySet();
            Iterator<Integer> node_keyIter = node_keySet.iterator();
            while (node_keyIter.hasNext()) {//按顺序写入所有节点阈值的信息
                Integer current_node_id = node_keyIter.next();//节点编号
                Map<String, Map<String, String[][]>> current_node_th=nodes_th.get(current_node_id);
                Map<String, String[][]> float_th=current_node_th.get("float");
                Map<String, String[][]> bool_th=current_node_th.get("bool");
                String float_th_str = null;
                String bool_th_str=null;

                //存储单精量阈值与状态量阈值
                Set<String> float_th_keySet=float_th.keySet();
                Iterator<String> float_th_keyIter=float_th_keySet.iterator();
                Set<String> bool_th_keySet=bool_th.keySet();
                Iterator<String> bool_th_keyIter=bool_th_keySet.iterator();
                while (float_th_keyIter.hasNext()){
                    String current_point_id=float_th_keyIter.next();
                    String[][] current_point_th=float_th.get(current_point_id);
                    float_th_str=float_th_str+current_point_id+operation_threshold(current_point_th)+"#";
                }
                while (bool_th_keyIter.hasNext()){
                    String current_point_id=bool_th_keyIter.next();
                    String[][] current_point_th=bool_th.get(current_point_id);
                    bool_th_str=bool_th_str+current_point_id+operation_threshold(current_point_th)+"#";
                }

                String value_set_th="("+current_node_id+", "+float_th_str+", "+bool_th_str+")";
                String sql_th_insert = "INSERT INTO " + tb_name_th + " VALUES " + value_set_th;//插入
                stmt.executeUpdate(sql_th_insert);
                System.out.println(type+" 模型叶子节点 " + current_node_id + " 阈值存入数据库成功！");
            }

            // 完成后关闭
            rs.close();
            System.out.println(type+" 模型叶子节点阈值存储完毕！");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void node_threshold_builder(String file_addr, String type){
        node_threshold_builder(file_addr, type, DB_URL_res, USR, PSW);
    }

    public static void node_threshold_builder(String file_addr, String type,
                                            String database_url, String user, String psw){//从excel存入阈值至数据库中
        final String JDBC_DRIVER = DB_DRIVER;
        final String DB_URL = database_url;
        final String USER = user;
        final String PASS = psw;
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();

            Calendar c = Calendar.getInstance();
            Date time_now = c.getTime();
            int year_new = c.get(Calendar.YEAR);

            /*
            进行模型版本搜索
            */
            String tb_model_guide="eva_model_guide";
            String sql_model_ver="SELECT * FROM "+tb_model_guide+" WHERE type='"+type+"' ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs_model_ver=stmt.executeQuery(sql_model_ver);

            int model_ver_new = year_new *10000 +1;
            if(rs_model_ver.next()) {
                rs_model_ver.last();
                model_ver_new=rs_model_ver.getInt("version");//得到最新版本模型编号
            }

            rs_model_ver.close();

            /*
            进行阈值最新版本搜索
             */
            String tb_name_guide = "eva_node_threshold_guide";
            String tb_create = "CREATE TABLE IF NOT EXISTS " + tb_name_guide + " " +
                    "(time INT(11) NOT NULL, type VARCHAR(10) NOT NULL, " +
                    "model_version INT(11) NOT NULL, threshold_version INT(11) NOT NULL," +
                    "PRIMARY KEY(time, type))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(tb_create);//如果引导表不存在则先建表

            String conditions = "type= '" + type + "'";
            String sql = "SELECT * FROM " + tb_name_guide + " WHERE " + conditions + " ORDER BY time DESC LIMIT 1";//取最新记录
            ResultSet rs = stmt.executeQuery(sql);

            int version_model=0;
            int version_threshold = 0;
            int version_th_new=1;
            // 保存最后一条记录
            if (rs.next()) {
                // 保存最后一条记录
                rs.last();
                String tm = timeUtil.longToString(rs.getLong("time") * 1000, "yyyy-MM-dd HH:mm:ss");
                version_model = rs.getInt("model_version");//最新模型版本号
                version_threshold = rs.getInt("threshold_version");//最新模型版本号

                // 输出数据
                System.out.print("最新"+type+"评价模型节点阈值存储时间: " + tm);
                System.out.print(", 最新模型版本号: " + version_model);
                System.out.print(", 最新节点阈值版本号: " + version_threshold);
                System.out.print("\n");
            }
            //在引导表中添加记录
            int year_old=(int)version_threshold/10000;
            long tm_new = timeUtil.dateToLong(time_now) / 1000;
            version_th_new = year_old * 10000 + version_threshold % 10000 + 1;
            if (year_new!=year_old||version_model!=model_ver_new){
                version_th_new = year_new *10000 +1;
            }

            System.out.print("最新阈值表保存时间: " + tm_new);
            System.out.print(", 最新版本号: " + version_th_new);
            System.out.print("\n");
            //更新阈值引导表
            String value_set_guide = "(" + tm_new + ", '" + type + "', " + model_ver_new+","+version_th_new + ")";
            sql = "INSERT INTO " + tb_name_guide + " VALUES " + value_set_guide;
            stmt.executeUpdate(sql);

            /*
            存储阈值表格中数据
             */
            String tb_name_th = "eva_node_threshold_"+type+"_"+model_ver_new+"_"+version_th_new;
            String tb_create_th = "CREATE TABLE IF NOT EXISTS " + tb_name_th + " (" +
                    "id INT(11) NOT NULL, float_threshold VARCHAR(4000) , " +
                    "bool_threshold VARCHAR(4000) , PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            stmt.execute(tb_create_th);//如果表不存在则先建表

            //读取excel文件并进行存储
            List th_content = excelUtil.readExcel(file_addr, 0);//读取excel文件中内容
            for (int i = 1; i < th_content.size(); i++) {//跳过表头，从第二行开始读取
                List row_content = (List) th_content.get(i);
                String[] single_node_th=new String[3];
                int snt_i=0;
                for (int ii=0;ii<4;ii++){
                    if (ii!=1) {//不存储节点名
                        single_node_th[snt_i] = row_content.get(ii).toString();
                        snt_i++;
                    }
                }
                String sql_insert="INSERT INTO " + tb_name_th + " VALUES (" +
                        Integer.parseInt(single_node_th[0])+",'"+single_node_th[1]+"','"+single_node_th[2]+"')";
                stmt.executeUpdate(sql_insert);
             }

            // 完成后关闭
            rs.close();
            System.out.println(type+" 模型叶子节点阈值存储完毕！");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static Map<Integer, String[]> node_search_val(tree tr, String tree_type, Date[] time_gap) throws Exception {
        Map<Integer, String[]> res=new HashMap<>();
        if (!tree_type.equals("lea")||!tree_type.equals("air")){
            for (int i=0;i<4;i++){
                Map<Integer, String[]> unit_node_search_val=node_search_val(tr,time_gap,i+1);
                if (i==0){
                    res=unit_node_search_val;
                }
                else{
                    Set<Integer> keySet = res.keySet();
                    Iterator<Integer> keyIter = keySet.iterator();
                    while (keyIter.hasNext()) {//按顺序写入所有测点阈值的信息
                        Integer current_node = keyIter.next();//测点编号
                        String[] res_val_set=res.get(current_node);
                        String[] current_val_set=unit_node_search_val.get(current_node);
                        for (int i_stick=0;i_stick<res_val_set.length;i_stick++){
                            res_val_set[i_stick]=res_val_set[i_stick]+current_val_set[i_stick];
                        }
                        res.put(current_node,res_val_set);//键值对存入res中
                    }
                }
            }
        }
        else{
            res=node_search_val(tr, time_gap,1);
        }
        return res;
    }

    public static Map<Integer, String[]> node_search_val(tree tr,Date[] time_gap, int unit) throws Exception {
        Map<Integer, String[]> res=new HashMap<>();
        leafnode[] LN=tr.get_leaf_nodes();
        for (int i=0;i<LN.length;i++){//遍历每个叶子节点
            leafnode current_LN=LN[i];
            String[] save_str=new String[]{"",""};//分别存储当前节点的float型和bool型测点待评估值
            if (current_LN.get_state()==leafnode.state_online_mark) {
                String[][] points = current_LN.get_point_index().get(unit);//对unit机组的值进行搜索
                float v = 0;//存储计算所得值
                for (int ii = 0; ii < points.length; ii++) {//对每个点进行计算
                    String[] current_point = points[ii];
                    String point_type = current_point[1];
                    System.out.println("Calculate "+point_type+" point "+current_point[0]);

                    String[] point_descrip = current_point[0].split("\\|");

                    int save_str_type_id=0;//确定写入信息的String[]位置，save_str[0]，bool为第1个save_str[1]
                    if (point_type.equals("bool")){
                        save_str_type_id=1;
                    }
                    if (point_descrip.length > 1) {//排除为0的记录（即无对应测点的项目）
                        //整理时间类型
                        String time_descrip = point_descrip[0];
                        String time_type;
                        int[] time_unit_set;
                        if (time_descrip.length()==1){//如果时间类型是“F”，则直接写入
                            time_type=time_descrip;
                            time_unit_set=new int[]{0};
                        }
                        else {//其他时间类型需要进行相关处理
                            time_type = time_descrip.substring(0, time_descrip.indexOf("["));
                            String[] time_unit_set_str = time_descrip.substring(time_descrip.indexOf("[") + 1,
                                    time_descrip.indexOf("]")).split(",");
                            time_unit_set = new int[time_unit_set_str.length];
                            for (int tus_i = 0; tus_i < time_unit_set.length; tus_i++) {
                                time_unit_set[tus_i] = Integer.parseInt(time_unit_set_str[tus_i]);
                            }
                        }

                        //按不同计算方式进行相应计算
                        String cal_descrip = point_descrip[1];
                        int point_id;
                        char cal_type_head = cal_descrip.charAt(0);
                        switch (cal_type_head) {
                            case 'B'://基本类型
                                point_id = Integer.parseInt(point_descrip[2]);
                                List<float[]> B_data= search_func_B(time_gap, point_id,
                                        point_type, time_type, time_unit_set);
                                float[][] min_avg_max=new float[B_data.size()][3];
                                for (int ib=0;ib<B_data.size();ib++){
                                    float[] B_data_group_ib=B_data.get(ib);
                                    float B_data_group_ib_sum=0;
                                    for (int ib_i=0;ib_i<B_data_group_ib.length;ib_i++){
                                        if (ib_i==0){
                                            min_avg_max[ib]=new float[]{B_data_group_ib[0],
                                                    B_data_group_ib[0],B_data_group_ib[0]};
                                        }
                                        else{
                                            min_avg_max[ib][0]=Math.min(min_avg_max[ib][0],B_data_group_ib[ib_i]);
                                            min_avg_max[ib][2]=Math.max(min_avg_max[ib][2],B_data_group_ib[ib_i]);
                                        }
                                        B_data_group_ib_sum=B_data_group_ib_sum+B_data_group_ib[ib_i];
                                        if (ib_i==B_data_group_ib.length-1){
                                            min_avg_max[ib][1]=B_data_group_ib_sum/B_data_group_ib.length;
                                        }
                                    }
                                }
                                save_str[save_str_type_id]=node_search_val_str_adder(save_str[save_str_type_id],
                                        current_point[0],min_avg_max);
                                break;
                            case 'L':
                                point_id = Integer.parseInt(point_descrip[2]);
                                String per_or_all = "per";
                                if (cal_descrip.charAt(1) == 'A') {
                                    per_or_all = "all";
                                }
                                List<long[]> L_data = search_func_L(time_gap, point_id,
                                        per_or_all,time_unit_set, time_type);
                                min_avg_max=new float[L_data.size()][3];
                                for (int il=0;il<L_data.size();il++){
                                    long[] L_data_group_il=L_data.get(il);
                                    float L_data_group_il_sum=0;
                                    for (int il_i=0;il_i<L_data_group_il.length;il_i++){
                                        if (il_i==0){
                                            min_avg_max[il]=new float[]{L_data_group_il[0],
                                                    L_data_group_il[0],L_data_group_il[0]};
                                        }
                                        else{
                                            min_avg_max[il][0]=Math.min(min_avg_max[il][0],L_data_group_il[il_i]);
                                            min_avg_max[il][2]=Math.max(min_avg_max[il][2],L_data_group_il[il_i]);
                                        }
                                        L_data_group_il_sum=L_data_group_il_sum+L_data_group_il[il_i];
                                        if (il_i==L_data_group_il.length-1){
                                            min_avg_max[il][1]=L_data_group_il_sum/L_data_group_il.length;
                                        }
                                    }
                                }
                                save_str[save_str_type_id]=node_search_val_str_adder(save_str[save_str_type_id],
                                        current_point[0],min_avg_max);
                                break;
                            case 'D'://注意D这里存储的是固定窗口长内具有最大差值的（最小值点，最大值点）点对，而非差值
                                point_id = Integer.parseInt(point_descrip[2]);
                                long window = Long.parseLong(cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ));
                                int[] state_split_index=new int[]{0};
                                List<float[]> D_data = search_func_D(time_gap, window, point_id,
                                        time_type, time_unit_set,state_split_index);
                                save_str[save_str_type_id]=save_str[save_str_type_id]+"#"+current_point[0];
                                String[] D_str=new String[D_data.size()];
                                for (int id=0;id<D_data.size();id++){
                                    float[] D_data_group_id=D_data.get(id);
                                    for (int id_i=0;id_i<D_data_group_id.length;id_i++){
                                        if (id_i==0){
                                            D_str[id]="{"+D_data_group_id[id_i];
                                        }
                                        else{
                                            D_str[id]=D_str[id]+"；"+D_data_group_id[id_i];
                                            if (id_i==D_data_group_id.length-1){
                                                D_str[id]=D_str[id]+"}";
                                            }
                                        }
                                    }
                                    save_str[save_str_type_id]=save_str[save_str_type_id]+D_str[id];
                                }
                                break;
                            case 'G':
                                per_or_all = "per";
                                if (cal_descrip.charAt(1) == 'A') {
                                    per_or_all = "all";
                                }
                                int point_id_0 = Integer.parseInt(point_descrip[2]);
                                int point_id_1 = Integer.parseInt(cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ));
                                List<Long> G_data = search_func_G(time_gap, point_id_0, point_id_1, per_or_all);
                                save_str[save_str_type_id]=save_str[save_str_type_id]+"#"+current_point[0];
                                float[] m_a_M=new float[3];
                                for (int ig=0;ig<G_data.size();ig++){
                                    if (ig==0){
                                        m_a_M=new float[]{G_data.get(0),G_data.get(0),G_data.get(0)};
                                    }
                                    else {
                                        m_a_M[0] = Math.min(m_a_M[0],G_data.get(ig));
                                        m_a_M[2] = Math.max(m_a_M[2],G_data.get(ig));
                                        m_a_M[1] = m_a_M[1]+G_data.get(ig);
                                    }
                                }
                                m_a_M[1]=m_a_M[1]/G_data.size();
                                save_str[save_str_type_id]=save_str[save_str_type_id]+"{"+
                                        m_a_M[0]+"；"+m_a_M[1]+"；"+m_a_M[2]+"}";
                                break;
                            case 'C'://此处存储的值为所有观测测点在对应时段内的为1次数，并非每个区间的越限点数
                                save_str[save_str_type_id]=save_str[save_str_type_id]+"#"+current_point[0]+"{";
                                String[] points_id_set_str = cal_descrip.substring(
                                        cal_descrip.indexOf("[") + 1, cal_descrip.indexOf("]") ).split(",");
                                int[] points_id_set = new int[points_id_set_str.length];
                                for (int pis_i = 0; pis_i < points_id_set_str.length; pis_i++) {//对每个测点
                                    points_id_set[pis_i] = Integer.parseInt(points_id_set_str[pis_i]);
                                    List<float[]> C_data = search_func_B(time_gap, points_id_set[pis_i],
                                            "bool", time_type, time_unit_set);//该测点的值加和
                                    for (int ic=0;ic<C_data.size();ic++){
                                        save_str[save_str_type_id]=save_str[save_str_type_id]+C_data.get(ic)[0];
                                        if (ic==C_data.size()-1){
                                            save_str[save_str_type_id]=save_str[save_str_type_id]+"；";//不同测点间用分号隔开
                                        }
                                        else{
                                            save_str[save_str_type_id]=save_str[save_str_type_id]+",";//同测点不同时间类型间用逗号隔开
                                        }
                                    }
                                }
                                save_str[save_str_type_id]=save_str[save_str_type_id]+"}";
                                break;
                        }
                        current_LN.write_value(v);
                    }
                }
                System.out.println("online leafnode "+current_LN.get_name()+" search process finished! ");
            }
            res.put(current_LN.get_index(),save_str);
        }
        return res;
    }

    public static void node_search_val_output(tree tr, Map<Integer,String[]> search_res, String excel_addr){
        List<String[]> excel_input=new ArrayList<>();
        excel_input.add(new String[]{"节点号","节点名","单精量测点","状态量测点"});
        Set<Integer> keySet = search_res.keySet();
        Iterator<Integer> keyIter = keySet.iterator();
        while (keyIter.hasNext()) {//按顺序写入所有测点阈值的信息
            Integer current_node_id = keyIter.next();//测点编号
            leafnode current_node=tr.get_leaf_node_by_index(current_node_id);
            if (current_node.get_state()==leafnode.state_online_mark) {//仅输出在线点
                String current_node_name = current_node.get_name();
                String[] val_set = search_res.get(current_node_id);
                String[] node_all_set = new String[]{
                        current_node_id + "", current_node_name, val_set[0], val_set[1]};//组合excel需要的String[]
                excel_input.add(node_all_set);
            }
        }
        writeExcel(excel_input, 4, excel_addr);
    }

    public static String node_search_val_str_adder(String save_str, String current_point_descrip, float[][] min_avg_max){
        save_str=save_str+"#"+current_point_descrip;
        for (int ssi=0;ssi<min_avg_max.length;ssi++){
            save_str=save_str+"{";
            for (int ssi_i=0;ssi_i<min_avg_max[ssi].length;ssi_i++){
                save_str=save_str+min_avg_max[ssi][ssi_i];
                if (ssi_i<min_avg_max[ssi].length-1){
                    save_str=save_str+"；";
                }
            }
            save_str=save_str+"}";
        }
        return save_str;
    }
}
