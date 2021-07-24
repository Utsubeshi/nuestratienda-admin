package com.nuestratienda.admin.repository

import com.nuestratienda.admin.model.PasswordResetToken
import org.springframework.data.repository.CrudRepository

interface PasswordResetTokenRepository : CrudRepository<PasswordResetToken, Long> {
}