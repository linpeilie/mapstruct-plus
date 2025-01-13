package io.github.linpeilie.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class ObjectUtils {

    /**
     * 检查对象是否为null<br>
     * 判断标准为：
     *
     * <pre>
     * 1. == null
     * 2. equals(null)
     * </pre>
     *
     * @param obj 对象
     * @return 是否为null
     */
    public static boolean isNull(Object obj) {
        //noinspection ConstantConditions
        return null == obj || obj.equals(null);
    }

    /**
     * 检查对象是否不为null
     * <pre>
     * 1. != null
     * 2. not equals(null)
     * </pre>
     *
     * @param obj 对象
     * @return 是否为非null
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 是否包含{@code null}元素
     *
     * @param <T>   数组元素类型
     * @param array 被检查的数组
     * @return 是否包含{@code null}元素
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isAnyNull(T... array) {
        if (ArrayUtil.isNotEmpty(array)) {
            for (T element : array) {
                if (isNull(element)) {
                    return true;
                }
            }
        }
        return array == null;
    }

    /**
     * 是否全都不为{@code null}元素
     *
     * @param <T>   数组元素类型
     * @param array 被检查的数组
     * @return 是否全都不为{@code null}元素
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isAllNotNull(T... array) {
        return !isAnyNull(array);
    }

    /**
     * 如果给定对象不为null 则以给定对象作为参数执行handle方法
     *
     * @param <T>    被检查对象
     * @param source Object 类型对象
     * @param handle 非空时自定义的处理方法
     * @return 处理后的对象
     */
    public static <T> T handleIfNull(T source, Consumer<T> handle) {
        if (isAllNotNull(source, handle)) {
            handle.accept(source);
        }
        return source;
    }

    /**
     * 如果给定对象为{@code null} 返回默认值, 如果不为null 返回自定义handle处理后的返回值
     *
     * @param <T>          被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @param <R>          被检查的对象类型
     * @param source       Object 类型对象
     * @param handle       非空时自定义的处理方法
     * @param defaultValue 默认为空的返回值
     * @return 处理后的返回值
     */
    public static <T, R> T defaultIfNull(R source, Function<R, ? extends T> handle, final T defaultValue) {
        if (isNotNull(source)) {
            return handle.apply(source);
        }
        return defaultValue;
    }

    /**
     * 如果给定对象为{@code null} 返回默认值, 如果不为null 返回自定义handle处理后的返回值
     *
     * @param <T>    被检查对象为{@code null}返回默认值，否则返回自定义handle处理后的返回值
     * @param <R>    被检查的对象类型
     * @param source Object 类型对象
     * @param handle 非空时自定义的处理方法
     * @return 处理后的返回值
     */
    public static <T, R> T defaultIfNull(R source, Function<R, ? extends T> handle) {
        return defaultIfNull(source, handle, null);
    }

}
