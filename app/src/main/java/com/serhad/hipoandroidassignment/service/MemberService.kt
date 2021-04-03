package com.serhad.hipoandroidassignment.service


import com.serhad.hipoandroidassignment.data.Response
import retrofit2.http.GET

interface MemberService {
    @GET("hipo.json")
    suspend fun getMembersData(): Response
}