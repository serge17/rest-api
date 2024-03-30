package org.company.request

import com.fasterxml.jackson.annotation.JsonProperty

data class GetUsersRequest(
    @JsonProperty("pageNumber", defaultValue = "0", required = true)
    val pageNumber: Int = 0,
    @JsonProperty("pageSize", defaultValue = "100", required = true)
    val pageSize: Int = 100,
)
