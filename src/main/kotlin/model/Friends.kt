package org.company.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Entity(name = "friends")
@Table(uniqueConstraints = [(UniqueConstraint(columnNames = ["first", "second"]))])
data class Friends(
    @Column(name = "first")
    val first: Long,
    @Column(name = "second")
    val second: Long,
    @Id
    @GeneratedValue
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "createdAtUtc")
    val createdAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
    @Column(name = "updatedAtUtc")
    val updatedAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
)
