package org.xinhua.gk.util;

import java.text.DecimalFormat;

/**
 * 类型转换工具类
 */
public class DataFormatUtil {

    public static Double dataFormat(Double d, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return Double.valueOf(df.format(d));
    }
}
