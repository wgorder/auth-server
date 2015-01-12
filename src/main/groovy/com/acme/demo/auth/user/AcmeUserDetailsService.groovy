package com.acme.demo.auth.user

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Loads user specific AcmeUser data
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Component("userDetailsService")
@Slf4j
public class AcmeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);
        String lowercaseEmail = email.toLowerCase();

        AcmeUser acmeUser = userRepository.findOneByEmail(lowercaseEmail);
        if (acmeUser == null) {
            throw new UserNotFoundException("User " + lowercaseEmail + " was not found in the database");
        } else if (!acmeUser.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseEmail + " was not activated");
        }

        return new AcmeUserDetails(acmeUser);
    }
}
