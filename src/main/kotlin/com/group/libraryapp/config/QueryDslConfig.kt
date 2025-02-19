package com.group.libraryapp.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *packageName    : com.group.libraryapp.config
 * fileName       : QueryDslConfig
 * author         : Yeong-Huns
 * date           : 2025-02-20
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-20        Yeong-Huns       최초 생성
 */
/* 쿼리 DSL 설정 */
@Configuration
class QueryDslConfig ( private val em: EntityManager ) {
    @Bean
    fun querydsl(): JPAQueryFactory = JPAQueryFactory(em)
}