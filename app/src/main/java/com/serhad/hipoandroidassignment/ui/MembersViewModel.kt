package com.serhad.hipoandroidassignment.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.serhad.hipoandroidassignment.service.HipoRepository
import kotlinx.coroutines.Dispatchers

import androidx.lifecycle.*
import com.serhad.hipoandroidassignment.data.Hipo
import com.serhad.hipoandroidassignment.data.Member

class MembersViewModel @ViewModelInject constructor(private val repository: HipoRepository):ViewModel() {

    var membersData= MutableLiveData<ArrayList<Member>>()
    var mutableLiveDataUpdated=false

    var dataFetch =liveData(Dispatchers.IO){
        val temp=repository.getMembers().members
        emit(temp)
    }

    init {
        println("sad")
        println(membersData)

    }

    fun addUser(){

        var temp=ArrayList<Member>(membersData.value)
        temp.add(Member(22,"serhadcaliskan", Hipo("intern",0)
                ,"İstanbul", "Serhad Caliskan"))
        membersData.postValue(temp)
    }
    fun updateMutableList(list:ArrayList<Member>){
        if(!mutableLiveDataUpdated){

            membersData.postValue(list)
            mutableLiveDataUpdated=!mutableLiveDataUpdated

        }


    }
}