package com.cache.cachespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final MyBeanRepository beanRepository;

    public AppRunner(MyBeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... MOSTRANDO MY BEAN CACHEADO");
        logger.info("isbn-1234 -->" + beanRepository.getByCode("isbn-1234"));
        logger.info("isbn-4567 -->" + beanRepository.getByCode("isbn-4567"));
        logger.info("isbn-1234 -->" + beanRepository.getByCode("isbn-1234"));
        logger.info("isbn-4567 -->" + beanRepository.getByCode("isbn-4567"));
        logger.info("isbn-1234 -->" + beanRepository.getByCode("isbn-1234"));
        logger.info("isbn-1234 -->" + beanRepository.getByCode("isbn-1234"));
    }
}
