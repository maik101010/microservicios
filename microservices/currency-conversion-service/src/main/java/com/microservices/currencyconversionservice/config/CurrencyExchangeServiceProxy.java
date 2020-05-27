package com.microservices.currencyconversionservice.config;

import com.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "current-exchange-service", url = "localhost:8000")
@FeignClient(name = "api-gateway")
@RibbonClient(name = "current-exchange-service")
public interface CurrencyExchangeServiceProxy {
    //Return the object to need, not object that return the other service, set name application "current-exchange-service
    @GetMapping("/current-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
                                                 @PathVariable("to") String to);
}
