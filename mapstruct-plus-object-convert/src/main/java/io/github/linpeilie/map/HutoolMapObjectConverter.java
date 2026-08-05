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

/**
 * 基于 <a href="https://hutool.cn">hutool</a> {@link Convert} 的默认转换器实现。
 * <p>
 * 使用此实现需引入 {@code hutool-core} 依赖（scope: provided/optional）。
 * 若不想依赖 hutool，可实现 {@link MapObjectConverter} 并通过
 * {@code @AutoMapMapper(use = MyConverter.class)} 或
 * {@code @MapperConfig(mapObjectConverter = MyConverter.class)} 指定。
 *
 * @since 1.5.2
 */
public class HutoolMapObjectConverter implements MapObjectConverter {

    @Override
    public String objToString(Object obj) {
        return Convert.toStr(obj);
    }

    @Override
    public BigDecimal objToBigDecimal(Object obj) {
        return Convert.toBigDecimal(obj);
    }

    @Override
    public BigInteger objToBigInteger(Object obj) {
        return Convert.toBigInteger(obj);
    }

    @Override
    public Integer objToInteger(Object obj) {
        return Convert.toInt(obj);
    }

    @Override
    public Long objToLong(Object obj) {
        return Convert.toLong(obj);
    }

    @Override
    public Double objToDouble(Object obj) {
        return Convert.toDouble(obj);
    }

    @Override
    public Boolean objToBoolean(Object obj) {
        return Convert.toBool(obj);
    }

    @Override
    public Date objToDate(Object obj) {
        return Convert.toDate(obj);
    }

    @Override
    public LocalDateTime objToLocalDateTime(Object obj) {
        return Convert.toLocalDateTime(obj);
    }

    @Override
    public LocalDate objToLocalDate(Object obj) {
        return Convert.convert(LocalDate.class, obj);
    }

    @Override
    public LocalTime objToLocalTime(Object obj) {
        return Convert.convert(LocalTime.class, obj);
    }

    @Override
    public URI objToUri(Object obj) {
        return Convert.convert(URI.class, obj);
    }

    @Override
    public URL objToUrl(Object obj) {
        return Convert.convert(URL.class, obj);
    }

    @Override
    public Calendar objToCalendar(Object obj) {
        return Convert.convert(Calendar.class, obj);
    }

    @Override
    public Currency objToCurrency(Object obj) {
        return Convert.convert(Currency.class, obj);
    }

}
