package element;

import java.util.Map;

public class leafnode extends node{
    private String[] standard_description;
    private Map<Integer, String[][]> point_index=null;//Integer为机组号，String[i][0]中存第i个测点及计算规则，String[i][1]中存类型
    public static final int float_mark=0;
    public static final int bool_mark=1;
    private boolean state;//在线量为true，离线量为false
    public static final boolean state_online_mark=true;
    public static final boolean state_offline_mark=false;

    public leafnode(){

    }

    public leafnode(int ind, String nam, int fa, int w, String[] st_des, Map<Integer, String[][]> p_ind){//创建在线叶子节点
        super( ind, nam, fa, w);
        /*index=ind;
        name=nam;
        father=fa;
        value=0;
        weight=w;*/
        state=state_online_mark;
        standard_description=st_des;
        point_index=p_ind;
    }

    public leafnode(int ind, String nam, int fa, int w, String[] st_des){//创建离线叶子节点
        super( ind, nam, fa, w);
        /*index=ind;
        name=nam;
        father=fa;
        weight=w;
        value=0;*/
        state=state_offline_mark;
        standard_description=st_des;
    }

    public boolean get_state(){
        return state;
    }

    public Map<Integer, String[][]> get_point_index(){
        return point_index;
    }

    public String[] get_standard_description(){
        return standard_description;
    }
}