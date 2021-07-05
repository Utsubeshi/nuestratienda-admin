package com.nuestratienda.admin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

@Entity
data class Vendedor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Pattern(regexp="[a-zA-Z]")
    @NotEmpty(message = "Please provide name")
    @Column(nullable = false)
    var nombres: String = "",

    @Column(nullable = false)
    var apellidos: String = "",

    @Column(nullable = false)
    private var password: String = "",

    @Column(unique = true, nullable = false)
    @Email
    var correo: String = "",

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idTienda")
    var tienda: Tienda = Tienda(),

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idSuscripcion")
    var suscripcion: Suscripcion = Suscripcion()
    ) : UserDetails {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun getPassword(): String = password

    fun setPassword(password: String) {
        this.password = password
    }

    @Transient
    var grantedAuthorities: MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getUsername(): String = correo

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isAccountNonExpired(): Boolean = true

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isAccountNonLocked(): Boolean = true

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}