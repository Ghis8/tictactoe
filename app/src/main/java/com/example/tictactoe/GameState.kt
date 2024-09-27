package com.example.tictactoe



enum class BoardCellValue{
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType{
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE
}
data class GameState(
    val playerCircleCount:Int=0,
    val playerCrossCount:Int=0,
    val drawCount:Int=0,
    val playerHintText:String="Player 'O' Turn",
    val currentTurn:BoardCellValue=BoardCellValue.CIRCLE,
    val victoryType:VictoryType=VictoryType.NONE,
    val hasWon:Boolean=false
)















