package io.github.linpeilie.map;

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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map 转对象时的核心类型转换接口。
 * <p>
 * 内置默认实现为 {@link HutoolMapObjectConverter}（依赖 hutool-core）。
 * 可通过 {@code @AutoMapMapper(use = MyConverter.class)} 或
 * {@code @MapperConfig(mapObjectConverter = MyConverter.class)} 指定自定义实现，
 * 从而完全摆脱 hutool 依赖。
 *
 * @since 1.5.2
 */
public interface MapObjectConverter {

    /** 实例缓存，保证同一个 Class 全局只实例化一次 */
    Map<Class<? extends MapObjectConverter>, MapObjectConverter> INSTANCE_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取转换器实例（单例），要求实现类具备无参构造函数。
     * 适用于运行时代码自行调用转换器的场景；MapStruct 生成的 mapper
     * 通过自身的 uses 机制实例化，不依赖此方法。
     */
    @SuppressWarnings("unchecked")
    static <T extends MapObjectConverter> T getInstance(Class<T> clazz) {
        return (T) INSTANCE_CACHE.computeIfAbsent(clazz, k -> {
            try {
                return k.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("实例化 " + clazz.getName() + " 失败，请确保存在无参构造", e);
            }
        });
    }

    String objToString(Object obj);

    BigDecimal objToBigDecimal(Object obj);

    BigInteger objToBigInteger(Object obj);

    Integer objToInteger(Object obj);

    Long objToLong(Object obj);

    Double objToDouble(Object obj);

    Boolean objToBoolean(Object obj);

    Date objToDate(Object obj);

    LocalDateTime objToLocalDateTime(Object obj);

    LocalDate objToLocalDate(Object obj);

    LocalTime objToLocalTime(Object obj);

    URI objToUri(Object obj);

    URL objToUrl(Object obj);

    Calendar objToCalendar(Object obj);

    Currency objToCurrency(Object obj);
}
