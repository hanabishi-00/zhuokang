package main.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static void main(String[] args) throws ParseException {
        long long_time=stringToLong("2018-06-26 20:04:01","yyyy-MM-dd HH:mm:ss");
        long xxy = long_time/1000;
//       System.out.println(xxy instanceof Long);

    }


    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型  qzzzzzz
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static String[] getYMDHMS(Date date){//以String格式返回年月日小时分钟秒
        String[] res=new String[6];
        String time;
        time=TimeUtils.dateToString(date,"yyyy-MM-dd HH:mm:ss");
        res[0]=time.substring(0,time.indexOf("-"));//存入年份
        res[1]=time.substring(time.indexOf("-")+1,time.lastIndexOf("-"));//存入月份
        res[2]=time.substring(time.lastIndexOf("-")+1, time.indexOf(" "));//存入日
        res[3]=time.substring(time.indexOf(" ")+1, time.indexOf(":"));//存入小时
        res[4]=time.substring(time.indexOf(":")+1, time.lastIndexOf(":"));//存入分钟
        res[5]=time.substring(time.lastIndexOf(":")+1);//存入秒
        return res;
    }
}
