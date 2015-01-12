package com.acme.demo.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Web configuration
 * @author William Gorder
 * @since 1/9/2015
 */
@Configuration
class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Serve a login page
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login")
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        new Hibernate4Module()
    }

}
