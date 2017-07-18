package com.jack.gumballs.utils;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;

/**
 * Redis 客服端
 * @ClassName:RedisClient
 * @Description:
 */
public class RedisUtil {
	
	public static Logger log = Logger.getLogger(RedisUtil.class);
	/**
	 * jedis 连接池 
	 */
	private static JedisPool jedisPool;

	@Resource
	public void setJedisPool(JedisPool jedisPool) {
		RedisUtil.jedisPool = jedisPool;
	}
	/**
	 * 释放连接对象
	 * @Title:closeShardedJedis
	 * @param jedis
	 * @return void
	 * @throws 
	 */
	public static void closeJedis(Jedis jedis){
		if (null != jedis){
			jedisPool.returnResource(jedis);
		}
	}
	
	
	/**
	 * 释放异常的连接对象
	 * @Title:closeShardedJedis
	 * @param jedis
	 * @return void
	 * @throws 
	 */
	public static void closeBrokenJedis(Jedis jedis){
		if (null != jedis){
			jedisPool.returnBrokenResource(jedis);
		}
	}
	/**
	 * 获取连接对象
	 * @Title:getShardedJedis
	 * @return ShardedJedis
	 * @throws 
	 */
	public static Jedis getJedis(){
		return RedisUtil.jedisPool.getResource();
	}
	
	
	/**
	 * 根据redis的key找到对应的值
	 * @param jediskey  redis键
	 * @return  //返回redis储存信息
	 */
	public static String getJedisVal(String jediskey)
	{
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			String value = jedis.get(jediskey);
			return value;
		}
		catch(Exception e)
		{
			log.error("key:"+jediskey+"未能取到redis的值.");
			closeBrokenJedis(jedis);
			return null;
		}
		finally
		{
			closeJedis(jedis);
		}
	}
	
	
	/**
	 * 存储redis的键值不设置时间
	 * @param jediskey  redis键
	 * @param jedisval  redis值
	 */
	public static void setJedisVal(String jediskey,String jedisval)
	{
		setJedisVal(jediskey,jedisval,0);
	}
	/**
	 * 设置时间存储redis
	 * @param jediskey  redis键
	 * @param jedisval  redis值
	 * @param seconds   时间(秒)
	 */
	public static void setJedisVal(String jediskey,String jedisval,int seconds) 
	{
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			
			if(seconds>0)
			{
				jedis.setex(jediskey, seconds, jedisval);
			}
			else
			{
				jedis.set(jediskey,jedisval);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			closeBrokenJedis(jedis);
		}
		finally
		{
			closeJedis(jedis);
		}
	}
	/**
	 * 根据redis键值删除redis
	 * @param jediskey  键值
	 */
	public static void delJedis(String jediskey) 
	{
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			jedis.del(jediskey);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			closeBrokenJedis(jedis);
		}
		finally
		{
			closeJedis(jedis);
		}
	}
	
	/**
	 * 在链表头位置插入数据
	 * @author LI JIA KUI 
	 * @date 2017年11月10日 上午6:03:28
	 * @param key
	 * @param value
	 * @see [类、类#方法、类#成员]
	 */
	public static void lpush(String key,String value){
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			jedis.lpush(key, value);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			closeBrokenJedis(jedis);
		}
		finally
		{
			closeJedis(jedis);
		}		
	}
	
	/**
	 * 根据key获取链表
	 * @author LI JIA KUI 
	 * @date 2017年11月10日 上午6:03:14
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static List<String> lrange(String key,Long start,Long end){
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			return jedis.lrange(key, start, end);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			closeBrokenJedis(jedis);
			return null;
		}
		finally
		{
			closeJedis(jedis);
		}			
	}
	
	/**
	 * 获取链表中的元素的数量
	 * @author LI JIA KUI 
	 * @date 2017年11月10日 上午6:02:51
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Long llen(String key){
		Jedis jedis =null;
		try
		{
			jedis =  RedisUtil.getJedis();
			return jedis.llen(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			closeBrokenJedis(jedis);
			return null;
		}
		finally
		{
			closeJedis(jedis);
		}				
	}
}
