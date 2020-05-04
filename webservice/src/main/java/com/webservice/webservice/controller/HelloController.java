package com.webservice.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    private final MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello/variable/{name}")
    public HelloBean getSay(@PathVariable String name) {
        return new HelloBean(String.format("Hello, %s", name));
    }

    @GetMapping(path = "/hello-internationalized")
    public String getSayInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        //Can in Header Accept-Language request the message.properties resource
        return messageSource.getMessage("good.morning.message", null,
                LocaleContextHolder.getLocale());
    }
    //Can in Header X-API-VERSION=1 request
    @GetMapping(path = "/version/header", headers = "X-API-VERSION=1")
    public String getVersionOne() {
        return "Version uno";
    }
    //Can in Header X-API-VERSION=1 request
    @GetMapping(path = "/version/header", headers = "X-API-VERSION=2")
    public String getVersionTwo() {
        return "Version dos";
    }
}