package com.acme.demo.auth.user

import com.acme.demo.auth.authority.Authority
import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.annotations.Type
import org.hibernate.validator.constraints.Email
import org.joda.time.DateTime

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Entity representing a user
 *
 * @author William Gorder
 * @since 12/30/14
 */
@Entity
@Table(name = "ACME_USER")
@ToString(excludes = ["password"])
@EqualsAndHashCode(includes = ["email"])
class AcmeUser implements Serializable{

    private static final long serialVersionUID = 1L;

    public AcmeUser() {}

    public AcmeUser(AcmeUser acmeUser) {
        super();
        this.userId = acmeUser.userId
        this.email = acmeUser.email
        this.password = acmeUser.password
        this.firstName = acmeUser.firstName
        this.lastName = acmeUser.lastName
        this.activated = acmeUser.activated
        this.activationKey = acmeUser.activationKey
        this.authorities = acmeUser.authorities
        this.createdBy = acmeUser.createdBy
        this.createdDate = acmeUser.createdDate
        this.lastModifiedBy = acmeUser.lastModifiedBy
        this.lastModifiedDate = acmeUser.lastModifiedDate
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE       )
    @Column(name = "user_id")
    Long userId

    @Email
    @NotNull
    @Size(min = 0, max = 100)
    @Column(length = 100, unique = true)
    String email

    @JsonIgnore
    @Size(min = 0, max = 100)
    @Column(length = 100)
    String password

    @Size(min = 0, max = 50)
    @Column(name = "first_name", length = 50)
    String firstName

    @Size(min = 0, max = 50)
    @Column(name = "last_name", length = 50)
    String lastName

    boolean activated = false;

    @Size(min = 0, max = 20)
    @Column(name = "activation_key", length = 20)
    String activationKey

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = [ @JoinColumn(name = "user_id", referencedColumnName = "user_id")],
            inverseJoinColumns = [ @JoinColumn(name = "name", referencedColumnName = "name") ])
    Set<Authority> authorities = new HashSet<>()

    @NotNull
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    String createdBy

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_date", nullable = false)
    DateTime createdDate = DateTime.now()

    @Column(name = "last_modified_by", length = 50)
    String lastModifiedBy

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "last_modified_date")
    DateTime lastModifiedDate = DateTime.now()
}
