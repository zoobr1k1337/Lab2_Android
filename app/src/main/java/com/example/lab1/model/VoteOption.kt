package com.example.lab1.model

import java.util.UUID

data class VoteOption(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val votes: Int = 0
)
