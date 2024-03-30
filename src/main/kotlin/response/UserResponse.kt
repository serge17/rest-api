package org.company.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class UserResponse(
    @JsonProperty("name")
    var name: String,
    @JsonProperty("email")
    var email: String,
    @JsonProperty("phone")
    var phone: String,
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("createdAtUtc")
    val createdAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
    @JsonProperty("updatedAtUtc")
    val updatedAtUtc: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),
)
