package org.zcy.agriculture.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Component
public class JedisClientPool implements JedisClient {

	@Autowired
	private JedisPool jedisPool;

	private Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	public String set(String key, String value) {
		Jedis jedis = getJedis();
		String result = null;
		try {
			result = jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}

	public String setEntity(String key, Object value) {
		Jedis jedis = null;
		String result = null;
		try {
			String objectJson = JSONObject.toJSONString(value);
			jedis = getJedis();
			if (jedis != null) {
				result = jedis.set(key, objectJson);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return result;
	}

	
	
	public String setEntityExpire(String key, Object value, int time) {
		String result = null;
		Jedis jedis = getJedis();
		String valueToSet = null;
		if(value instanceof String) {
			valueToSet = (String) value;
		}else {
			valueToSet = JSONObject.toJSONString(value);
		}
		if (time > 0) {
			result = jedis.setex(key, time, valueToSet);
		} else {
			result = setEntity(key, value);
		}
		return result;
	}

	public String get(String key) {
		Jedis jedis = getJedis();
		String result = null;
		try {
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return result;
	}


	public <T> T getEntity(String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
                String value = jedis.get(key);
                T object = JSON.parseObject(value, clazz);
                return object;
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return null;
	}

	public <T> boolean setList(String key, List<T> list) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				for (T vz : list) {
					if (vz instanceof String) {
						jedis.lpush(key, (String) vz);
					} else {
						String objectJson = JSONObject.toJSONString(vz);
						jedis.lpush(key, objectJson);
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	public <T> List<T> getListEntity(String key, Class<T> entityClass) {
		List<T> list = new ArrayList<>();
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				List<String> valueJson = jedis.lrange(key, 0, -1);
				for(String s : valueJson) {
					T object = JSON.parseObject(s, entityClass);
					list.add(object);
				}
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	public List<String> getListString(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				return jedis.lrange(key, 0, -1);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}


	public Boolean exists(String key) {
		Jedis jedis = getJedis();
		Boolean result = false;
		try {
			result = jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}


	public Long expire(String key, int seconds) {
		Jedis jedis = getJedis();
		Long result = -1L;
		try {
			result = jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}


	public Long ttl(String key) {
		Jedis jedis = getJedis();
		Long result = -1L;
		try {
			result = jedis.ttl(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}


	public Long incr(String key) {
		Jedis jedis = getJedis();
		Long result = -1L;
		try {
			result = jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = getJedis();
		Long result = -1L;
		try {
			result = jedis.decr(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return result;
	}


	public Long zadd(String key, Object value, double score) {
		Jedis jedis = getJedis();
		String objectJson = JSONObject.toJSONString(value);
		Long zadd = -1L;
		try {
			zadd = jedis.zadd(key, score, objectJson);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return zadd;
	}



	public Long zrem(String key, Object value) {
		Jedis jedis = getJedis();
		String objectJson = JSONObject.toJSONString(value);
		Long zrem = -1L;
		try {
			zrem = jedis.zrem(key, objectJson);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		return zrem;
	}


	public Long del(String... key) {
		Jedis jedis = getJedis();
		Long del = -1L;
		try {
			del = jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jedis.close();
		}
		return del;
	}

	public <K, V> boolean setMap(String key, Map<String, V> map) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				Set<Map.Entry<String, V>> entry = map.entrySet();
				for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
					Map.Entry<String, V> kv = ite.next();
					if (kv.getValue() instanceof String) {
						jedis.hset(key, kv.getKey(), (String) kv.getValue());
					} else if (kv.getValue() instanceof List) {
						jedis.hset(key, kv.getKey(), JSONArray.toJSONString(kv.getValue()));
					} else {
						jedis.hset(key, kv.getKey(), JSONObject.toJSONString(kv.getValue()));
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	public boolean setMapKey(String key, String mapKey, Object value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				if (value instanceof String) {
					jedis.hset(key, mapKey, String.valueOf(value));
				} else if (value instanceof List) {
					jedis.hset(key, mapKey, JSONArray.toJSONString(value));
				} else {
					jedis.hset(key, mapKey, JSONObject.toJSONString(value));
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	public boolean setMapKeyExpire(String key, String mapKey, Object value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				if (value instanceof String) {
					jedis.hset(key, mapKey, String.valueOf(value));
				} else if (value instanceof List) {
					jedis.hset(key, mapKey, JSONArray.toJSONString(value));
				} else {
					jedis.hset(key, mapKey, JSONObject.toJSONString(value));
				}
				jedis.expire(key, seconds);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	@SuppressWarnings("unchecked")
	public <K, V> Map<String, V> getMap(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
				return map;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	public Object getMapKey(String key, String mapKey) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				return jedis.hget(key, mapKey);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedis.close();
		}
	}

	public boolean delMapKey(String key, String mapKey) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hdel(key, mapKey);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}

	public boolean hexists(String key, String mapKey) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hexists(key, mapKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			jedis.close();
		}
	}




}
