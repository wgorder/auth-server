package com.acme.demo.auth.user

import groovy.transform.InheritConstructors
import org.springframework.security.core.AuthenticationException


/**
 * Thrown when the user attempting to authenticate is not activated
 *
 * @author William Gorder
 * @since 12/30/14
 */
@InheritConstructors
class UserNotActivatedException extends AuthenticationException{
}
