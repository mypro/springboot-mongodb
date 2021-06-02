package org.xinhua.gk.util;

/**
 * @Classname DateFormatUtil
 * @Description TODO
 * @Date 2021/3/8 9:53
 * @Created by Chen Weichao
 */

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateFormatUtil {
    public static final String FOR_STRING = "yyyy-MM-dd HH:mm:ss";
    public static final String FOR_DAY = "MM/dd/yyyy";
    public static final String FOR_STR_YYYYMMDD = "yyyyMMdd";
    public static final String FOR_STRING3 = "";
    public static final String FOR_DAY_MM = "MM/dd/yyyy HH:mm";
    public static final String FOR_DAY_MM_SS = "MM/dd/yyyy HH:mm:ss";
    public static final String FOR_DAY_MM_00 = "MM/dd/yyyy 00:00";

    public DateFormatUtil() {
    }

    public static String toString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date toDate(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            if (!StringUtils.isEmpty(time)) {
                Date date = format.parse(time);
                return date;
            }
        } catch (ParseException var3) {
            ;
        }

        return null;
    }

    public static Date toDate(String time, String farmat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(farmat);
            if (!StringUtils.isEmpty(time)) {
                Date date = format.parse(time);
                return date;
            }
        } catch (ParseException var4) {
            ;
        }

        return null;
    }

    public static String toString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String toDayStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static Date dealDay(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, num);
        return calendar.getTime();
    }

    public static Date yesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static List<Map<String, Date>> getStatisDateList(Date start, Date end, String startTime, String endTime) {
        List<Map<String, Date>> list = new ArrayList();
        Date sTime = start;
        Date eTime = dealDay(start, 1);
        if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
            String startStr = toString(start, "MM/dd/yyyy") + " " + startTime + ":00";
            sTime = toDate(startStr, "MM/dd/yyyy HH:mm:ss");
            String endStr = toString(start, "MM/dd/yyyy") + " " + endTime + ":59";
            eTime = toDate(endStr, "MM/dd/yyyy HH:mm:ss");
            end = toDate(toString(end, "MM/dd/yyyy") + " " + endTime + ":59", "MM/dd/yyyy HH:mm:ss");
        } else {
            end = dealDay(end, 1);
        }

        new HashMap();

        while (end.after(sTime)) {
            Map<String, Date> map = new HashMap();
            map.put("start", sTime);
            map.put("end", eTime);
            list.add(map);
            sTime = dealDay(sTime, 1);
            eTime = dealDay(eTime, 1);
        }

        return list;
    }
}
