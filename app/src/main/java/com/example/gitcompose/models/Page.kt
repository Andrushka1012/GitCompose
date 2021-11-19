package com.example.gitcompose.models

import kotlin.math.ceil

data class Page<T>(
    val results: List<T>,
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalCount: Int = 0
) {
    private val totalPages: Int
        get() = ceil(totalCount.toDouble() / pageSize).toInt()

    private val hasNextPage: Boolean
        get() = page + 1 <= totalPages

    val nextPage: Int?
        get() = when {
            hasNextPage -> page + 1
            else -> null
        }

    val previousPage: Int?
        get() = when {
            page.minus(1) > 0 -> page - 1
            else -> null
        }

    companion object {
        fun <T> emptyPage() = Page(emptyList<T>())
    }
}