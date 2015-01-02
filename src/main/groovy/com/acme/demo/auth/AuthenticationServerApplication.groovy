package com.acme.demo.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

/**
 * The authentication server application.  Also handles new user registrations, password
 * changes, user activation etc
 *
 * @author William Gorder
 * @since 12/30/14
 */
@SpringBootApplication
@EnableDiscoveryClient
class AuthenticationServerApplication {

    static void main(String[] args) {
        SpringApplication.run AuthenticationServerApplication, args
    }
}
