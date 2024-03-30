package org.company.request

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserRequest(
    @JsonProperty("name", required = true)
    val name: String,
    @JsonProperty("email", required = true)
    val email: String,
    @JsonProperty("phone", required = true)
    val phone: String,
)
