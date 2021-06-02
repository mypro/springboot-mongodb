package org.xinhua.gk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String FOR_STR_YYYYMMDD = "yyyyMMdd";
    public static final String FOR_STR_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FOR_STR_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] arg) {
        String s = "2019年12月16日、2019年12月17日、2019年12月18日";
        String[] ss = s.split("、");
        for (String sss : ss) {
            System.out.println(sss);
        }
    }

    public static Date DoubleToDate(Double dVal) {
        Date oDate = new Date();
        Calendar c = Calendar.getInstance();
        oDate.setTime((long) (dVal * 24 * 3600 * 1000 - c.get(Calendar.ZONE_OFFSET)));
        return oDate;
    }

    public static Date longToDate(long val) {
        Date oDate = new Date();
        oDate.setTime(val);
        return oDate;
    }

    public static Date transferDate(String dateStr, String fmt) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        if (fmt == null || fmt.equals("")) {
            fmt = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            if (logger.isDebugEnabled()) {
                logger.error("DateUtil.transferDate ERR dateStr:{},fmt:{}", dateStr, fmt);
            }
        }
        return date;
    }

    public static String transferDate(Date date, String fmt) {
        if (date == null) {
            return null;
        }
        if (fmt == null || fmt.equals("")) {
            fmt = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        String dateStr = null;
        try {
            dateStr = format.format(date);
        } catch (Exception e) {
            e.getMessage();
        }
        return dateStr;
    }

    /**
     * 指定时间加指定天数
     *
     * @param step
     * @param date
     * @return
     */
    public static Date dateAddition(int step, Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, step);
        return ca.getTime();
    }

    /**
     * 两个时间差年、月、日
     *
     * @param fromTime
     * @param toTime
     * @return
     */
    public static Map<String, Integer> timeDifferYearMonthDay(Date fromTime, Date toTime) {

        LocalDate from = fromTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate to = toTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period pe = Period.between(from, to);

        Map<String, Integer> differMap = new HashMap<>();
        differMap.put("year", Integer.valueOf(pe.getYears()));
        differMap.put("month", Integer.valueOf(pe.getMonths()));
        differMap.put("day", Integer.valueOf(pe.getDays()));

        return differMap;
    }

    public static String transferDate(Long times, String fmt) {
        if (times == null) {
            return null;
        }
        if (fmt == null || fmt.equals("")) {
            fmt = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        String dateStr = null;
        try {
            Date date = new Date(times);
            dateStr = format.format(date);
        } catch (Exception e) {
            logger.error("DateUtil.transferDate ERR times:{},fmt:{}", times, fmt);
        }
        return dateStr;
    }


}
