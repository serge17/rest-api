package org.company.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ConnectUsersResponse(
    @JsonProperty("success")
    val success: Boolean,
    @JsonProperty("error")
    val error: String,
)
