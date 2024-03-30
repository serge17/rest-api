package org.company.repository

import org.company.model.Friends
import org.springframework.data.jpa.repository.JpaRepository

interface FriendsRepository : JpaRepository<Friends, Long> {
    fun findByFirstOrSecond(
        first: Long,
        second: Long,
    ): List<Friends>
}
