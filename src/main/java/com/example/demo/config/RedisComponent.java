package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author
 */
@Component
public class RedisComponent {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public void set(String key, String value) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if (!this.stringRedisTemplate.hasKey(key)) {
			ops.set(key, value);
			System.out.println("set key success -->  "+key + "  : "+value);
		} else {
			// 存在则打印之前的value值
			//RedisTemplate<Object, Object>;
			System.out.println("已存在  【"+key + "】  = " + ops.get(key));
		}
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @param l  超时时间
	 * @param timeUnit  时间类型，秒，分，时
	 */
	public void setLogin(String key, String value, long l , TimeUnit timeUnit) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();

			ops.set("login_"+key, value,l,timeUnit);
			System.out.println("setLogin key success -->  "+key + "  : "+value);

	}


	public String getLogin(String key) {
		return this.stringRedisTemplate.opsForValue().get("login_"+key);
	}

	/**
	 *
	 * @param key
	 * @param value
	 * @param l  超时时间
	 * @param timeUnit  时间类型，秒，分，时
	 */
	public void setString(String key, String value, long l , TimeUnit timeUnit) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
			ops.set(key, value,l,timeUnit);
	}



	public void useSessionCallBack(RedisTemplate redisTemplate){
		redisTemplate.execute(new SessionCallback() {
			@Nullable
			@Override
			public Object execute(RedisOperations redisOperations) throws DataAccessException {
				redisOperations.opsForValue().set("key1","value1");
				redisOperations.opsForHash().put("hash1","filed","h_value");
				return null;
			}
		});
	}


	public String get(String key) {
		return this.stringRedisTemplate.opsForValue().get(key);
	}

	public void del(String key) {
		this.stringRedisTemplate.delete(key);
	}
}
