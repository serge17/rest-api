package org.company.service

import org.company.Util
import org.company.extension.toPageRequest
import org.company.extension.toUserModel
import org.company.extension.toUserResponse
import org.company.model.Friends
import org.company.model.User
import org.company.repository.FriendsRepository
import org.company.repository.UserRepository
import org.company.request.CreateUserRequest
import org.company.request.GetUsersRequest
import org.company.response.ConnectUsersResponse
import org.company.response.DistanceResponse
import org.company.response.UserNullableResponse
import org.company.response.UserResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Service
class UserService(
    @Autowired
    private val userRepository: UserRepository,
    @Autowired
    private val friendsRepository: FriendsRepository,
) {
    fun findDistance(
        firstUserId: Long,
        secondUserId: Long,
    ): DistanceResponse {
        val firstUserOptional = userRepository.findById(firstUserId)
        val secondUserOptional = userRepository.findById(secondUserId)

        if (firstUserOptional.isEmpty) {
            return DistanceResponse(0, true, "User with id $firstUserId not found.")
        }
        if (secondUserOptional.isEmpty) {
            return DistanceResponse(0, true, "User with id $secondUserId not found.")
        }

        val dist = Util.bfs(firstUserId, secondUserId, friendsRepository)

        if (dist == 0) {
            return DistanceResponse(dist, true, "Users are not connected or their distance is greater than ${Util.MAX_DIST}")
        }
        return DistanceResponse(dist, false, "")
    }

    fun connectUsers(
        firstUserId: Long,
        secondUserId: Long,
    ): ConnectUsersResponse {
        try {
            friendsRepository.save(Friends(firstUserId, secondUserId))
            return ConnectUsersResponse(true, "")
        } catch (e: Exception) {
            return ConnectUsersResponse(false, e.message ?: "Could not connect users.")
        }
    }

    fun getById(id: Long): UserNullableResponse {
        val userOptional = userRepository.findById(id)
        if (userOptional.isEmpty) {
            return UserNullableResponse(false)
        }
        return UserNullableResponse(true, userOptional.get().toUserResponse())
    }

    fun getAll(getUsersRequest: GetUsersRequest): Page<UserResponse> {
        return userRepository.findAll(getUsersRequest.toPageRequest()).map { it.toUserResponse() }
    }

    fun saveUser(createUserRequest: CreateUserRequest): UserResponse {
        return userRepository.save(createUserRequest.toUserModel()).toUserResponse()
    }

    fun replaceUser(
        newUser: CreateUserRequest,
        id: Long,
    ): UserResponse {
        val userOptional = userRepository.findById(id)

        if (userOptional.isEmpty) {
            val createdUser = User(newUser.name, newUser.email, newUser.phone, id)
            userRepository.save(createdUser)
            return createdUser.toUserResponse()
        } else {
            val user = userOptional.get()
            user.name = newUser.name
            user.email = newUser.email
            user.phone = newUser.phone
            user.updatedAtUtc = OffsetDateTime.now(ZoneOffset.UTC)
            userRepository.save(user)
            return user.toUserResponse()
        }
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}
