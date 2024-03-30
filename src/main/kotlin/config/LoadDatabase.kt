package org.company.config

import org.company.model.Friends
import org.company.model.User
import org.company.repository.FriendsRepository
import org.company.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {
    private val log: Logger = LoggerFactory.getLogger(LoadDatabase::class.java)

    @Bean
    fun initDatabase(
        userRepository: UserRepository,
        friendsRepository: FriendsRepository,
    ): CommandLineRunner {
        return CommandLineRunner {
            val user1 = userRepository.save(User("John Johnson", "john.johnson@example.com", "12345678"))
            val user2 = userRepository.save(User("Steve Stephenson", "steve.stephenson@example.com", "987654321"))
            val user3 = userRepository.save(User("Jack Jackson", "jack.jackson@example.com", "0908978874233"))
            val user4 = userRepository.save(User("Pete Peterson", "pete.peterson@example.com", "12345678"))
            val user5 = userRepository.save(User("Charles Carlson", "charles.carlson@example.com", "09098776123"))
            val user6 = userRepository.save(User("Bob Robertson", "bob.robertson@example.com", "7676321123"))
            val user7 = userRepository.save(User("Brian Brianson", "brian.brianson@example.com", "87423784623746"))

            log.info("Preloading $user1")
            log.info("Preloading $user2")
            log.info("Preloading $user3")
            log.info("Preloading $user4")
            log.info("Preloading $user5")
            log.info("Preloading $user6")
            log.info("Preloading $user7")

            val friends1 = friendsRepository.save(Friends(user1.id!!, user2.id!!))
            val friends2 = friendsRepository.save(Friends(user2.id, user3.id!!))
            val friends3 = friendsRepository.save(Friends(user3.id, user4.id!!))
            val friends4 = friendsRepository.save(Friends(user4.id, user5.id!!))
            val friends5 = friendsRepository.save(Friends(user5.id, user6.id!!))
            val friends6 = friendsRepository.save(Friends(user6.id, user7.id!!))

            log.info("Preloading $friends1")
            log.info("Preloading $friends2")
            log.info("Preloading $friends3")
            log.info("Preloading $friends4")
            log.info("Preloading $friends5")
            log.info("Preloading $friends6")
        }
    }
}
