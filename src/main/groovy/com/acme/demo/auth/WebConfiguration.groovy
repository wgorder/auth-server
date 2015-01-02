package com.acme.demo.auth

import org.springframework.context.annotation.Configuration
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
        registry.addViewController("/login").setViewName("login");
    }

}
