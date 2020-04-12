package com.proxy.config;

import com.google.common.collect.ImmutableMap;
import com.proxy.servlet.ProxyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProxyServletConfiguration {

  @Bean
  public Servlet proxyServlet(){
    ProxyServlet proxyServlet = new ProxyServlet();
    Map<String, String> map = new HashMap<>();
    map.put("/http-bind", "/http-bind");
    map.put("/libs", "/libs");
    map.put("/jtisi", "");
    proxyServlet.setTargetUriMap(map);
    return proxyServlet;
  }

  @Bean
  public ServletRegistrationBean servletRegistrationBean(){
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(proxyServlet(), "/http-bind", "/libs/*", "/jtisi/*");
    Map<String, String> params = ImmutableMap.of(
            "targetUri", "https://192.168.125.136:8443",
            "log", "true");
    registrationBean.setInitParameters(params);
    return registrationBean;
  }
}