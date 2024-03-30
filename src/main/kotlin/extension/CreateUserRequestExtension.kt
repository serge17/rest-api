package org.company.extension

import org.company.model.User
import org.company.request.CreateUserRequest

fun CreateUserRequest.toUserModel(): User {
    return User(
        name = this.name,
        email = this.email,
        phone = this.phone,
    )
}
