package com.cache.cachespring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class SimpleMyBeanRepository implements MyBeanRepository{
    @Override
    @Cacheable("myBean")
    public MyBean getByCode(String code) {
        simulateSlowService();
        return new MyBean(code,"test", "test direction", new Date());

    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
