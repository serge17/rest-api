package org.company.extension

import org.company.model.User
import org.company.response.UserResponse

fun User.toUserResponse(): UserResponse {
    return UserResponse(
        name = this.name,
        email = this.email,
        phone = this.phone,
        id = this.id,
        createdAtUtc = this.createdAtUtc,
        updatedAtUtc = this.updatedAtUtc,
    )
}
