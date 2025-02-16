package com.group.libraryapp.uitl

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

/**
 *packageName    : com.group.libraryapp.uitl
 * fileName       : ExceptionUtils
 * author         : Yeong-Huns
 * date           : 2025-02-16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-16        Yeong-Huns       최초 생성
 */

fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {
    return this.findByIdOrNull(id) ?: fail("[$id] 조회에 실패했습니다.")
}