package com.serhad.hipoandroidassignment.service;


import javax.inject.Inject
import javax.inject.Singleton;

@Singleton
public class HipoRepository @Inject constructor(private val memberService: MemberService) {

    suspend fun getMembers() = memberService.getMembersData()

}
