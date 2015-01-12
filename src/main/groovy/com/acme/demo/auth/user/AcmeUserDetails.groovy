package com.acme.demo.auth.user

import groovy.transform.ToString
import org.springframework.security.core.userdetails.UserDetails

/**
 * User Details implementation for an AcmeUser
 *
 * @author William Gorder
 * @since 12/30/2014
 */
@ToString(includeSuper = true)
class AcmeUserDetails extends AcmeUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    public AcmeUserDetails(){

    }

    AcmeUserDetails(AcmeUser acmeUser) {
        super(acmeUser)
    }

    @Override
    String getUsername() {
        return getEmail()
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return isActivated()
    }
}
