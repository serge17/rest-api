package org.company.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DistanceResponse(
    @JsonProperty("distance")
    val distance: Int,
    @JsonProperty("error")
    val error: Boolean,
    @JsonProperty("message")
    val message: String,
)
