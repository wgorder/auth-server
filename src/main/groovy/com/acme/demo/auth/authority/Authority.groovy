package com.acme.demo.auth.authority

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Defines the granted authorities currently held by an AcmeUser
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Entity
@Table(name = 'AUTHORITY')
@EqualsAndHashCode
@ToString
class Authority implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(length = 50)
    String name;

    @Override
    String getAuthority() {
        return new SimpleGrantedAuthority(name)
    }
}
