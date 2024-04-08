package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @Test
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("lee", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("lee")
        assertThat(results[0].age).isNull()
    }

    @Test
    fun getUsersTest() {
        // given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null)
        ))

        // when
        val resultUsers = userService.getUsers()

        //then
        assertThat(resultUsers).hasSize(2)
        assertThat(resultUsers).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(resultUsers).extracting("age").containsExactlyInAnyOrder(20, null)
    }

}