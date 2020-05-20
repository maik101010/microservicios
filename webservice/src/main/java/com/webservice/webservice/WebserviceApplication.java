package com.webservice.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

//@SpringBootApplication
interface Sender {
    public void saludar();
}

interface Download {
    public void descargar();
}

interface Jugar {
    public void jugar();
}

class JugarImplements implements Jugar {

    @Override
    public void jugar() {
        System.out.println("Implementando jugar");
    }
}

class DownloadImplements implements Download {

    @Override
    public void descargar() {
        System.out.println("Implementando download");
    }
}

class SenderImplements implements Sender {
    Jugar jugar;
    Download download;

    public SenderImplements(Jugar jugar, Download download) {
        this.download = download;
        this.jugar = jugar;
    }

    @Override
    public void saludar() {
        System.out.println("Implementando saludar de sender");
    }

    public Jugar getJugar() {
        return jugar;
    }

    public void setJugar(Jugar jugar) {
        this.jugar = jugar;
    }

    public Download getDownload() {
        return download;
    }

    public void setDownload(Download download) {
        this.download = download;
    }
}

public class WebserviceApplication {

    public static void main(String[] args) {
        Jugar jugar = new JugarImplements();
        Download download = new DownloadImplements();
        Sender sender = new SenderImplements(jugar, download);
        sender.saludar();
//        SpringApplication.run(WebserviceApplication.class, args);
    }

//    @Bean
//    public LocaleResolver localeResolver() {
//        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.US);
//        return localeResolver;
//    }

}
