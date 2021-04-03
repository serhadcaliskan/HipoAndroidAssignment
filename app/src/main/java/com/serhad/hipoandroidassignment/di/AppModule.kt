package com.serhad.hipoandroidassignment.di

import com.serhad.hipoandroidassignment.service.MemberService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl("https://gist.githubusercontent.com/artizco/a957d4e0af6f9d35048808e7200ea076/raw/0d45cd846122c16439148ddd21057c55f1ec6a4b/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    fun provideMemberService(retrofit: Retrofit): MemberService = retrofit.create(MemberService::class.java)
}