package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *packageName    : com.group.libraryapp
 * fileName       : LibraryAppApplication
 * author         : Yeong-Huns
 * date           : 2025-02-17
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-17        Yeong-Huns       최초 생성
 */
@SpringBootApplication
class LibraryAppApplication

fun main(args: Array<String>) {
    runApplication<LibraryAppApplication>(*args)
}