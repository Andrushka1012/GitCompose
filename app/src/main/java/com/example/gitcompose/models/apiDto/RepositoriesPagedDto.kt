package com.example.gitcompose.models.apiDto

// This file generated with json to kotlin plugin. In real projects i prefer to use
// https://github.com/OpenAPITools/openapi-generator
data class RepositoriesPagedDto(
    val incomplete_results: Boolean,
    val items: List<RepositoryDto>,
    val total_count: Int
)