package com.cache.cachespring;

public interface MyBeanRepository {
    MyBean getByCode(String code);
}
