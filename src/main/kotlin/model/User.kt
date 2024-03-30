package org.company.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Entity(name = "user")
data class User(
    @Column(name = "name")
    var name: String,
    @Column(name = "email")
    var email: String,
    @Column(name = "phone")
    var phone: String,
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "createdAtUtc")
    val createdAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
    @Column(name = "updatedAtUtc")
    var updatedAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
)
