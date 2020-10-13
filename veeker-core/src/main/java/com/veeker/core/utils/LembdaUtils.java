package com.veeker.core.utils;

import ch.qos.logback.core.pattern.Converter;
import com.google.common.base.Supplier;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Lembda 函数式接口工具
 *
 * @author ：qiaoliang
 */
public class LembdaUtils {

    /**
     * 对类型为T的对象应用操作
     *
     * @author ：qiaoliang
     * @param list : 对象T
     * @param consumer : 操作方法
     */
    public static <T> void accept(List<T> list, Consumer<T> consumer){
        for (T t1 : list) {
            consumer.accept(t1);
        }
    }

    /**
     * 对类型为T的对象多个应用操作
     *
     * @author ：qiaoliang
     * @param list : 对象T
     * @param consumers : 多个操作方法  逗号分隔
     */
    public static <T> void accept(List<T> list, Consumer<T>... consumers){
        for (T t1 : list) {
            for (Consumer<T> consumer : consumers) {
                consumer.accept(t1);
            }
        }
    }


    /**
     * 产生指定个数的对象，并放入集合中
     *
     * @author ：qiaoliang
     * @param num : 指定个数
     * @param supplier : 对象的产生
     * @return java.util.List<T>
     */
    public static <T> List<T> get(int num , Supplier<T> supplier){
        List<T> lists = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            T t = supplier.get();
            if(Objects.nonNull(t)){
                lists.add(t);
            }
        }
        return lists;
    }

    /**
     * 实体间进行互相转换
     *
     * @author ：qiaoliang
     * @param list : 集合
     * @param function : 转换方式
     * @return java.util.List<V>
     */
    public static <T,V> List<V> apply(List<T> list, Function<T,V> function){
        List<V> vList = new ArrayList<>();
        V v = null;
        for (T t : list) {
            v = function.apply(t);
            vList.add(v);
        }
        return vList;
    }


    /**
     * 将满足条件的实体，放入集合中
     *
     * @author ：qiaoliang
     * @param list : 操作集合
     * @param predicate : 验证方式
     * @return java.util.List<T>
     */
    public static <T> List<T> filter(List<T> list , Predicate<T> predicate){
        List<T> tList = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t)){
                tList.add(t);
            }
        }
        return tList;
    }

    /**
     * 将满足条件的实体，放入集合中
     *
     * @author ：qiaoliang
     * @param list : 操作集合
     * @param predicate : 多验证方式   逗号分隔
     * @return java.util.List<T>
     */
    public static <T> List<T> filter(List<T> list , Predicate<T>... predicate){
        List<T> tList = new ArrayList<>();
        for (T t : list) {
            boolean flg = true;
            for (Predicate<T> tPredicate : predicate) {
                if(!tPredicate.test(t)){
                    flg = false;
                }
            }
            if(flg){
                tList.add(t);
            }
        }
        return tList;
    }


    private static final Pattern GET_PATTERN = Pattern.compile("get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("is[A-Z].*");
    /**
     * 切割方法名称 获取对应字段名称
     *
     * @author ：qiaoliang
     * @param fn : 接口
     * @return java.lang.String
     * @date 2020-08-21 17:03
     */
    public static <T> String fnToFieldName(Converter<T> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda invoke = (SerializedLambda) method.invoke(fn);
            // 得到方法名
            String getter = invoke.getImplMethodName();
            // 切割得到字段名
            if(GET_PATTERN .matcher(getter).matches()) {
                getter =  getter.substring(3);
            }
            if (IS_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(2);
            }
            return Introspector.decapitalize(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException();
        }
    }

}
