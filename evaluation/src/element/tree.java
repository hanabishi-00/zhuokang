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
                    Map<Integer, int[][]> node_points = new HashMap<>();
                    String float_point = row_content.get(leaf_displacement + 8).toString();//单精量测点
                    String bool_point = row_content.get(leaf_displacement + 9).toString();//状态量测点
                    String[] FP_set = float_point.split("}");
                    String[] BP_set = bool_point.split("}");
                    if (FP_set.length == BP_set.length) {
                        for (int i_fp = 0; i_fp < FP_set.length; i_fp++) {
                            String unit_fP = FP_set[i_fp].substring(FP_set[i_fp].indexOf("{") + 1);
                            String unit_bp = BP_set[i_fp].substring(BP_set[i_fp].indexOf("{") + 1);
                            int[][] related_points = {{-1, -1}};//保存所有测点的编号和性质

                            for (String single_point : unit_fP.split("；")) {//存入单精量测点
                                if (!"-1".equals(single_point)) {//若存在有效测点编号
                                    if (related_points[0][0] == -1) {//代表数组刚初始化，直接写入
                                        related_points[0][0] = (int) Float.parseFloat(single_point);
                                        related_points[0][1] = leafnode.float_mark;
                                    } else {//增加一行
                                        int[][] temp_related_points = new int[related_points.length + 1][2];
                                        for (int k = 0; k < related_points.length; k++) {
                                            temp_related_points[k][0] = related_points[k][0];
                                            temp_related_points[k][1] = related_points[k][1];
                                        }
                                        temp_related_points[related_points.length][0] = (int) Float.parseFloat(single_point);
                                        temp_related_points[related_points.length][1] = leafnode.float_mark;
                                        related_points = temp_related_points;
                                    }
                                    System.out.println("叶子节点 " +
                                            (int) Float.parseFloat(row_content.get(leaf_displacement).toString()) +
                                            " 机组 " + (i_fp + 1) + "#" +
                                            " 单精量测点: " + (int) Float.parseFloat(single_point));
                                }
                            }

                            for (String single_point : unit_bp.split("；")) {//存入状态量测点
                                if (!"-1".equals(single_point)) {//若存在有效测点编号
                                    if (related_points[0][0] == -1) {//代表数组刚初始化，直接写入
                                        related_points[0][0] = (int) Float.parseFloat(single_point);
                                        related_points[0][1] = leafnode.bool_mark;
                                    } else {//增加一行
                                        int[][] temp_related_points = new int[related_points.length + 1][2];
                                        for (int k = 0; k < related_points.length; k++) {
                                            temp_related_points[k][0] = related_points[k][0];
                                            temp_related_points[k][1] = related_points[k][1];
                                        }
                                        temp_related_points[related_points.length][0] = (int) Float.parseFloat(single_point);
                                        temp_related_points[related_points.length][1] = leafnode.bool_mark;
                                        related_points = temp_related_points;
                                    }
                                    System.out.println("叶子节点 " +
                                            (int) Float.parseFloat(row_content.get(leaf_displacement).toString()) +
                                            " 状态量测点: " + (int) Float.parseFloat(single_point));
                                }
                            }

                            //录入测点Map中
                            node_points.put(i_fp + 1, related_points);
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

            /*String sql_leaf_row_number="SELECT TABLE_ROWS FROM information_schema.`TABLES`" +
                    "WHERE TABLE_NAME =  '"+tb_name_leaf+"' AND TABLE_SCHEMA ='result' LIMIT 1";
            ResultSet rs_leaf_ss = stmt.executeQuery(sql_leaf_row_number);
            rs_leaf_ss.next();
            int leaf_number=rs_leaf_ss.getInt("TABLE_ROWS");
            int group_unit=100;//以group_unit为单位进行分次搜索、写入（否则占用内存过大）
            int search_part=(int) leaf_number/group_unit;
            int search_rest=leaf_number%group_unit;
            rs_leaf_ss.close();*/

            /*String sql_test_leaf="SELECT * FROM eva_model_tur_20200001_leaf LIMIT 0,10";
            ResultSet test_leaf=stmt.executeQuery(sql_test_leaf);
            while(test_leaf.next()){
                System.out.print("id="+test_leaf.getInt("id")+" ");
                System.out.println("name="+test_leaf.getString("name"));
            }*/


            //for (int i_leaf=0;i_leaf<=search_part;i_leaf++) {//开始分次查询
                /*int start_row=i_leaf*group_unit;
                int group_row_number=group_unit;
                if (i_leaf==search_part){
                    group_row_number=search_rest;
                }
                String sql_leaf=sql_leaf_base+" LIMIT "+start_row+","+group_row_number;

                ResultSet rs_leaf = stmt.executeQuery(sql_leaf);*/

            ResultSet rs_leaf = stmt.executeQuery(sql_leaf_base);
            // 循环存入所有叶子节点信息
            int weight;
            boolean state;
            String[] stan_description;
            Map<Integer, int[][]> points;
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
            int year = c.get(Calendar.YEAR);
            long tm_new = timeUtil.dateToLong(time_now) / 1000;
            int version_new = year * 10000 + version % 10000 + 1;

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
                    "points VARCHAR(600) NOT NULL, PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
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
                Map<Integer, int[][]> CN_points_set = current_node.get_point_index();
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

    public static Map<String, Map<Integer, float[][]>> threshold_builder(String file_addr) {//由excel导入阈值表
        Map<Integer, float[][]> float_th = threshold_builder(file_addr, 0);
        Map<Integer, float[][]> bool_th = threshold_builder(file_addr, 1);
        Map<String, Map<Integer, float[][]>> res = new HashMap<>();
        res.put("float", float_th);
        res.put("bool", bool_th);
        return res;
    }

    public static Map<Integer, float[][]> threshold_builder(String file_addr, int sheet_id) {//由excel导入阈值表
        Map<Integer, float[][]> res = new HashMap<>();
        List th_content = excelUtil.readExcel(file_addr, sheet_id);//读取excel文件中内容
        for (int i = 1; i < th_content.size(); i++) {//跳过表头，从第二行开始读取
            List row_content = (List) th_content.get(i);

            int sz = (row_content.size() - 1) / 7;//需要创建的阈值保存数组的行数（单精量有2行，分别保存抽水和发电工况）
            float[][] point_threshold_value = new float[sz][7];
            for (int j = 0; j < sz; j++) {
                for (int jj = 0; jj < 7; jj++) {
                    point_threshold_value[j][jj] = Float.parseFloat(row_content.get(j * 7 + jj + 1).toString());
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

    public static Map<String, Map<Integer, float[][]>> threshold_reader(tree model) {//直接用此函数即可读取数据库中存储的阈值
        Map<Integer, float[][]> float_th = threshold_reader("float", model);
        Map<Integer, float[][]> bool_th = threshold_reader("bool", model);
        Map<String, Map<Integer, float[][]>> res = new HashMap<>();
        res.put("float", float_th);
        res.put("bool", bool_th);
        return res;
    }

    public static Map<Integer, float[][]> threshold_reader(String data_type, tree model) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        Map<Integer, float[][]> res = threshold_reader(data_type, model, database_url, user, psw);
        return res;
    }

    public static Map<Integer, float[][]> threshold_reader(String data_type, tree model, String database_url, String user, String psw) {//type为float或bool
        Map<Integer, float[][]> res = new HashMap<>();//float[][]分别存储抽水态和发电态的阈值
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

            /*
            进行阈值最新版本搜索
             */

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

            /*
            按树中叶子节点涉及的该类测点进行评价阈值存储
             */
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
                                float[][] threshold_values = operation_threshold(rs.getString("threshold"));
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

    public Map<String, Map<Integer, float[][]>> threshold_reader(int leaf_node_ind, String database_url, String user, String psw) {//按叶子节点查询其包含的测点的阈值
        Map<Integer, float[][]> res_float = new HashMap<>();//float[]分别存储抽水态和发电态的阈值
        Map<Integer, float[][]> res_bool = new HashMap<>();//float[]分别存储抽水态和发电态的阈值
        Map<String, Map<Integer, float[][]>> res = new HashMap<>();

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

            /*
            进行阈值最新版本搜索
             */

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

            /*
            按树中叶子节点涉及的该类测点进行评价阈值存储
             */

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
                        float[][] threshold_values = operation_threshold(rs.getString("threshold"));
                        res_float.put(point_id, threshold_values);
                    } else if ((unit_points[jj][0] != -1) && (unit_points[jj][1] == leafnode.bool_mark)) {
                        tb_name_query = "eva_threshold_" + type[1] + "_" + version_bool;
                        condition = "id=" + unit_points[jj][0];
                        sql_th_query = "SELECT * FROM " + tb_name_query + " WHERE " + condition;

                        rs = stmt.executeQuery(sql_th_query);
                        Integer point_id = rs.getInt("id");
                        float[][] threshold_values = operation_threshold(rs.getString("threshold"));
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
    }

    public static void threshold_saver(Map<String, Map<Integer, float[][]>> threshold_map) {
        threshold_saver(threshold_map.get("float"), "float");
        threshold_saver(threshold_map.get("bool"), "bool");
    }

    public static void threshold_saver(Map<Integer, float[][]> threshold_map, String type) {
        final String database_url = DB_URL_res;
        final String user = USR;
        final String psw = PSW;
        threshold_saver(threshold_map, type, database_url, user, psw);
    }

    public static void threshold_saver(Map<Integer, float[][]> threshold_map, String type, String database_url, String user, String psw) {
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
                int year = c.get(Calendar.YEAR);
                long tm_new = timeUtil.dateToLong(time_now) / 1000;
                int version_new = year * 10000 + version % 10000 + 1;

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

    public void add_node(int ind, String nam, int fa, int w, String[] st_des, Map<Integer, int[][]> p_ind) {//新增在线叶子节点
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

    public void edit_node(int ind, String nam, int fa, int w, String[] st_des, Map<Integer, int[][]> p_ind) {//修改在线叶子节点
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
    public static tree cal(tree tr, Date[] time, int unit, String type) throws Exception {
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
    }

    public static void mid_nodes_updater(tree tr, int fa, float val) {//中间节点更新函数
        if (fa != -1) {
            tr.get_mid_node_by_index(fa).write_value(val);
            mid_nodes_updater(tr, tr.get_mid_node_by_index(fa).get_father(), tr.get_mid_node_by_index(fa).get_value());
        }
    }

    public static tree cal_leafnodes(tree tr, Date[] time, int unit, String type, String database_url, String user, String psw) throws Exception {//计算叶子节点分值
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
        Map<String, Map<Integer, float[][]>> all_th = threshold_reader(tr);

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
    }

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
        Map<Integer, int[][]> points;
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
            sql_mid_res_table_build=sql_mid_res_table_build+", PRIMARY KEY (time_online, time_offline, unit));";
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

    public tree cal_online( tree tr, Date[] time, int unit) throws Exception {//仅计算在线点的分值
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
        Map<String, Map<Integer, float[][]>> all_th=threshold_reader(tr);

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

    public static float cal_online_single(leafnode LN, int unit, Map<String, Map<Integer,float[]>> val, Map<String, Map<Integer, float[][]>> all_th) throws Exception {
        /*计算单点的值*/
        float res=0;
        if (LN.get_state()==leafnode.state_online_mark){//在线节点
            Map<Integer, int[][]> LN_points_set=LN.get_point_index();//得到节点的测点集
            int[][] LN_points=LN_points_set.get(unit);
            if ((val.get("bool").size()+Math.max(val.get("float_turbine").size(),val.get("float_pump").size()))==LN_points.length) {
                for (int i = 0; i < LN_points.length; i++) {//对第i个测点
                    if (LN_points[i][0] != -1) {//有测点
                        float[][] threshold = null;
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
    }

    public static float th_cal(float val, float[] th) throws Exception {//直接输入阈值数组计算基本扣分值
        float res=0;
        if (th.length!=7){
            throw new Exception("定义阈值数目异常！");
        }
        else{//依据其所处位置分别打分
            if (val<th[1]){
                res=range_cal(val,new float[]{th[0],th[1]},1,3);
            }
            else if (val>th[5]){
                res=range_cal(val,new float[]{th[5],th[6]},-1,3);
            }
            else if (val<th[2]){
                res=range_cal(val,new float[]{th[1],th[2]},1,2);
            }
            else if (val>th[4]){
                res=range_cal(val, new float[]{th[4],th[5]},-1,2);
            }
            else if (val<th[3]){
                res=range_cal(val,new float[]{th[2],th[3]},1,1);
            }
            else if (val>=th[3]){
                res=range_cal(val,new float[]{th[3],th[4]},-1,1);
            }
        }
        return res;
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

    public static Map<Integer, Map<String, Map<Integer, float[]>>> id_min_avg_max( Map<String, Date[][]> time_gap, tree eva_tree) throws ParseException {//求某时段内所有在线叶子节点相关单精量测点的最小值、平均值和最大值
        /*time_gap存储总时间和稳态时间（total/turbine/pump）三种条件下的查询时段，分别用于查询状态量和单精量*/

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
        /*time_gap存储总时间和稳态时间（total/turbine/pump）三种条件下的查询时段，分别用于查询状态量和单精量*/

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
    }

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

    public static Map<Integer, int[][]> operation_points(String src_str){//按数据库中内容存入树叶子结点的关联测点中
        Map<Integer, int[][]> map_res=new HashMap<>();
        int[][] res=null;
        if (!src_str.isEmpty()){
            String[] p_set=src_str.split("}");
            for (int j=0; j<p_set.length; j++) {//对每台机组的数据进行读取
                String unit_p=p_set[j].substring(p_set[j].indexOf("{")+1);
                List<int[]> res_list = new ArrayList<>();

                String[] PPS_set = unit_p.split("；");
                for (int jj=0;jj<PPS_set.length;jj++) {
                    String[] P=PPS_set[jj].split("，");
                    res_list.add(new int[]{Integer.parseInt(P[0]), Integer.parseInt(P[1])});
                }

                int num = res_list.size();
                res = new int[num][];
                for (int i = 0; i < num; i++) {
                    res[i] = res_list.get(i);
                }

                map_res.put(j+1,res);
            }
        }
        return map_res;
    }

    public static String operation_points(Map<Integer, int[][]> node_points){//按照{}划分机组，按照point_id,type；的形式划分测点及类型
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

    public static float[][] operation_threshold(String src_str){//完成由数据库中存储的String类型数据向float[][]形的转换
        float[][] res=null;
        if (!src_str.isEmpty()){
            List<float[]> res_list=new ArrayList<>();
            int[] cursor=new int[]{src_str.indexOf('{'),src_str.indexOf('}')};
            String per_th_str=src_str.substring(cursor[0]+1,cursor[1]-1);
            String[] PTS_set=per_th_str.split("；");
            float[]  per_th=new float[PTS_set.length];
            for (int i=0;i<PTS_set.length;i++){
                per_th[i]=Float.parseFloat(PTS_set[i]);
            }
            res_list.add(per_th);
            while(cursor[1]<src_str.lastIndexOf('}')){//按{}对内容进行划分存储
                cursor[0]= src_str.indexOf('{', cursor[1]);
                cursor[1]= src_str.indexOf('}', cursor[0]);
                per_th_str=src_str.substring(cursor[0]+1,cursor[1]);
                PTS_set=per_th_str.split("；");

                per_th=new float[PTS_set.length];
                for (int i=0;i<PTS_set.length;i++){
                    per_th[i]=Float.parseFloat(PTS_set[i]);
                }

                res_list.add(per_th);
                cursor[1]++;
            }

            int num=res_list.size();
            res=new float[num][];
            for (int i=0;i<num;i++){
                res[i]=res_list.get(i);
            }
        }
        return res;
    }

    public static String operation_threshold(float[][] points_th){
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
}
