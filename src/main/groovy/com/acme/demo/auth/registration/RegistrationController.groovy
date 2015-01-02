package com.acme.demo.auth.registration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    public String register(@RequestParam("email") String email, @RequestParam("password") String password,
                           @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        registrationService.registerUser(email, password, firstName, lastName)
    }

    /**
     * Activates the user with the given key
     *
     * @param activationKey - the activation key
     */
    @RequestMapping(value = "/activate/{key}")
    public void activateUser(@PathVariable("key") String activationKey) {
        registrationService.activateUser(activationKey)
    }
}
