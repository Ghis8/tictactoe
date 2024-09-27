package com.example.tictactoe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    val state= mutableStateOf(GameState())

    fun onActions(action:UserAction){

    }
}