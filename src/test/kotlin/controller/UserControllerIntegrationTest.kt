package controller

import org.company.SpringApplication
import org.company.model.User
import org.company.repository.UserRepository
import org.company.request.CreateUserRequest
import org.company.response.UserNullableResponse
import org.company.response.UserResponse
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringApplication::class])
class UserControllerIntegrationTest(
    @Autowired val userRepository: UserRepository,
) {
    @LocalServerPort
    private val port = 0

    val restTemplate: TestRestTemplate = TestRestTemplate()

    private lateinit var headers: HttpHeaders

    @BeforeAll
    fun init() {
        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
    }

    private fun createURLWithPort(path: String): String {
        return "http://localhost:$port/users$path"
    }

    @Test
    fun testGetOne() {
        val user = User("Smith Smith", "smith.smith@example.com", "1234123123")
        val savedUser = userRepository.save(user)
        print(savedUser)

        val entity = HttpEntity<String>(null, headers)
        val response =
            restTemplate.exchange(
                createURLWithPort("/${user.id}"),
                HttpMethod.GET,
                entity,
                UserNullableResponse::class.java,
            )

        val userResponse = response.body?.userResponse
        assertNotNull(userResponse)
        assertEquals("Smith Smith", userResponse.name)
    }

    @Test
    fun testSave() {
        val createUserRequest = CreateUserRequest("John John", "john.john@example.com", "312342313412312")
        val entity = HttpEntity<CreateUserRequest>(createUserRequest, headers)
        val response =
            restTemplate.exchange(
                createURLWithPort(""),
                HttpMethod.POST,
                entity,
                UserResponse::class.java,
            )

        val userResponse = response.body
        assertNotNull(userResponse)
        assertEquals("John John", userResponse.name)
    }
}
