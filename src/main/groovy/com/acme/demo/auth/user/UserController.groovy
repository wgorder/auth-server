package com.acme.demo.auth.user

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

/**
 * Return information about the currently logged in user
 *
 * @author William Gorder
 * @since 1/10/15
 */
@RestController
@Slf4j
class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal principal) {
        return principal
    }
}
