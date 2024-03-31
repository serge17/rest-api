package org.company.cache

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.company.response.UserNullableResponse
import org.company.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class UserServiceCache(
    @Autowired
    private val userService: UserService,
) {
    val cache: Cache<Long, UserNullableResponse> =
        Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)
            .build()

    fun getById(id: Long): UserNullableResponse {
        var userNullableResponse = cache.getIfPresent(id)
        if (userNullableResponse == null) {
            userNullableResponse = userService.getById(id)
            cache.put(id, userNullableResponse)
        }
        return userNullableResponse
    }
}
