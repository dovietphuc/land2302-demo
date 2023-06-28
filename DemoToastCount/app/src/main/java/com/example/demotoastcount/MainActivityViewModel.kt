package com.example.demotoastcount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var numberValue = MutableLiveData<Int>(0)

    fun increaseValue(){
        numberValue.value = numberValue.value!! + 1
    }
}