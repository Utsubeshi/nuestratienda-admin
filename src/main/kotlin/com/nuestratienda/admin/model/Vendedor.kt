package com.nuestratienda.admin.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class Vendedor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var nombres: String = "",

    @Column(nullable = false)
    var apellidos: String = "",

    @Column(nullable = false)
    private var password: String = "",

    @Column(unique = true, nullable = false)
    var correo: String = "",

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idTienda")
    var tienda: Tienda = Tienda(),

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "idSuscripcion")
    var suscripcion: Suscripcion = Suscripcion()
    ) : UserDetails {

    @JsonIgnore
    override fun getPassword(): String = password

    fun setPassword(password: String) {
        this.password = password
    }

    @Transient
    var grantedAuthorities: MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getUsername(): String = correo

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}