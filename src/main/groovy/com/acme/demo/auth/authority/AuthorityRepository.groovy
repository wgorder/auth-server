package com.acme.demo.auth.authority

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * A Spring data JPA repository for looking up granted authorities from the database
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Repository
interface AuthorityRepository extends JpaRepository<Authority, String> {

}