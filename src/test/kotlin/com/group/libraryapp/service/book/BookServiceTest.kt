package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.JavaBook
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    /**
     * 각각의 유닛테스트가 실행 완료될 때마다 DB 클리어
     */
    @AfterEach
    fun cleanDB() {
        bookRepository.deleteAll()
        userRepository.deleteAll()  // FK의 엔티티도 삭제
    }

    @Test
    @DisplayName("책 저장 테스트")
    fun saveBookTest() {
        // given
        val request = BookRequest("삼국지")

        //when
        bookService.saveBook(request)

        // then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("삼국지")
    }

    @Test
    @DisplayName("책 대출 기능 테스트")
    fun loanBookTest() {
        // given
        bookRepository.save(Book("삼국지"))
        val saveUser = userRepository.save(User("lee", null))
        val request = BookLoanRequest("lee", "삼국지")

        // when
        bookService.loanBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("삼국지")
        assertThat(results[0].user.id).isEqualTo(saveUser.id)
        assertThat(results[0].isReturn).isFalse
    }

    @Test
    @DisplayName("대출이 이미 된 책 대출 테스트")
    fun loanBookFailTest() {
        // given
        bookRepository.save(Book("삼국지"))
        val saveUser = userRepository.save(User("lee", null))
        // 이미 대출 된 책이므로 대출 기록이 존재
        userLoanHistoryRepository.save(UserLoanHistory(saveUser, "삼국지", false))
        val request = BookLoanRequest("lee", "삼국지")

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    @DisplayName("책 반납 기능 테스트")
    fun returnBookTest() {
        // given
        val saveUser = userRepository.save(User("lee", null))
        userLoanHistoryRepository.save(UserLoanHistory(saveUser, "삼국지", false))
        val request = BookReturnRequest("lee", "삼국지")

        // when
        bookService.returnBook(request)

        // then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].isReturn).isTrue
    }

}