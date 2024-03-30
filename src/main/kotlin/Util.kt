package org.company

import org.company.repository.FriendsRepository

object Util {
    const val MAX_DIST: Int = 5

    /**
     * breadth first search to find the distance between users
     * @param first - first user id
     * @param second - second user id
     * @param friendsRepository - friendsRepository
     * @param maxDist - max distance to examine. Limits the execution time.
     * @return distance, if users are connected and their distance doesn't exceed [maxDist], 0 otherwise
     * */
    fun bfs(
        first: Long,
        second: Long,
        friendsRepository: FriendsRepository,
        maxDist: Int = MAX_DIST,
    ): Int {
        var dist = 0

        var set = mutableSetOf(first)
        val examined = mutableSetOf<Long>()

        var tempSet = mutableSetOf<Long>()

        while (set.isNotEmpty() && dist <= maxDist) {
            set.forEach { currUserId ->
                if (currUserId == second) {
                    return dist
                } else {
                    val friendsIds = getFriendsIds(currUserId, friendsRepository).filter { !examined.contains(it) }
                    examined.addAll(friendsIds)
                    tempSet.addAll(friendsIds)
                }
            }

            set = tempSet
            tempSet = mutableSetOf()
            dist += 1
        }
        return 0
    }

    private fun getFriendsIds(
        userId: Long,
        friendsRepository: FriendsRepository,
    ): Set<Long> {
        val set = mutableSetOf<Long>()
        val firstFriendsList = friendsRepository.findByFirstOrSecond(userId, userId)

        firstFriendsList.forEach {
            if (it.first != userId) {
                set.add(it.first)
            } else {
                set.add(it.second)
            }
        }
        return set
    }
}
