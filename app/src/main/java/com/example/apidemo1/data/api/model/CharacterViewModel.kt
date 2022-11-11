package com.example.apidemo1.data.api.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apidemo1.data.api.APIService
import kotlinx.coroutines.launch
import java.lang.Exception

class CharacterViewModel : ViewModel(){
    private val _characterList = mutableStateListOf<Character>()
    var errorMessage: String by mutableStateOf("")

    val characterList: List<Character>
        get() = _characterList //since _todolist is private

    // fetches from our apo
    fun getCharacters(){
        //coroutine
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try{
                _characterList.clear()
                _characterList.addAll(apiService.getCharacters())
                Log.d("Tesfa character", apiService.getCharacters().toString())
            }
            catch (e: Exception){
                errorMessage = e.message.toString() // need to log in rather
            }
        }
    }// getTodoList
}// ViewModel