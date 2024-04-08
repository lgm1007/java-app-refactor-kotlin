package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    /**
     * 각각의 유닛테스트가 실행 완료될 때마다 DB 클리어
     */
    @AfterEach
    fun cleanDB() {
        userRepository.deleteAll()
    }

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

    @Test
    fun updateUserNameTest() {
        // given
        val saveUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(saveUser.id, "B")

        // when
        userService.updateUserName(request)

        // then
        val resultUser = userRepository.findAll()[0]
        assertThat(resultUser.name).isEqualTo("B")
    }

}