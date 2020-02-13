package util;

import element.tree;
import hdb.BoolDataListHolder;
import hdb.FloatDataListHolder;
import hdb.Hdb;
import hdb.InterpolationLine;

import java.text.ParseException;
import java.util.*;
import java.util.Date;

public class hdbUtil {

    // 数据库 URL及接口
    static final String HDB_URL="127.0.0.1";
    static final int HDB_PORT=22135;

    // 数据库的用户名与密码
    static final String HDB_USER = "root";
    static final String HDB_PASS = "root1234";

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
        Date d2=timeUtil.stringToDate("2017-12-29 23:59:59","yyyy-MM-dd HH:mm:ss");

        Date[] time_d={d1,d2};
        Date[][] steady_turbine=steadyTime(time_d,1, tree.work_condition_turbine);
        Date[][] steady_pump=steadyTime(time_d,1, tree.work_condition_pump);
        //List<resBool> rb=searchBool(time_d,822);
        List<resFloat> rf=searchFloat(time_d,50);
        for (int f_i=0;f_i<rf.size();f_i++) {
            System.out.println("record "+f_i+": " + rf.get(f_i).getTime() + " " + rf.get(f_i).getValue() + " " + rf.get(f_i).getFlag());
        }

    }

    public static List searchFloat(Date[] time_gap, int id){//查询数据库中单精量
        List<resFloat> resList=new ArrayList<>();

        //连接Hdb数据库
        Hdb hdb = new Hdb();
        int ret = hdb.open(HDB_URL, HDB_PORT, HDB_USER, HDB_PASS);
        boolean opened = hdb.isOpened();

        /*System.out.println("ret="+ret);
        System.out.println("isOpened="+opened);*/

        String[] t1=timeUtil.getYMDHMS(time_gap[0]);
        String[] t2=timeUtil.getYMDHMS(time_gap[1]);

        int[] t1_val=new int[6];
        int[] t2_val=new int[6];

        for (int i_t=0;i_t<6;i_t++){
            t1_val[i_t]=Integer.parseInt(t1[i_t]);
            t2_val[i_t]=Integer.parseInt(t2[i_t]);
        }

        FloatDataListHolder dataList = new FloatDataListHolder();
        ret = hdb.readFloatHisDataById(id,
                hdb.toUTC(t1_val[0], t1_val[1], t1_val[2], t1_val[3], t1_val[4], t1_val[5]),
                hdb.toUTC(t2_val[0], t2_val[1], t2_val[2], t2_val[3], t2_val[4], t2_val[5]),
                0, InterpolationLine.value, dataList);
//        System.out.println("ret="+ret);

        //存储历史数据
        for(int i=0; i< dataList.value.length; i++){
            long tm  = dataList.value[i].tm;
            float value = dataList.value[i].val;
            int flag = dataList.value[i].flag;
            resFloat rf=new resFloat(tm,value,flag);
            resList.add(rf);

            /*//输出
            System.out.print("单精量测点: " + id);
            System.out.print(", 时间: " + tm);
            System.out.print(", 测点值: " + value);
            System.out.print(", 标志: " + flag);
            System.out.print("\n");*/
        }

//        System.out.println("Goodbye!");
        return resList;
    }

    public static List searchBool(Date[] time_gap, int id) throws ParseException {//查询数据库中状态量
        List<resBool> resList=new ArrayList<>();

        //连接Hdb数据库
        Hdb hdb = new Hdb();
        int ret = hdb.open(HDB_URL, HDB_PORT, HDB_USER, HDB_PASS);
        boolean opened = hdb.isOpened();

/*        System.out.println("ret="+ret);
        System.out.println("isOpened="+opened);*/

        String[] t1=timeUtil.getYMDHMS(time_gap[0]);
        String[] t2=timeUtil.getYMDHMS(time_gap[1]);

        int[] t1_val=new int[6];
        int[] t2_val=new int[6];

        for (int i_t=0;i_t<6;i_t++){
            t1_val[i_t]=Integer.parseInt(t1[i_t]);
            t2_val[i_t]=Integer.parseInt(t2[i_t]);
        }

        long t1_second=timeUtil.dateToLong(time_gap[0])/1000;
        long t2_second=timeUtil.dateToLong(time_gap[1])/1000;

        BoolDataListHolder dataList = new BoolDataListHolder();
        ret = hdb.readBoolHisDataById(id,
                hdb.toUTC(t1_val[0], t1_val[1], t1_val[2], t1_val[3], t1_val[4], t1_val[5]),
                hdb.toUTC(t2_val[0], t2_val[1], t2_val[2], t2_val[3], t2_val[4], t2_val[5]),
                0, dataList);
//        System.out.println("ret="+ret);

        //对查询结果进行处理（原函数仅查询原值，须对查询时段首末时刻进行插值）
        int origin_data_len=dataList.value.length;//原时段查询得到的原值数
        if (  origin_data_len > 0 ){//如果有查询结果
            if (dataList.value[0].tm<t1_second){//如果首实测值时刻小于开始查询时间，向前查询一天
                Date[] time_gap_sub={timeUtil.longToDate(1000*(t1_second-24*3600-1),"yyyy-MM-dd HH:mm:ss"),
                        timeUtil.longToDate(1000*(t1_second-1),"yyyy-MM-dd HH:mm:ss")};
                BoolDataListHolder dataList_sub = searchBoolForward(id, time_gap_sub);
                int first_value=dataList_sub.value.length;
                long tm_sub  = t1_second;
                int value_sub = dataList.value[first_value-1].val;//取查询到的最接近查询起始时刻的值
                int flag_sub = 3;

                resBool rf = new resBool(tm_sub, value_sub, flag_sub);
                resList.add(rf);
            }

            //存储历史数据
            for(int i=0; i< dataList.value.length; i++){
                long tm  = dataList.value[i].tm;
                int value = dataList.value[i].val;
                int flag = dataList.value[i].flag;
                if ((i==0)||(i==dataList.value.length-1)||(flag!=3)) {//如果是首末时刻值或首末时刻间原值，则存储
                    resBool rf = new resBool(tm, value, flag);
                    resList.add(rf);

                /*//输出
                System.out.print("状态量测点: " + id);
                System.out.print(", 时间: " + tm);
                System.out.print(", 测点值: " + value);
                System.out.print(", 标志: " + flag);
                System.out.print("\n");*/
                }
            }

            //添加查询末时刻数据
            if (dataList.value[origin_data_len-1].tm<t2_second){//若时段内查询到的最末原值对应时刻在查询结束时刻前
                long tm_sub  = t2_second;
                int value_sub = dataList.value[origin_data_len-1].val;//取最末原值
                int flag_sub = 3;

                resBool rf = new resBool(tm_sub, value_sub, flag_sub);
                resList.add(rf);
            }
        }
        else{//无原值可被查询
            Date[] time_gap_sub={timeUtil.longToDate(1000*(t1_second-24*3600-1),"yyyy-MM-dd HH:mm:ss"),
                    timeUtil.longToDate(1000*(t1_second-1),"yyyy-MM-dd HH:mm:ss")};
            BoolDataListHolder dataList_sub = searchBoolForward(id, time_gap_sub);
            int first_value=dataList_sub.value.length;
            int value_sub = dataList.value[first_value-1].val;//取查询到的最接近查询起始时刻的值
            int flag_sub = 3;

            //存入首末时刻值
            resBool rf_1 = new resBool(t1_second, value_sub, flag_sub);
            resBool rf_2 = new resBool(t2_second, value_sub, flag_sub);
            resList.add(rf_1);
            resList.add(rf_2);
        }

//        System.out.println("Goodbye!");
        return resList;
    }

    public static BoolDataListHolder searchBoolForward(int id, Date[] time_gap) throws ParseException {//从当前时间开始按天向前搜索直至查询得到结果
        int day_second=3600*24;

        //连接Hdb数据库
        Hdb hdb = new Hdb();
        int ret = hdb.open(HDB_URL, HDB_PORT, HDB_USER, HDB_PASS);
        boolean opened = hdb.isOpened();

/*        System.out.println("ret="+ret);
        System.out.println("isOpened="+opened);*/

        String[] t1=timeUtil.getYMDHMS(time_gap[0]);
        String[] t2=timeUtil.getYMDHMS(time_gap[1]);
        int[] t1_val=new int[6];
        int[] t2_val=new int[6];
        for (int i_t=0;i_t<6;i_t++){
            t1_val[i_t]=Integer.parseInt(t1[i_t]);
            t2_val[i_t]=Integer.parseInt(t2[i_t]);
        }
        long t1_second=timeUtil.dateToLong(time_gap[0])/1000;
        long t2_second=timeUtil.dateToLong(time_gap[1])/1000;

        BoolDataListHolder dataList = new BoolDataListHolder();
        ret = hdb.readBoolHisDataById(id,
                hdb.toUTC(t1_val[0], t1_val[1], t1_val[2], t1_val[3], t1_val[4], t1_val[5]),
                hdb.toUTC(t2_val[0], t2_val[1], t2_val[2], t2_val[3], t2_val[4], t2_val[5]),
                0, dataList);
//        System.out.println("ret="+ret);

        if (dataList.value.length == 0) {//向前推一天后查询
            Date[] time_gap_sub={timeUtil.longToDate(1000*(t1_second-day_second-1),"yyyy-MM-dd HH:mm:ss"),
                    timeUtil.longToDate(1000*(t1_second-1),"yyyy-MM-dd HH:mm:ss")};

            dataList=searchBoolForward(id, time_gap_sub);
        }

        return dataList;
    }

    public static Date[][] steadyTimeSimple(Date[] time_gap, int id, int steadyThreshold) throws ParseException {//查询发电态、抽水态的稳态时段（设置达到稳态的时间为开机后steadyThreshold秒）
        //作为steadyTime的子函数使用
        Date[][] passage = null;//每行存储一对符合要求的稳态工况起始时段
        long gap=(timeUtil.dateToLong(time_gap[1])-timeUtil.dateToLong(time_gap[0]))/1000;
        if (gap>steadyThreshold) {//如果查询时间>threshold
            List<resBool> origin_data = searchBool(time_gap, id);//存储time_gap内的id测点值
            int total_len = origin_data.size();

            for (int i = 0; i < total_len; i++) {
                long[] per_passage_long = new long[2];
                long[] neighbor_time_gap = new long[2];//相邻两跳变时刻（为1的时段）

                for (int j1 = i; j1 < total_len - 1; j1++) {
                    if ((j1 == 0) && (origin_data.get(j1).getValue() == 1)) {//如果首位为1
                        neighbor_time_gap[0] = origin_data.get(j1).getTime();//直接存储
                        i = j1;
                        break;
                    } else if ((origin_data.get(j1).getValue() == 0) &&
                            (origin_data.get(j1 + 1).getValue() == 1)) {//上升沿
                        neighbor_time_gap[0] = origin_data.get(j1 + 1).getTime();//前一时刻
                        i = j1 + 1;
                        break;
                    }
                }

                for (int j2 = i; j2 < total_len; j2++) {
                    if (j2 < total_len - 1) {//未到最后一位
                        if ((origin_data.get(j2).getValue() == 1) &&
                                (origin_data.get(j2 + 1).getValue() == 0)) {//下降沿
                            neighbor_time_gap[1] = origin_data.get(j2 + 1).getTime();//后一时刻
                            i = j2 ;
                            break;
                        }
                    } else if (origin_data.get(j2).getValue() == 1) {//到最后一位且为1，直接存储
                        neighbor_time_gap[1] = origin_data.get(j2).getTime();//后一时刻
                    }
                }

                if (neighbor_time_gap != null) {
                    if ((neighbor_time_gap[1] - neighbor_time_gap[0]) > steadyThreshold) {
                        //如果前一上升沿时间与后一个下降沿时间差大于达稳态时间阈值
                        per_passage_long[0] = neighbor_time_gap[0] + steadyThreshold;
                        per_passage_long[1] = neighbor_time_gap[1];

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
        }

        return passage;
    }

    //计算在某时间间隔内某号机组的稳态工作时段
    public static Date[][] steadyTime(Date[] time_gap, int unit_id, int type, int steadyThreshold) throws ParseException {
        Date[][] res_time_psg = new Date[0][];
        int[] state_point_id;
        //首先定义各机组的状态测点
        switch (type){//判断查询状态
            case tree.work_condition_pump://抽水工况
                state_point_id=new int[]{826, 2740, 4654, 6568};//各机组抽水态id
                int unit_choushui_id=state_point_id[unit_id-1];
                res_time_psg=steadyTimeSimple(time_gap, unit_choushui_id,steadyThreshold);
                break;
            default://非-1表示正功率，发电，受其他机组启停的影响
                state_point_id=new int[]{822, 2736, 4650, 6564};//各机组发电态id
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
                    Date d1=timeUtil.longToDate(CUTP_long_list.get(rr)[0]*1000, "yyyy-MM-dd HH:mm:ss");
                    Date d2=timeUtil.longToDate(CUTP_long_list.get(rr)[1]*1000, "yyyy-MM-dd HH:mm:ss");
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

