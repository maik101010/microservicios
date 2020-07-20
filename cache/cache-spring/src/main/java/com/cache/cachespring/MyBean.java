package com.cache.cachespring;

import org.springframework.cache.annotation.Cacheable;

import java.util.Date;

public class MyBean {
    private String code;
    private String value;
    private String address;
    private Date currentDate;

    public MyBean(String code, String value, String address, Date currentDate) {
        this.code = code;
        this.value = value;
        this.address = address;
        this.currentDate = currentDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Cacheable("addresses")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String id) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "code=" + code +
                ", value='" + value + '\'' +
                ", address='" + address + '\'' +
                ", currentDate=" + currentDate +
                '}';
    }
}
