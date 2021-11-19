package com.example.gitcompose.models.apiDto

// This file generated with json to kotlin plugin. In real projects i prefer to use
// https://github.com/OpenAPITools/openapi-generator
data class FileDto(
    val _links: Links,
    val content: String,
    val download_url: String,
    val encoding: String,
    val git_url: String,
    val html_url: String,
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val type: String,
    val url: String
)