package com.acme.demo.auth.user

import com.acme.demo.auth.support.SecurityAccessor
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal
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

    @Autowired
    SecurityAccessor securityAccessor

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Principal user(Principal principal) {
        if(principal.userAuthentication instanceof AcmeUserDetails) {
            log.info "YAY it is correct: ${principal.userAuthentication}"
        }
        return principal //todo should be an instance of AcmeUserDetails but its not
        //securityAccessor.getCurrentUser()
    }
}
