package com.group.libraryapp.dto.book.response

import com.group.libraryapp.type.BookType

/**
 *packageName    : com.group.libraryapp.dto.book.response
 * fileName       : BookStatResponse
 * author         : Yeong-Huns
 * date           : 2025-02-19
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-19        Yeong-Huns       최초 생성
 */
data class BookStatResponse(
    val type: BookType,
    val count: Int,
)
