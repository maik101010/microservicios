package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.microservices.currencyconversionservice.bean.JsonFormatBean;
import com.microservices.currencyconversionservice.config.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeServiceProxy serviceProxy;

    public CurrencyConversionController(CurrencyExchangeServiceProxy serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to
            , @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables
                );
        CurrencyConversionBean response = responseEntity.getBody();
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to
            , @PathVariable BigDecimal quantity) {
        CurrencyConversionBean response = serviceProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/test-json-format")
    public JsonFormatBean formatJson() {
        return new JsonFormatBean(new CurrencyConversionBean(1L, "", "", new BigDecimal(12), new BigDecimal(15), new BigDecimal(16), 1), "json");
    }
    @GetMapping("/test/{param}")
    public JsonFormatBean formatJson(@PathVariable("param") String param) {
        return new JsonFormatBean("hola " + param);
    }
}

