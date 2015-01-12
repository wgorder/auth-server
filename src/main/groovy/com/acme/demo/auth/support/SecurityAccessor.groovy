package com.acme.demo.auth.support

import com.acme.demo.auth.user.AcmeUserDetails
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Component

/**
 * Utility class for accessing things from the security context
 */
@Component
class SecurityAccessor {

    /**
     * Get the current users details
     * //TODO not and instance of AcmeUserDetails... WHY?
     * @return profile info for the current user
     */
    AcmeUserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder.context.authentication
        if(authentication) {
            //we know its an instance of OAuth2Authentication
           return authentication.userAuthentication
        }
        return  null
    }
}
