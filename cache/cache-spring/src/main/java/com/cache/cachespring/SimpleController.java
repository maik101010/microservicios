package com.cache.cachespring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SimpleController {
    private final MyBeanRepository beanRepository;

    public SimpleController(MyBeanRepository beanRepository) {
        this.beanRepository = beanRepository;
    }
    @GetMapping(path = "/cache")
    public List<MyBean> getBean(){
        String code="code-";
        List<MyBean> myBeanList = new ArrayList<>();
        for (int i=1; i<=3; i++){
            myBeanList.add(beanRepository.getByCode(code+""+i));
        }
        return myBeanList;
    }
}
