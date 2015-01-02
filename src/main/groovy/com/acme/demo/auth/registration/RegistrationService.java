package com.acme.demo.auth.registration;

import com.acme.demo.auth.authority.Authorities;
import com.acme.demo.auth.authority.Authority;
import com.acme.demo.auth.authority.AuthorityRepository;
import com.acme.demo.auth.support.RandomUtil;
import com.acme.demo.auth.user.AcmeUser;
import com.acme.demo.auth.user.UserRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Handles registering and activating users.  Also cleans up users who have not activated
 * in a reasonable amount of time
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Service
public class RegistrationService {

    private final Logger log = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Activates the user with the given registration key
     * @param key - the activation key
     * @return The activated user
     */
    public AcmeUser activateUser(String key) {
        return Optional.ofNullable(userRepository.getUserByActivationKey(key))
                .map(user -> {
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                })
                .orElse(null);
    }

    /**
     * Non-activated users should be automatically deleted after 3 days.
     * <p/>
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     * </p>
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNonActivatedUsers() {
        DateTime now = new DateTime();
        List<AcmeUser> users = userRepository.findNotActivatedUsersByCreationDateBefore(now.minusDays(3));
        for (AcmeUser user : users) {
            log.debug("Deleting not activated user {}", user.getEmail());
            userRepository.delete(user);
        }
    }

    /**
     * Registers a new user
     *
     * @param email - the email for the new user
     * @param password - the password for the new user
     * @param firstName - the new users first name
     * @param lastName - the new users last name
     * @return the activation code
     */
    public String registerUser(String email, String password, String firstName, String lastName) {
        AcmeUser newUser = new AcmeUser();
        //default new users to ROLE_USER
        Authority authority = authorityRepository.findOne(Authorities.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
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
