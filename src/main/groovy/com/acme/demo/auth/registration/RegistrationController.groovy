package com.acme.demo.auth.registration

import com.acme.demo.auth.user.AcmeUser
import com.acme.demo.auth.user.UserRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Controller exposing HTTP endpoints for new user registration and activation
 *
 * @author William Gorder
 * @since 12/30/14
 */
@RestController
class RegistrationController {

    @Autowired
    RegistrationService registrationService

    @Autowired
    UserRepository userRepository

    @Autowired
    ActivationService activationService

    /**
     * Normally we would register the new user and email that user their activation key.  But to
     * keep this demo a little simpler we will just send the activation code back
     * @param email - the email of the new user
     * @param password - the password of the new user
     * @param firstName - the first name of the new user
     * @param lastName - the last name of the new user
     * @return the activation code needed to complete the registration and activate the user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody String registrationJson){
        def registration = new JsonSlurper().parseText(registrationJson)
        AcmeUser acmeUser = userRepository.findOneByEmail(registration.email)
        if(acmeUser){
            return new ResponseEntity<String>("e-mail address already in use", HttpStatus.BAD_REQUEST)
        }
        else {
            String activationKey = registrationService.registerUser(registration)
            Map activation = ['registrationKey' : activationKey]
            return new ResponseEntity(activation, HttpStatus.CREATED)
        }
    }

    /**
     * Activates the user with the given key
     *
     * @param activationKey - the activation key
     */
    @RequestMapping(value = "/activate")
    public void activateUser(@RequestParam("key") String activationKey) {
        activationService.activateUser(activationKey)
    }
}
