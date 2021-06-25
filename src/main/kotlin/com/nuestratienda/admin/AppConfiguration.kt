package com.nuestratienda.admin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

@Configuration
@EnableSwagger2WebMvc
open class AppConfiguration {
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}