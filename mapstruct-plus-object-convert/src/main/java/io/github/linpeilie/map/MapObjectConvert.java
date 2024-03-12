package io.github.linpeilie.map;

import cn.hutool.core.convert.Convert;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

public class MapObjectConvert {

    public static String objToString(Object obj) {
        return Convert.toStr(obj);
    }

    public static BigDecimal objToBigDecimal(Object obj) {
        return Convert.toBigDecimal(obj);
    }

    public static BigInteger objToBigInteger(Object obj) {
        return Convert.toBigInteger(obj);
    }

    public static Integer objToInteger(Object obj) {
        return Convert.toInt(obj);
    }

    public static Long objToLong(Object obj) {
        return Convert.toLong(obj);
    }

    public static Double objToDouble(Object obj) {
        return Convert.toDouble(obj);
    }

    public static Boolean objToBoolean(Object obj) {
        return Convert.toBool(obj);
    }

    public static Date objToDate(Object obj) {
        return Convert.toDate(obj);
    }

    public static LocalDateTime objToLocalDateTime(Object obj) {
        return Convert.toLocalDateTime(obj);
    }

    public static LocalDate objToLocalDate(Object obj) {
        return Convert.convert(LocalDate.class, obj);
    }

    public static LocalTime objToLocalTime(Object obj) {
        return Convert.convert(LocalTime.class, obj);
    }

    public static URI objToUri(Object obj) {
        return Convert.convert(URI.class, obj);
    }

    public static URL objToUrl(Object obj) {
        return Convert.convert(URL.class, obj);
    }

    public static Calendar objToCalendar(Object obj) {
        return Convert.convert(Calendar.class, obj);
    }

    public static Currency objToCurrency(Object obj) {
        return Convert.convert(Currency.class, obj);
    }

}
