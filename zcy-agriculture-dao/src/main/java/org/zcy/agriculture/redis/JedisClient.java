package org.zcy.agriculture.redis;

import java.util.List;
import java.util.Map;

public interface JedisClient {

    String set(String key, String value);
    
    String get(String key);
    
    String setEntity(String key, Object value);
    
    String setEntityExpire(String key, Object value, int time);
    
    <T> boolean setList(String key, List<T> list);
    
    <T> T getEntity(String key, Class<T> clazz);
    
    <T> List<T> getListEntity(String key, Class<T> entityClass);

    List<String> getListString(String key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long ttl(String key);

    Long incr(String key);

    Long decr(String key);

    Long zadd(String key, Object value, double score);

    Long zrem(String key, Object value);
    
    Long del(String... key);

    <K, V> boolean setMap(String key, Map<String, V> map);

    <K, V> Map<String, V> getMap(String key);

    boolean setMapKey(String key, String mapKey, Object value);

    Object getMapKey(String key, String mapKey);

    boolean setMapKeyExpire(String key, String mapKey, Object value, int seconds);

    boolean delMapKey(String key, String mapKey);
    
    boolean hexists(String key, String mapKey);
    
}
