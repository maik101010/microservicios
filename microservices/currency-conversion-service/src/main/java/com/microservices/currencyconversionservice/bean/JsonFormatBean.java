package com.microservices.currencyconversionservice.bean;

import org.springframework.validation.annotation.Validated;

public class JsonFormatBean {
    private CurrencyConversionBean currencyConversionBean;
    private String json;

    public JsonFormatBean(CurrencyConversionBean currencyConversionBean, String json) {
        this.currencyConversionBean = currencyConversionBean;
        this.json = json;
    }

    public JsonFormatBean() {
    }
    public JsonFormatBean(String param) {
        this.json=param;
    }

    public CurrencyConversionBean getCurrencyConversionBean() {
        return currencyConversionBean;
    }

    public void setCurrencyConversionBean(CurrencyConversionBean currencyConversionBean) {
        this.currencyConversionBean = currencyConversionBean;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
