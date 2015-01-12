package com.acme.demo.auth.registration

import com.acme.demo.auth.authority.Authorities
import com.acme.demo.auth.authority.Authority
import com.acme.demo.auth.authority.AuthorityRepository
import com.acme.demo.auth.support.RandomUtil
import com.acme.demo.auth.user.AcmeUser
import com.acme.demo.auth.user.UserRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * Register new users
 */
@Service
@Slf4j
class RegistrationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user
     *
     * @param email - the email for the new user
     * @param password - the password for the new user
     * @param firstName - the new users first name
     * @param lastName - the new users last name
     * @return the activation code
     */
    public String registerUser(def registration) {
        AcmeUser newUser = new AcmeUser();
        //default new users to ROLE_USER
        Authority authority = authorityRepository.findOne(Authorities.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(registration.password);
        newUser.setEmail(registration.email);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(registration.firstName);
        newUser.setLastName(registration.lastName);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        newUser.setCreatedBy("system");
        userRepository.save(newUser);
        log.debug("Created User: {}", newUser);
        return newUser.getActivationKey();
    }
}
