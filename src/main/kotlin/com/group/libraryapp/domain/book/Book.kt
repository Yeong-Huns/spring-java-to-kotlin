package com.group.libraryapp.domain.book

import com.group.libraryapp.type.BookType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 *packageName    : com.group.librayapp.domain.book
 * fileName       : Book
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */

@Entity
class Book(
    val name: String,
    @Enumerated(EnumType.STRING)
    val type: BookType,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    init {
        if(name.isBlank()){
            throw IllegalArgumentException("이름은 비어있을 수 없습니다.")
        }
    }

    companion object{
        fun fixture(
            name: String = "책 이름",
            type: BookType = BookType.COMPUTER,
            id: Long? = null
        ): Book{
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }
}