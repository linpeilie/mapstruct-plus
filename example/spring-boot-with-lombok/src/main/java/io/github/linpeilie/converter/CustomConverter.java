package io.github.linpeilie.converter;

import io.github.linpeilie.map.MapObjectConverter;
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

/**
 * 自定义转换器 —— 不依赖 hutool，验证 @AutoMapMapper(use = ...) 生效。
 * <p>
 * String 转换添加 {@code [custom]} 前缀以便测试区分。
 *
 * @since 1.5.2
 */
public class CustomConverter implements MapObjectConverter {

    @Override
    public String objToString(Object obj) {
        return obj == null ? null : "[custom]" + obj;
    }

    @Override
    public BigDecimal objToBigDecimal(Object obj) {
        return obj == null ? null : new BigDecimal(obj.toString());
    }

    @Override
    public BigInteger objToBigInteger(Object obj) {
        return obj == null ? null : new BigInteger(obj.toString());
    }

    @Override
    public Integer objToInteger(Object obj) {
        return obj == null ? null : Integer.valueOf(obj.toString());
    }

    @Override
    public Long objToLong(Object obj) {
        return obj == null ? null : Long.valueOf(obj.toString());
    }

    @Override
    public Double objToDouble(Object obj) {
        return obj == null ? null : Double.valueOf(obj.toString());
    }

    @Override
    public Boolean objToBoolean(Object obj) {
        return obj == null ? null : Boolean.valueOf(obj.toString());
    }

    @Override
    public Date objToDate(Object obj) {
        return obj == null ? null : (Date) obj;
    }

    @Override
    public LocalDateTime objToLocalDateTime(Object obj) {
        return obj == null ? null : LocalDateTime.parse(obj.toString());
    }

    @Override
    public LocalDate objToLocalDate(Object obj) {
        return obj == null ? null : LocalDate.parse(obj.toString());
    }

    @Override
    public LocalTime objToLocalTime(Object obj) {
        return obj == null ? null : LocalTime.parse(obj.toString());
    }

    @Override
    public URI objToUri(Object obj) {
        return obj == null ? null : URI.create(obj.toString());
    }

    @Override
    public URL objToUrl(Object obj) {
        try {
            return obj == null ? null : new URL(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Calendar objToCalendar(Object obj) {
        return obj == null ? null : (Calendar) obj;
    }

    @Override
    public Currency objToCurrency(Object obj) {
        return obj == null ? null : Currency.getInstance(obj.toString());
    }

}
