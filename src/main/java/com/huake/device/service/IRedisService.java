package com.huake.device.service;

public interface IRedisService {  
    
    public boolean set(String key, String value);  
    
    public boolean set(String key, String value, long expire);
      
    public String get(String key);  
      
    public boolean expire(String key, long expire);
    
    public boolean delete(final String key);
      
} 
