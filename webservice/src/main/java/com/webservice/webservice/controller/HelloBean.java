package com.webservice.webservice.controller;

public class HelloBean {
    public String hello;

    public HelloBean(String helloWorld) {
        this.hello=helloWorld;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
