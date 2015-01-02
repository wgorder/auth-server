package com.acme.demo.auth.user

import groovy.transform.InheritConstructors
import org.springframework.security.core.AuthenticationException

/**
 * Thrown when the provided username is not found
 *
 * @author William Gorder
 * @since 12/30/14
 */
@InheritConstructors
class UserNotFoundException extends AuthenticationException {
}
