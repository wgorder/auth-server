package com.acme.demo.auth.user

import org.joda.time.DateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * AcmeUser Repository
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Repository
interface UserRepository extends JpaRepository<AcmeUser, String> {

    @Query("select u from AcmeUser u where u.activationKey = ?1")
    AcmeUser getUserByActivationKey(String activationKey);

    @Query("select u from AcmeUser u where u.activated = false and u.createdDate > ?1")
    List<AcmeUser> findNotActivatedUsersByCreationDateBefore(DateTime dateTime);

    AcmeUser findOneByEmail(String email);
}