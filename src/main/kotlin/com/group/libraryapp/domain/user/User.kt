package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import javax.persistence.*

/**
 *packageName    : com.group.libraryapp.domain.user
 * fileName       : User
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */
@Entity
class User(
    var name: String,
    var age: Int?,
    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다.")
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun loanBook(book: Book) {
        this.userLoanHistories.add(UserLoanHistory(this, book.name, false))
    }

    fun returnBook(bookName: String) {
        /*this.userLoanHistories.asSequence()
            .filter { history -> history.bookName == bookName }
            .first()
            .doReturn()*/
        /* 좀 더 깔끔하게 변경 */
        this.userLoanHistories.first{ history -> history.bookName == bookName }.doReturn()
    }
}