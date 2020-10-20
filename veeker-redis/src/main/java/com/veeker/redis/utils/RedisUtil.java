package com.veeker.redis.utils;

import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具
 *
 * @author ：qiaoliang
 */
public class RedisUtil implements AutoCloseable{


    private static RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    private static long isLong(Long lo){
        return Objects.nonNull(lo)?lo:0;
    }

    private static boolean isBoolean(Boolean bol){
        return Objects.nonNull(bol)?bol:false;
    }

    /**
     * 指定缓存失效时间
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param time : 缓存时间
     * @return boolean
     * @date 2020-07-28 14:33
     */
    public static boolean expire(String key,long time){
        if(time>0){
            return isBoolean(redisTemplate.expire(key, time, TimeUnit.SECONDS));
        }
        return false;
    }

    /**
     * 根据key 获取过期时间
     *
     * @author ：qiaoliang
     * @param key : 键 不能为null
     * @return long 时间(秒) 返回0代表为永久有效
     * @date 2020-07-28 14:33
     */
    public static long getExpire(String key){
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return Objects.nonNull(expire)?expire:-1;
    }

    /**
     * 判断key是否存在
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return boolean
     * @date 2020-07-28 14:33
     */
    public static boolean isKey(String key){
        return isBoolean(redisTemplate.hasKey(key));
    }

    /**
     * 删除缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return java.lang.Integer
     * @date 2020-07-28 14:33
     */
    public static Integer delete(String ... key){
        if(key.length==1){
            Boolean delete = redisTemplate.delete(key[0]);
            return Objects.nonNull(delete)&&delete?1:0;
        }else{
            return Objects.requireNonNull(redisTemplate.delete(Arrays.asList(key))).intValue();
        }
    }

    /**
     * 普通缓存获取
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return java.lang.Object
     */
    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 普通缓存放入
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     * @date 2020-07-28 14:33
     */
    public static void set(String key,Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param time : 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @param value : 值
     * @date 2020-07-28 14:34
     */
    public static void set(String key,long time,Object value){
        if(time>0){
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        }else{
            set(key, value);
        }
    }

    /**
     * 递增
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param delta : 要增加几(大于0)
     * @return long
     * @date 2020-07-28 14:34
     */
    public static long incr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return isLong(redisTemplate.opsForValue().increment(key, delta));
    }

    /**
     * 递减
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param delta : 要减几(大于0)
     * @return long
     * @date 2020-07-28 14:34
     */
    public static long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return isLong(redisTemplate.opsForValue().increment(key, -delta));
    }

    /**
     * HashGet
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @return java.lang.Object
     * @date 2020-07-28 14:34
     */
    public static Object hashGet(String key,String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return java.util.Map<java.lang.Object, java.lang.Object>
     */
    public static Map<Object,Object> hashGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param map : 对应多个键值
     */
    public static void hashsSet(String key, Map<String,Object> map){
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * HashSet
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param map : 对应多个键值
     * @param time : 时间(秒)
     * @return boolean
     */
    public static void hashsSet(String key, Map<String, Object> map, long time){
        redisTemplate.opsForHash().putAll(key, map);
        if(time>0){
            expire(key, time);
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @param value : 值
     * @return boolean
     */
    public static void hashsSet(String key,String item,Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @param value : 值
     * @param time : 时间(秒)
     * @return boolean
     */
    public static void hashsSet(String key,String item,Object value,long time) {
        redisTemplate.opsForHash().put(key, item, value);
        if(time>0){
            expire(key, time);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 键
     */
    public static void delectHash(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @return boolean
     */
    public static boolean isHashKey(String key, String item){
        return isBoolean(redisTemplate.opsForHash().hasKey(key, item));
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @param by : 要增加几(大于0)
     * @return double
     */
    public static double hashIncr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减 并把新增后的值返回
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param item : 项
     * @param by : 要减少记(小于0)
     * @return double
     */
    public static double hashDecr(String key, String item,double by){
        return redisTemplate.opsForHash().increment(key, item,-by);
    }

    /**
     *  获取hash表中的所有key
     *
     * @author ：qiaoliang
     * @param key : key
     * @return java.util.Set<java.lang.Object>
     */
    public static Set<Object> hashKeysAll(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     *  获取hash表中的所有value
     *
     * @author ：qiaoliang
     * @param key : key
     * @return java.util.List<java.lang.Object>
     * @date 2020-10-19 16:57
     */
    public static List<Object> hashValusAll(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return java.util.Set<java.lang.Object>
     */
    public static Set<Object> setGet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     * @return boolean
     */
    public static boolean setIsKey(String key,Object value){
        return isBoolean(redisTemplate.opsForSet().isMember(key, value));
    }

    /**
     * 将数据放入set缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param values : 值 可以是多个
     * @return long
     */
    public static long setSet(String key, Object...values) {
        return isLong(redisTemplate.opsForSet().add(key, values));
    }

    /**
     * 将set数据放入缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param time : 时间(秒)
     * @param values : 值 可以是多个
     * @return long
     * @date 2020-07-28 14:39
     */
    public static long setSetAndTime(String key,long time,Object...values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if(time>0) {
            expire(key, time);
        }
        return isLong(count);
    }

    /**
     * 获取set缓存的长度
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return long
     */
    public static long setGetSetSize(String key){
        return isLong(redisTemplate.opsForSet().size(key));
    }

    /**
     * 移除值为value的
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param values : 值 可以是多个
     * @return long
     */
    public static long setRemove(String key, Object ...values) {
        return isLong(redisTemplate.opsForSet().remove(key, values));
    }

    /**
     * 获取list缓存的内容
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param start : 开始
     * @param end : 结束  0 到 -1代表所有值
     * @return java.util.List<java.lang.Object>
     */
    public static List<Object> listGet(String key,long start, long end){
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @author ：qiaoliang
     * @param key : 键
     * @return long
     */
    public static long listGetListSize(String key){
        return isLong(redisTemplate.opsForList().size(key));
    }

    /**
     * 通过索引 获取list中的值
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param index : 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return java.lang.Object
     */
    public static Object listGetIndex(String key,long index){
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     */
    public static void listSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     * @param time : 时间(秒)
     */
    public static void listSet(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        if (time > 0){
            expire(key, time);
        }
    }

    /**
     * 将list放入缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     */
    public static void listSet(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @author ：qiaoliang
     * @param key : 键
     * @param value : 值
     * @param time : 时间(秒)
     */
    public static void listSet(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0){
            expire(key, time);
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @author ：qiaoliang
     * @param key 键
     * @param index 索引
     * @param value 值
     */
    public static void listUpdateIndex(String key, long index,Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @author ：qiaoliang
     * @param key 键
     * @param count
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     * @param value 值
     * @return long
     */
    public static long listRemove(String key,long count,Object value) {
        return isLong(redisTemplate.opsForList().remove(key, count, value));
    }


    @Override
    public void close() throws Exception {
        RedisConnectionUtils.unbindConnection(Objects.requireNonNull(redisTemplate.getConnectionFactory()));
    }

}
