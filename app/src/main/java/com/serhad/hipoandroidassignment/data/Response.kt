package com.serhad.hipoandroidassignment.data


data class Response(
    val company: String,
    val members: List<Member>,
    val team: String
)