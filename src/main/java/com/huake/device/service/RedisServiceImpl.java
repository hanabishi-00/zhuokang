package com.huake.device.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;

	@Override
	public boolean set(final String key, final String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return true;
			}
		});
		return result;
	}

	@Override
	public boolean set(final String key, final String value, long expire) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				connection.expire(redisTemplate.getStringSerializer().serialize(key), expire);
				return true;
			}
		});
		return result;
	}

	public String get(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
		return result;
	}

	@Override
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public boolean delete(final String key) {
		return redisTemplate.delete(key);
	}

}
