package org.ixkit.land.lang;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

/**
 * @class:DateTimes
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 15/07/2022
 * @version:0.1.0
 * @purpose:
 */
public class DateTimes {
    public static Date toDate(String strDate){
       String fmt = DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern();
        try {
            return DateUtils.parseDate(strDate,fmt);
        } catch (ParseException e) {
         //
            e.printStackTrace();
        }
        return null;
    }

    public static String now(){
       return  DateUtil.now();
    }

    public static long getTimestamp(){
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        return timeStampMillis;
    }
}
