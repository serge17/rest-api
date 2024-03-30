package org.company.response

data class UserNullableResponse(
    val success: Boolean,
    val userResponse: UserResponse? = null,
)
